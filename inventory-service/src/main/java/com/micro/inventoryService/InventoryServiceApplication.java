package com.micro.inventoryService;

import com.micro.inventoryService.model.Inventory;
import com.micro.inventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//			Inventory in1 = new Inventory();
//			in1.setSkuCode("iphone-13");
//			in1.setQuantity(100);
//			Inventory in2 = new Inventory();
//			in2.setSkuCode("macbook-pro");
//			in2.setQuantity(100);
//			inventoryRepository.save(in1);
//			inventoryRepository.save(in2);
//		};
//	}

}
