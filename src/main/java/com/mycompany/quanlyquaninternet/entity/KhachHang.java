package com.mycompany.quanlyquaninternet.entity;

import java.util.Date;

// Lớp đại diện cho 1 khách hàng
public class KhachHang {
    private int ma;
    private String ten;
    private String sdt;
    private double soDu;
    private double tongGio;
    private int diem;
    private Date ngayTao;

    public KhachHang() {}

    public KhachHang(int ma, String ten, String sdt, double soDu, double tongGio, int diem) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.soDu = soDu;
        this.tongGio = tongGio;
        this.diem = diem;
    }

    public int getMa() { return ma; }
    public void setMa(int ma) { this.ma = ma; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public double getSoDu() { return soDu; }
    public void setSoDu(double soDu) { this.soDu = soDu; }
    public double getTongGio() { return tongGio; }
    public void setTongGio(double tongGio) { this.tongGio = tongGio; }
    public int getDiem() { return diem; }
    public void setDiem(int diem) { this.diem = diem; }
    public Date getNgayTao() { return ngayTao; }
    public void setNgayTao(Date ngayTao) { this.ngayTao = ngayTao; }
}
