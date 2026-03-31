package com.mycompany.quanlyquaninternet.entity;

import java.util.Date;

// Lớp đại diện cho 1 phiên sử dụng máy
public class PhienSuDung {
    private int ma;
    private int maMayTinh;
    private Integer maKhachHang;
    private String tenKhach;
    private Date gioBatDau;
    private Date gioKetThuc;
    private double tongTien;
    private String trangThai; // "Đang chạy", "Kết thúc"

    // Trường phụ (không lưu DB, dùng để hiển thị)
    private String tenMayTinh;
    private double giaMoiGio;

    public PhienSuDung() {}

    public PhienSuDung(int maMayTinh, String tenKhach, Integer maKhachHang) {
        this.maMayTinh = maMayTinh;
        this.tenKhach = tenKhach;
        this.maKhachHang = maKhachHang;
        this.gioBatDau = new Date();
        this.trangThai = "Đang chạy";
    }

    public int getMa() { return ma; }
    public void setMa(int ma) { this.ma = ma; }
    public int getMaMayTinh() { return maMayTinh; }
    public void setMaMayTinh(int maMayTinh) { this.maMayTinh = maMayTinh; }
    public Integer getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(Integer maKhachHang) { this.maKhachHang = maKhachHang; }
    public String getTenKhach() { return tenKhach; }
    public void setTenKhach(String tenKhach) { this.tenKhach = tenKhach; }
    public Date getGioBatDau() { return gioBatDau; }
    public void setGioBatDau(Date gioBatDau) { this.gioBatDau = gioBatDau; }
    public Date getGioKetThuc() { return gioKetThuc; }
    public void setGioKetThuc(Date gioKetThuc) { this.gioKetThuc = gioKetThuc; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public String getTenMayTinh() { return tenMayTinh; }
    public void setTenMayTinh(String tenMayTinh) { this.tenMayTinh = tenMayTinh; }
    public double getGiaMoiGio() { return giaMoiGio; }
    public void setGiaMoiGio(double giaMoiGio) { this.giaMoiGio = giaMoiGio; }

    public boolean dangChay() { return "Đang chạy".equals(trangThai); }

    // Tính thời gian sử dụng (giờ)
    public double tinhSoGio() {
        Date ketThuc = (gioKetThuc != null) ? gioKetThuc : new Date();
        long chenhLech = ketThuc.getTime() - gioBatDau.getTime();
        return chenhLech / (1000.0 * 60 * 60);
    }

    // Tính tiền dựa trên thời gian và giá/giờ
    public double tinhTien(double giaMoiGio) {
        return Math.ceil(tinhSoGio() * 10) / 10.0 * giaMoiGio;
    }
}
