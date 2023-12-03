package com.micro.inventoryService.service;

import com.micro.inventoryService.dto.InventoryResponse;
import com.micro.inventoryService.model.Inventory;
import com.micro.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public boolean inStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
    public List<InventoryResponse> inStock(List<String> skuCodes) {
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0)
                        .build()).toList();
    }
    public InventoryResponse inStockSku(String skuCode, Long id) {
        Optional<Inventory> invs = inventoryRepository.findByIdAndSkuCode(id, skuCode);
        if(invs.isPresent()) {
            Inventory inventory = invs.get();
            return InventoryResponse.builder().skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity()>0)
                    .build();
        }
        return null;
    }
//    public boolean inStock(String skuCode, Long id, boolean orCondition) {
//        List<Inventory> inventoryList =  inventoryRepository.findByIdOrSkuCode(id, skuCode);
//        if(inventoryList.size() >= 1)
//            return true;
//        else
//            return false;
//    }
}
