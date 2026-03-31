package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.DonHang;
import com.mycompany.quanlyquaninternet.entity.ChiTietDonHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Lớp truy xuất dữ liệu đơn hàng
public class DonHangDAO {

    private Connection layKetNoi() throws SQLException {
        return KetNoiCSDL.layThucThe().layKetNoi();
    }

    // Tạo đơn hàng mới và trả về mã
    public int taoDonHang(DonHang donHang) {
        String sql = "INSERT INTO don_hang (ma_phien, ma_may_tinh, tong_gia, thoi_gian_dat) VALUES (?, ?, ?, NOW())";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (donHang.getMaPhien() != null) {
                pstmt.setInt(1, donHang.getMaPhien());
            } else {
                pstmt.setNull(1, Types.INTEGER);
            }
            pstmt.setInt(2, donHang.getMaMayTinh());
            pstmt.setDouble(3, donHang.getTongGia());
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int maDH = keys.getInt(1);
                donHang.setMa(maDH);
                for (ChiTietDonHang ct : donHang.getDanhSachMon()) {
                    ct.setMaDonHang(maDH);
                    themChiTiet(ct);
                }
                return maDH;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public boolean themChiTiet(ChiTietDonHang ct) {
        String sql = "INSERT INTO chi_tiet_don_hang (ma_don_hang, ma_do_an, ten_do_an, so_luong, gia) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ct.getMaDonHang());
            pstmt.setInt(2, ct.getMaDoAn());
            pstmt.setString(3, ct.getTenDoAn());
            pstmt.setInt(4, ct.getSoLuong());
            pstmt.setDouble(5, ct.getGia());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<DonHang> layTheoPhien(int maPhien) {
        List<DonHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM don_hang WHERE ma_phien = ? ORDER BY thoi_gian_dat";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, maPhien);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DonHang dh = chuyenDoi(rs);
                dh.setDanhSachMon(layChiTiet(dh.getMa()));
                ds.add(dh);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public List<ChiTietDonHang> layChiTiet(int maDonHang) {
        List<ChiTietDonHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_don_hang WHERE ma_don_hang = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, maDonHang);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ChiTietDonHang ct = new ChiTietDonHang();
                ct.setMa(rs.getInt("ma"));
                ct.setMaDonHang(rs.getInt("ma_don_hang"));
                ct.setMaDoAn(rs.getInt("ma_do_an"));
                ct.setTenDoAn(rs.getString("ten_do_an"));
                ct.setSoLuong(rs.getInt("so_luong"));
                ct.setGia(rs.getDouble("gia"));
                ds.add(ct);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    // Tổng tiền dịch vụ theo phiên
    public double layTongTienDonHang(int maPhien) {
        String sql = "SELECT COALESCE(SUM(tong_gia), 0) FROM don_hang WHERE ma_phien = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, maPhien);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    // Doanh thu dịch vụ hôm nay
    public double layDoanhThuDVHomNay() {
        String sql = "SELECT COALESCE(SUM(tong_gia), 0) FROM don_hang WHERE DATE(thoi_gian_dat) = CURDATE()";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private DonHang chuyenDoi(ResultSet rs) throws SQLException {
        DonHang dh = new DonHang();
        dh.setMa(rs.getInt("ma"));
        int maP = rs.getInt("ma_phien");
        dh.setMaPhien(rs.wasNull() ? null : maP);
        dh.setMaMayTinh(rs.getInt("ma_may_tinh"));
        dh.setTongGia(rs.getDouble("tong_gia"));
        dh.setThoiGianDat(rs.getTimestamp("thoi_gian_dat"));
        return dh;
    }
}
