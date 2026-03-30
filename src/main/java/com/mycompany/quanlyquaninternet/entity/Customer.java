package com.mycompany.quanlyquaninternet.entity;

import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private double balance;
    private double totalHours;
    private Date createdAt;

    public Customer() {}

    public Customer(int id, String name, String phone, double balance, double totalHours) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.balance = balance;
        this.totalHours = totalHours;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public double getTotalHours() { return totalHours; }
    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }
}
