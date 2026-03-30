package com.mycompany.quanlyquaninternet.entity;

public class OrderItem {
    private int id;
    private int orderId;
    private int foodDrinkId;
    private String foodDrinkName;
    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(int foodDrinkId, String foodDrinkName, int quantity, double price) {
        this.foodDrinkId = foodDrinkId;
        this.foodDrinkName = foodDrinkName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getFoodDrinkId() { return foodDrinkId; }
    public void setFoodDrinkId(int foodDrinkId) { this.foodDrinkId = foodDrinkId; }
    public String getFoodDrinkName() { return foodDrinkName; }
    public void setFoodDrinkName(String foodDrinkName) { this.foodDrinkName = foodDrinkName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getSubTotal() { return price * quantity; }
}
