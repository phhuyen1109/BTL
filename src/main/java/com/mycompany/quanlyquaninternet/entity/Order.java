package com.mycompany.quanlyquaninternet.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Integer sessionId;
    private int computerId;
    private double totalPrice;
    private Date orderTime;
    private List<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
        this.orderTime = new Date();
    }

    public Order(Integer sessionId, int computerId) {
        this();
        this.sessionId = sessionId;
        this.computerId = computerId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Integer getSessionId() { return sessionId; }
    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }
    public int getComputerId() { return computerId; }
    public void setComputerId(int computerId) { this.computerId = computerId; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public Date getOrderTime() { return orderTime; }
    public void setOrderTime(Date orderTime) { this.orderTime = orderTime; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public void addItem(OrderItem item) {
        items.add(item);
        recalculateTotal();
    }

    public void recalculateTotal() {
        totalPrice = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }
}
