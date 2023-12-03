package com.micro.inventoryService.inventoryController;

import com.micro.inventoryService.dto.InventoryResponse;
import com.micro.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String helloService() {
        return "Hello World";
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> inStock(@RequestParam List<String> skuCode) {
        return inventoryService.inStock(skuCode);
    }

    @GetMapping("/{sku-code}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse inStockSku(@PathVariable("sku-code") String skuCode, @PathVariable("id") String id) {
        return inventoryService.inStockSku(skuCode, Long.parseLong(id));
    }

}
