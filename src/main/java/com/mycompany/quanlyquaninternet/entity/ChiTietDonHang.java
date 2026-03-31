package com.mycompany.quanlyquaninternet.entity;

// Lớp đại diện cho 1 chi tiết trong đơn gọi món
public class ChiTietDonHang {
    private int ma;
    private int maDonHang;
    private int maDoAn;
    private String tenDoAn;
    private int soLuong;
    private double gia;

    public ChiTietDonHang() {}

    public ChiTietDonHang(int maDoAn, String tenDoAn, int soLuong, double gia) {
        this.maDoAn = maDoAn;
        this.tenDoAn = tenDoAn;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getMa() { return ma; }
    public void setMa(int ma) { this.ma = ma; }
    public int getMaDonHang() { return maDonHang; }
    public void setMaDonHang(int maDonHang) { this.maDonHang = maDonHang; }
    public int getMaDoAn() { return maDoAn; }
    public void setMaDoAn(int maDoAn) { this.maDoAn = maDoAn; }
    public String getTenDoAn() { return tenDoAn; }
    public void setTenDoAn(String tenDoAn) { this.tenDoAn = tenDoAn; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public double tinhThanhTien() { return gia * soLuong; }
}
