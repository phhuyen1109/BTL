package com.mycompany.quanlyquaninternet.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Lớp đại diện cho 1 đơn gọi món
public class DonHang {
    private int ma;
    private Integer maPhien;
    private int maMayTinh;
    private double tongGia;
    private Date thoiGianDat;
    private List<ChiTietDonHang> danhSachMon;

    public DonHang() {
        this.danhSachMon = new ArrayList<>();
        this.thoiGianDat = new Date();
    }

    public DonHang(Integer maPhien, int maMayTinh) {
        this();
        this.maPhien = maPhien;
        this.maMayTinh = maMayTinh;
    }

    public int getMa() { return ma; }
    public void setMa(int ma) { this.ma = ma; }
    public Integer getMaPhien() { return maPhien; }
    public void setMaPhien(Integer maPhien) { this.maPhien = maPhien; }
    public int getMaMayTinh() { return maMayTinh; }
    public void setMaMayTinh(int maMayTinh) { this.maMayTinh = maMayTinh; }
    public double getTongGia() { return tongGia; }
    public void setTongGia(double tongGia) { this.tongGia = tongGia; }
    public Date getThoiGianDat() { return thoiGianDat; }
    public void setThoiGianDat(Date thoiGianDat) { this.thoiGianDat = thoiGianDat; }
    public List<ChiTietDonHang> getDanhSachMon() { return danhSachMon; }
    public void setDanhSachMon(List<ChiTietDonHang> danhSachMon) { this.danhSachMon = danhSachMon; }

    public void themMon(ChiTietDonHang mon) {
        danhSachMon.add(mon);
        tinhLaiTong();
    }

    public void tinhLaiTong() {
        tongGia = danhSachMon.stream()
                .mapToDouble(m -> m.getGia() * m.getSoLuong())
                .sum();
    }
}
