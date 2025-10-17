package com.example.fulfillmentworkerservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "order-placement-topic", groupId = "fulfillment-group")
    public void listen(String message) {
        System.out.println("Received order: " + message);
    }
}
