package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.PhienSuDung;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Lớp truy xuất dữ liệu phiên sử dụng
public class PhienSuDungDAO {

    private Connection layKetNoi() throws SQLException {
        return KetNoiCSDL.layThucThe().layKetNoi();
    }

    public List<PhienSuDung> layTatCa() {
        List<PhienSuDung> ds = new ArrayList<>();
        String sql = "SELECT p.*, m.ten as ten_may_tinh, m.gia_moi_gio " +
                     "FROM phien_su_dung p JOIN may_tinh m ON p.ma_may_tinh = m.ma ORDER BY p.ma DESC";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public List<PhienSuDung> layPhienDangChay() {
        List<PhienSuDung> ds = new ArrayList<>();
        String sql = "SELECT p.*, m.ten as ten_may_tinh, m.gia_moi_gio " +
                     "FROM phien_su_dung p JOIN may_tinh m ON p.ma_may_tinh = m.ma " +
                     "WHERE p.trang_thai = 'Đang chạy' ORDER BY p.gio_bat_dau DESC";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public PhienSuDung layTheoMa(int ma) {
        String sql = "SELECT p.*, m.ten as ten_may_tinh, m.gia_moi_gio " +
                     "FROM phien_su_dung p JOIN may_tinh m ON p.ma_may_tinh = m.ma WHERE p.ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ma);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return chuyenDoi(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public PhienSuDung layPhienDangChayTheoMay(int maMayTinh) {
        String sql = "SELECT p.*, m.ten as ten_may_tinh, m.gia_moi_gio " +
                     "FROM phien_su_dung p JOIN may_tinh m ON p.ma_may_tinh = m.ma " +
                     "WHERE p.ma_may_tinh = ? AND p.trang_thai = 'Đang chạy' LIMIT 1";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, maMayTinh);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return chuyenDoi(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int batDauPhien(PhienSuDung phien) {
        String sql = "INSERT INTO phien_su_dung (ma_may_tinh, ma_khach_hang, ten_khach, gio_bat_dau, trang_thai) VALUES (?, ?, ?, ?, 'Đang chạy')";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, phien.getMaMayTinh());
            if (phien.getMaKhachHang() != null) {
                pstmt.setInt(2, phien.getMaKhachHang());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setString(3, phien.getTenKhach());
            pstmt.setTimestamp(4, new Timestamp(phien.getGioBatDau().getTime()));
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public boolean ketThucPhien(int maPhien, double tongTien) {
        String sql = "UPDATE phien_su_dung SET gio_ket_thuc = NOW(), tong_tien = ?, trang_thai = 'Kết thúc' WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDouble(1, tongTien);
            pstmt.setInt(2, maPhien);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<PhienSuDung> layTheoNgay(Date ngay) {
        List<PhienSuDung> ds = new ArrayList<>();
        String sql = "SELECT p.*, m.ten as ten_may_tinh, m.gia_moi_gio " +
                     "FROM phien_su_dung p JOIN may_tinh m ON p.ma_may_tinh = m.ma " +
                     "WHERE DATE(p.gio_bat_dau) = DATE(?) ORDER BY p.gio_bat_dau DESC";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public double layDoanhThuTheoNgay(Date ngay) {
        String sql = "SELECT COALESCE(SUM(tong_tien), 0) FROM phien_su_dung WHERE DATE(gio_bat_dau) = DATE(?) AND trang_thai = 'Kết thúc'";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(ngay.getTime()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public double layDoanhThuKhoangNgay(Date tuNgay, Date denNgay) {
        String sql = "SELECT COALESCE(SUM(tong_tien), 0) FROM phien_su_dung WHERE DATE(gio_bat_dau) BETWEEN DATE(?) AND DATE(?) AND trang_thai = 'Kết thúc'";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(tuNgay.getTime()));
            pstmt.setDate(2, new java.sql.Date(denNgay.getTime()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // Doanh thu theo từng ngày
    public List<Object[]> layDoanhThuTheoTungNgay(Date tuNgay, Date denNgay) {
        List<Object[]> ds = new ArrayList<>();
        String sql = "SELECT DATE(gio_bat_dau) as ngay, COALESCE(SUM(tong_tien), 0) as doanh_thu, COUNT(*) as so_phien " +
                     "FROM phien_su_dung WHERE DATE(gio_bat_dau) BETWEEN DATE(?) AND DATE(?) AND trang_thai = 'Kết thúc' " +
                     "GROUP BY DATE(gio_bat_dau) ORDER BY ngay";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(tuNgay.getTime()));
            pstmt.setDate(2, new java.sql.Date(denNgay.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ds.add(new Object[]{
                    rs.getDate("ngay"),
                    rs.getDouble("doanh_thu"),
                    rs.getInt("so_phien")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public int demPhienHomNay() {
        String sql = "SELECT COUNT(*) FROM phien_su_dung WHERE DATE(gio_bat_dau) = CURDATE()";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public double layDoanhThuHomNay() {
        return layDoanhThuTheoNgay(new Date());
    }

    private PhienSuDung chuyenDoi(ResultSet rs) throws SQLException {
        PhienSuDung p = new PhienSuDung();
        p.setMa(rs.getInt("ma"));
        p.setMaMayTinh(rs.getInt("ma_may_tinh"));
        int maKH = rs.getInt("ma_khach_hang");
        p.setMaKhachHang(rs.wasNull() ? null : maKH);
        p.setTenKhach(rs.getString("ten_khach"));
        p.setGioBatDau(rs.getTimestamp("gio_bat_dau"));
        p.setGioKetThuc(rs.getTimestamp("gio_ket_thuc"));
        p.setTongTien(rs.getDouble("tong_tien"));
        p.setTrangThai(rs.getString("trang_thai"));
        p.setTenMayTinh(rs.getString("ten_may_tinh"));
        p.setGiaMoiGio(rs.getDouble("gia_moi_gio"));
        return p;
    }
}
