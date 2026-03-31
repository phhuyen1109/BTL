package com.mycompany.quanlyquaninternet.entity;

// Lớp đại diện cho 1 tài khoản quản trị
public class NguoiDung {
    private int ma;
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;

    public NguoiDung() {}

    public NguoiDung(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public NguoiDung(int ma, String tenDangNhap, String matKhau, String vaiTro) {
        this.ma = ma;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public int getMa() { return ma; }
    public void setMa(int ma) { this.ma = ma; }
    public String getTenDangNhap() { return tenDangNhap; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; }
    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }
    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }
}
