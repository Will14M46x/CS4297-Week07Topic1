package com.example.fulfillmentworkerservice.model;

import java.util.List;

public class Order {
    private String orderId;
    private List<String> items;
    private double amount;

    // Default constructor for Jackson deserialization
    public Order() {}

    public Order(String orderId, List<String> items, double amount) {
        this.orderId = orderId;
        this.items = items;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
