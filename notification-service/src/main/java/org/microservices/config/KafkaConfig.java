package org.microservices.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.microservices.appConstants.NotificationAppConstant;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class KafkaConfig {

    @KafkaListener(topics = NotificationAppConstant.ORDER_PLACED_TOPIC, groupId = NotificationAppConstant.GROUP_ID)
    public void getOrderDetails(long id) {
      System.out.println("Order placed and got message from kafka: " + id);
      //Code for email
    }
}
