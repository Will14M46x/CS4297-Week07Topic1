package com.example.order_api_service.controller;

import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @RequestMapping("/api/order") public class OrderController {

    @PostMapping("/sync")
    public ResponseEntity<String> placeOrderSync(@RequestBody Map<String, Object> orderData) {
        long startTime = System.currentTimeMillis();
        System.out.println("Received synchronous order request: " + orderData);

        try {
            // Simulate long-running work (payment + email)
            System.out.println("Processing payment...");
            Thread.sleep(2000);

            System.out.println("Sending confirmation email...");
            Thread.sleep(3000);

            System.out.println("Order completed successfully.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Order failed due to interruption.");
        }

        long duration = System.currentTimeMillis() - startTime;
        return ResponseEntity.ok("Order completed in " + duration + " ms (synchronous)");
    }


}