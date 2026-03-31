package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.MayTinh;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Lớp truy xuất dữ liệu máy tính
public class MayTinhDAO {

    private Connection layKetNoi() throws SQLException {
        return KetNoiCSDL.layThucThe().layKetNoi();
    }

    public List<MayTinh> layTatCa() {
        List<MayTinh> ds = new ArrayList<>();
        String sql = "SELECT * FROM may_tinh ORDER BY ma";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public MayTinh layTheoMa(int ma) {
        String sql = "SELECT * FROM may_tinh WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ma);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return chuyenDoi(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<MayTinh> layTheoTrangThai(String trangThai) {
        List<MayTinh> ds = new ArrayList<>();
        String sql = "SELECT * FROM may_tinh WHERE trang_thai = ? ORDER BY ma";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, trangThai);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public boolean them(MayTinh mt) {
        String sql = "INSERT INTO may_tinh (ten, loai, gia_moi_gio, trang_thai, cau_hinh) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, mt.getTen());
            pstmt.setString(2, mt.getLoai());
            pstmt.setDouble(3, mt.getGiaMoiGio());
            pstmt.setString(4, mt.getTrangThai());
            pstmt.setString(5, mt.getCauHinh());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean capNhat(MayTinh mt) {
        String sql = "UPDATE may_tinh SET ten=?, loai=?, gia_moi_gio=?, trang_thai=?, cau_hinh=? WHERE ma=?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, mt.getTen());
            pstmt.setString(2, mt.getLoai());
            pstmt.setDouble(3, mt.getGiaMoiGio());
            pstmt.setString(4, mt.getTrangThai());
            pstmt.setString(5, mt.getCauHinh());
            pstmt.setInt(6, mt.getMa());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean capNhatTrangThai(int ma, String trangThai) {
        String sql = "UPDATE may_tinh SET trang_thai=? WHERE ma=?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, trangThai);
            pstmt.setInt(2, ma);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean xoa(int ma) {
        String sql = "DELETE FROM may_tinh WHERE ma=?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ma);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public int demTheoTrangThai(String trangThai) {
        String sql = "SELECT COUNT(*) FROM may_tinh WHERE trang_thai = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, trangThai);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int demTatCa() {
        String sql = "SELECT COUNT(*) FROM may_tinh";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private MayTinh chuyenDoi(ResultSet rs) throws SQLException {
        MayTinh mt = new MayTinh();
        mt.setMa(rs.getInt("ma"));
        mt.setTen(rs.getString("ten"));
        mt.setLoai(rs.getString("loai"));
        mt.setGiaMoiGio(rs.getDouble("gia_moi_gio"));
        mt.setTrangThai(rs.getString("trang_thai"));
        mt.setCauHinh(rs.getString("cau_hinh"));
        return mt;
    }
}
