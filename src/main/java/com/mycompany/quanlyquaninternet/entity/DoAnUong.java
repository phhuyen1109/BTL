package com.mycompany.quanlyquaninternet.entity;

// Lớp đại diện cho 1 món ăn/uống trong menu
public class DoAnUong {
    private int ma;
    private String ten;
    private double gia;
    private String phanLoai;    // "Đồ ăn" hoặc "Nước uống"
    private int diemDoi;        // Số điểm cần để đổi thưởng
    private boolean conHang;

    public DoAnUong() {}

    public int getMa() { return ma; }
    public void setMa(int ma) { this.ma = ma; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }
    public String getPhanLoai() { return phanLoai; }
    public void setPhanLoai(String phanLoai) { this.phanLoai = phanLoai; }
    public int getDiemDoi() { return diemDoi; }
    public void setDiemDoi(int diemDoi) { this.diemDoi = diemDoi; }
    public boolean isConHang() { return conHang; }
    public void setConHang(boolean conHang) { this.conHang = conHang; }
}
