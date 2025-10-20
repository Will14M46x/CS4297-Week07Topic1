package com.example.orderapiservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Step 2
    @PostMapping("/sync")
    public ResponseEntity<Map<String, Object>> placeOrderSync(@RequestBody String orderJson) {
        long startTime = System.currentTimeMillis();

        System.out.println("Order received: " + orderJson);

        // Simulate payment processing (3 seconds)
        System.out.println("Processing payment...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Payment processed successfully");

        // Simulate email confirmation (2 seconds)
        System.out.println("Sending confirmation email...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Email sent successfully");

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Order completed successfully in " + duration + " ms");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order completed successfully (synchronous)");
        response.put("processingTime", duration + " ms");
        response.put("order", orderJson);

        return ResponseEntity.ok(response);
    }

    // Step 3
    @PostMapping("/async")
    public ResponseEntity<Map<String, Object>> placeOrderAsync(@RequestBody String orderJson) {
        long startTime = System.currentTimeMillis();

        System.out.println("Order received (async): " + orderJson);

        // Send message to Kafka immediately
        kafkaTemplate.send("order-placement-topic", orderJson);
        System.out.println("Order message sent to Kafka topic");

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("API response sent in " + duration + " ms");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order received and queued for processing");
        response.put("status", "ACCEPTED");
        response.put("processingTime", duration + " ms");
        response.put("order", orderJson);

        // Return 202 Accepted immediately
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}