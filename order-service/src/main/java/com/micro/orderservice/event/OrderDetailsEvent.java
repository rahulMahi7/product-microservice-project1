package com.micro.orderservice.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class OrderDetailsEvent {
    private long orderId;
}
