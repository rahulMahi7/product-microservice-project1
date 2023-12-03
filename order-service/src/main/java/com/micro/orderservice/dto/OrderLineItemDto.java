package com.micro.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class OrderLineItemDto {
    private long id;
    private String skuCode;
    private BigDecimal price;
    private int quantity;
}
