package com.micro.orderservice.service;

import com.micro.orderservice.dto.InventoryRequest;
import com.micro.orderservice.dto.InventoryResponse;
import com.micro.orderservice.dto.OrderLineItemDto;
import com.micro.orderservice.dto.OrderRequest;
import com.micro.orderservice.model.Order;
import com.micro.orderservice.model.OrderLineItem;
import com.micro.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemList(orderLineItems);
        List<String> orderSkuCodes = order.getOrderLineItemList().stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

//        List<InventoryRequest> inventoryRequests = getInventoryRequestDtoList(orderLineItems);
        InventoryResponse[] result = webClientBuilder.build().get()
                .uri("http://inventory-service/apis/v1/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", orderSkuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class).block();
        if(result != null) {
            if(Arrays.stream(result).allMatch(InventoryResponse::getIsInStock)) {
                orderRepository.save(order);

            }
            else {
                throw new IllegalArgumentException("Product(s) out of stock");
            }
        }
        else {
            throw new IllegalArgumentException("Product(s) out of stock");
        }
    }

    public List<InventoryRequest> getInventoryRequestDtoList(List<OrderLineItem> orderLineItems) {
        List<InventoryRequest> inventoryRequests = new ArrayList<InventoryRequest>();
        for(OrderLineItem ord : orderLineItems) {
            InventoryRequest inventoryRequest = InventoryRequest.builder()
                    .skuCode(ord.getSkuCode())
                    .quantity(ord.getQuantity())
                    .build();
            inventoryRequests.add(inventoryRequest);
        }
        return inventoryRequests;
    }
    public OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        return orderLineItem;
    }
}
