package com.mycompany.quanlyquaninternet.entity;

// Lớp đại diện cho 1 máy tính
public class MayTinh {
    private int ma;
    private String ten;
    private String loai;           // "Thường" hoặc "VIP"
    private double giaMoiGio;
    private String trangThai;      // "Trống", "Đang dùng", "Bảo trì"
    private String cauHinh;

    public MayTinh() {}

    public MayTinh(int ma, String ten, String loai, double giaMoiGio, String trangThai, String cauHinh) {
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.giaMoiGio = giaMoiGio;
        this.trangThai = trangThai;
        this.cauHinh = cauHinh;
    }

    public int getMa() { return ma; }
    public void setMa(int ma) { this.ma = ma; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getLoai() { return loai; }
    public void setLoai(String loai) { this.loai = loai; }
    public double getGiaMoiGio() { return giaMoiGio; }
    public void setGiaMoiGio(double giaMoiGio) { this.giaMoiGio = giaMoiGio; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public String getCauHinh() { return cauHinh; }
    public void setCauHinh(String cauHinh) { this.cauHinh = cauHinh; }

    public boolean laTrong() { return "Trống".equals(trangThai); }
    public boolean dangDung() { return "Đang dùng".equals(trangThai); }
    public boolean dangBaoTri() { return "Bảo trì".equals(trangThai); }
    public boolean laVIP() { return "VIP".equals(loai); }
}
