package com.micro.orderservice.controller;

import com.micro.orderservice.dto.OrderRequest;
import com.micro.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    Logger logger =  LogManager.getLogger(OrderController.class);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @CircuitBreaker(name = "orderCircuitBreaker" , fallbackMethod = "orderCircuitBreaker")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        logger.info("Order placed");
        return "Order created successfully";
    }
    public String orderCircuitBreaker(OrderRequest orderRequest, RuntimeException runtimeException) {
        return "Something went wrong! Please try again later";
    }
}
