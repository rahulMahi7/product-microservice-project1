package com.micro.orderservice.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<OrderLineItemDto> orderLineItemDtoList;
}
