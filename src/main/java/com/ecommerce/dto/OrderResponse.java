package com.ecommerce.dto;

import java.time.LocalDateTime;

public class OrderResponse {
    private String customerName;
    private Double totalAmount;
    private String itemName;
    private LocalDateTime orderTime;

    public OrderResponse(String customerName, Double totalAmount, String itemName, LocalDateTime orderTime) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.itemName = itemName;
        this.orderTime = orderTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}
