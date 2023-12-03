package com.micro.inventoryService.repository;

import com.micro.inventoryService.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Optional<Inventory> findBySkuCode(String skuCode);
    public Optional<Inventory> findByIdAndSkuCode(Long id, String skuCode);
    public List<Inventory> findByIdOrSkuCode(Long id, String skuCode);
    public List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
