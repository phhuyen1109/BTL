package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.DoAnUong;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Lớp truy xuất dữ liệu đồ ăn uống
public class DoAnUongDAO {

    private Connection layKetNoi() throws SQLException {
        return KetNoiCSDL.layThucThe().layKetNoi();
    }

    public List<DoAnUong> layTatCa() {
        List<DoAnUong> ds = new ArrayList<>();
        String sql = "SELECT * FROM do_an_uong ORDER BY phan_loai, ten";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public List<DoAnUong> layConHang() {
        List<DoAnUong> ds = new ArrayList<>();
        String sql = "SELECT * FROM do_an_uong WHERE con_hang = true ORDER BY phan_loai, ten";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public List<DoAnUong> layTheoPhanLoai(String phanLoai) {
        List<DoAnUong> ds = new ArrayList<>();
        String sql = "SELECT * FROM do_an_uong WHERE phan_loai = ? ORDER BY ten";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, phanLoai);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public DoAnUong layTheoMa(int ma) {
        String sql = "SELECT * FROM do_an_uong WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ma);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return chuyenDoi(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean them(DoAnUong dau) {
        String sql = "INSERT INTO do_an_uong (ten, gia, phan_loai, con_hang) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, dau.getTen());
            pstmt.setDouble(2, dau.getGia());
            pstmt.setString(3, dau.getPhanLoai());
            pstmt.setBoolean(4, dau.isConHang());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean capNhat(DoAnUong dau) {
        String sql = "UPDATE do_an_uong SET ten=?, gia=?, phan_loai=?, con_hang=? WHERE ma=?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, dau.getTen());
            pstmt.setDouble(2, dau.getGia());
            pstmt.setString(3, dau.getPhanLoai());
            pstmt.setBoolean(4, dau.isConHang());
            pstmt.setInt(5, dau.getMa());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean xoa(int ma) {
        String sql = "DELETE FROM do_an_uong WHERE ma=?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ma);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private DoAnUong chuyenDoi(ResultSet rs) throws SQLException {
        DoAnUong dau = new DoAnUong();
        dau.setMa(rs.getInt("ma"));
        dau.setTen(rs.getString("ten"));
        dau.setGia(rs.getDouble("gia"));
        dau.setPhanLoai(rs.getString("phan_loai"));
        dau.setDiemDoi(rs.getInt("diem_doi"));
        dau.setConHang(rs.getBoolean("con_hang"));
        return dau;
    }
}
