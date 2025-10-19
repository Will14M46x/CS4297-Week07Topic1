package com.example.fulfillmentworkerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String orderId;

    private List<String> items;

    private double amount;

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

    @JsonProperty("items")
    public void setItems(List<String> items) {
        this.items = items;
    }
    @JsonProperty("item")
    private void setSingleItem(String item) {
        this.items = List.of(item);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
