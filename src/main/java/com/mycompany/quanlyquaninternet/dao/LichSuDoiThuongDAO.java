package com.mycompany.quanlyquaninternet.dao;

import java.sql.*;

// Lớp ghi lại lịch sử đổi thưởng
public class LichSuDoiThuongDAO {

    private Connection layKetNoi() throws SQLException {
        return KetNoiCSDL.layThucThe().layKetNoi();
    }

    // Ghi lại lịch sử đổi thưởng
    public boolean ghiLichSu(int maKhachHang, int maDoAn, String tenDoAn, int diemDaDung) {
        String sql = "INSERT INTO lich_su_doi_thuong (ma_khach_hang, ma_do_an, ten_do_an, diem_da_dung) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, maKhachHang);
            pstmt.setInt(2, maDoAn);
            pstmt.setString(3, tenDoAn);
            pstmt.setInt(4, diemDaDung);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
