package com.example.orderapiservice.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String placeOrder(@RequestBody String orderJson) {
        kafkaTemplate.send("order-placement-topic", orderJson);
        return "Order placed successfully!";
    }
}
