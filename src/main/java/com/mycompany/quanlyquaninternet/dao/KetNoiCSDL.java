package com.mycompany.quanlyquaninternet.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Lớp quản lý kết nối cơ sở dữ liệu
public class KetNoiCSDL {
    private static KetNoiCSDL thucThe;
    private Connection ketNoi;
    private String duongDan;
    private String tenDangNhap;
    private String matKhau;

    private KetNoiCSDL() {
        try {
            Properties cauHinh = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
            if (is != null) {
                cauHinh.load(is);
                duongDan = cauHinh.getProperty("db.url");
                tenDangNhap = cauHinh.getProperty("db.username");
                matKhau = cauHinh.getProperty("db.password");
                String driver = cauHinh.getProperty("db.driver");
                Class.forName(driver);
            } else {
                duongDan = "jdbc:mysql://localhost:3306/quanly_internet?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true&characterEncoding=utf8";
                tenDangNhap = "root";
                matKhau = "";
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static KetNoiCSDL layThucThe() {
        if (thucThe == null) {
            thucThe = new KetNoiCSDL();
        }
        return thucThe;
    }

    public Connection layKetNoi() throws SQLException {
        if (ketNoi == null || ketNoi.isClosed()) {
            ketNoi = DriverManager.getConnection(duongDan, tenDangNhap, matKhau);
        }
        return ketNoi;
    }

    public Connection layKetNoiMoi() throws SQLException {
        return DriverManager.getConnection(duongDan, tenDangNhap, matKhau);
    }

    public static boolean kiemTraKetNoi() {
        try {
            Connection conn = layThucThe().layKetNoi();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void dongKetNoi() {
        try {
            if (ketNoi != null && !ketNoi.isClosed()) {
                ketNoi.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
