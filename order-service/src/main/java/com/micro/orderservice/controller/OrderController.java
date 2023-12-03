package com.micro.orderservice.controller;

import com.micro.orderservice.dto.OrderRequest;
import com.micro.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @GetMapping("hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "Hiii";
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Reached111");
        orderService.placeOrder(orderRequest);
        return "Order created successfully";
    }
}
