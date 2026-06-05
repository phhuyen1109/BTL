package com.mycompany.quanlyquaninternet.entity;

public class Computer {
    private int id;
    private String name;
    private String type;        // "Thường" or "VIP"
    private double pricePerHour;
    private String status;      // "Trống", "Đang dùng", "Bảo trì"
    private String specs;

    public Computer() {}

    public Computer(int id, String name, String type, double pricePerHour, String status, String specs) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.status = status;
        this.specs = specs;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSpecs() { return specs; }
    public void setSpecs(String specs) { this.specs = specs; }

    public boolean isAvailable() { return "Trống".equals(status); }
    public boolean isInUse() { return "Đang dùng".equals(status); }
    public boolean isMaintenance() { return "Bảo trì".equals(status); }
    public boolean isVIP() { return "VIP".equals(type); }
}
