package com.mycompany.quanlyquaninternet.entity;

import java.util.Date;

public class Session {
    private int id;
    private int computerId;
    private Integer customerId;
    private String customerName;
    private Date startTime;
    private Date endTime;
    private double totalCost;
    private String status; // "Đang chạy", "Kết thúc"

    // Transient fields (not in DB, for display)
    private String computerName;
    private double pricePerHour;

    public Session() {}

    public Session(int computerId, String customerName, Integer customerId) {
        this.computerId = computerId;
        this.customerName = customerName;
        this.customerId = customerId;
        this.startTime = new Date();
        this.status = "Đang chạy";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getComputerId() { return computerId; }
    public void setComputerId(int computerId) { this.computerId = computerId; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getComputerName() { return computerName; }
    public void setComputerName(String computerName) { this.computerName = computerName; }
    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }

    public boolean isRunning() { return "Đang chạy".equals(status); }

    /**
     * Tính thời gian sử dụng (giờ)
     */
    public double getElapsedHours() {
        Date end = (endTime != null) ? endTime : new Date();
        long diff = end.getTime() - startTime.getTime();
        return diff / (1000.0 * 60 * 60);
    }

    /**
     * Tính tiền dựa trên thời gian và giá/giờ
     */
    public double calculateCost(double pricePerHour) {
        return Math.ceil(getElapsedHours() * 10) / 10.0 * pricePerHour;
    }
}
