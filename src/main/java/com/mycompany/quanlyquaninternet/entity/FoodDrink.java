package com.mycompany.quanlyquaninternet.entity;

public class FoodDrink {
    private int id;
    private String name;
    private double price;
    private String category; // "Đồ ăn", "Nước uống"
    private boolean available;

    public FoodDrink() {}

    public FoodDrink(int id, String name, double price, String category, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return name + " - " + String.format("%,.0f", price) + "đ";
    }
}
