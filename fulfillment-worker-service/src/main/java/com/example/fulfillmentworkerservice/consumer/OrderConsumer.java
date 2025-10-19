package com.example.fulfillmentworkerservice.consumer;

import com.example.fulfillmentworkerservice.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order-placement-topic", groupId = "fulfillment-group")
    public void consumeOrder(String orderJson) {
        try {
            logger.info("Received order for processing: {}", orderJson);

            // Parse the JSON message into an Order object
            Order order = objectMapper.readValue(orderJson, Order.class);

            // Simulate payment processing (3 seconds delay)
            logger.info("Starting payment processing for order: {}", order.getOrderId());
            Thread.sleep(3000);  // Simulate long-running payment task
            logger.info("Payment processed for order: {}", order.getOrderId());

            // Simulate email confirmation (2 seconds delay)
            logger.info("Starting email confirmation for order: {}", order.getOrderId());
            Thread.sleep(2000);  // Simulate long-running email task
            logger.info("Email confirmation sent for order: {}", order.getOrderId());

            logger.info("Order processing completed for: {}", order.getOrderId());
        } catch (Exception e) {
            logger.error("Error processing order: {} - {}", orderJson, e.getMessage());
        }
    }
}
