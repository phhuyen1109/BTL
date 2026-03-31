package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.KhachHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Lớp truy xuất dữ liệu khách hàng
public class KhachHangDAO {

    private Connection layKetNoi() throws SQLException {
        return KetNoiCSDL.layThucThe().layKetNoi();
    }

    public List<KhachHang> layTatCa() {
        List<KhachHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang ORDER BY ma";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public KhachHang layTheoMa(int ma) {
        String sql = "SELECT * FROM khach_hang WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ma);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return chuyenDoi(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<KhachHang> timKiem(String tuKhoa) {
        List<KhachHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang WHERE ten LIKE ? OR sdt LIKE ? ORDER BY ma";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            String like = "%" + tuKhoa + "%";
            pstmt.setString(1, like);
            pstmt.setString(2, like);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { ds.add(chuyenDoi(rs)); }
        } catch (SQLException e) { e.printStackTrace(); }
        return ds;
    }

    public boolean them(KhachHang kh) {
        String sql = "INSERT INTO khach_hang (ten, sdt, so_du, tong_gio, diem) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, kh.getTen());
            pstmt.setString(2, kh.getSdt());
            pstmt.setDouble(3, kh.getSoDu());
            pstmt.setDouble(4, kh.getTongGio());
            pstmt.setInt(5, kh.getDiem());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) { kh.setMa(keys.getInt(1)); }
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean capNhat(KhachHang kh) {
        String sql = "UPDATE khach_hang SET ten=?, sdt=?, so_du=?, tong_gio=?, diem=? WHERE ma=?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setString(1, kh.getTen());
            pstmt.setString(2, kh.getSdt());
            pstmt.setDouble(3, kh.getSoDu());
            pstmt.setDouble(4, kh.getTongGio());
            pstmt.setInt(5, kh.getDiem());
            pstmt.setInt(6, kh.getMa());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean xoa(int ma) {
        String sql = "DELETE FROM khach_hang WHERE ma=?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, ma);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Nạp tiền cho khách hàng
    public boolean napTien(int maKH, double soTien) {
        String sql = "UPDATE khach_hang SET so_du = so_du + ? WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDouble(1, soTien);
            pstmt.setInt(2, maKH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Trừ tiền từ tài khoản
    public boolean truTien(int maKH, double soTien) {
        String sql = "UPDATE khach_hang SET so_du = so_du - ? WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDouble(1, soTien);
            pstmt.setInt(2, maKH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Cộng giờ sử dụng
    public boolean congGio(int maKH, double soGio) {
        String sql = "UPDATE khach_hang SET tong_gio = tong_gio + ? WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setDouble(1, soGio);
            pstmt.setInt(2, maKH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Cộng điểm cho khách
    public boolean congDiem(int maKH, int soDiem) {
        String sql = "UPDATE khach_hang SET diem = diem + ? WHERE ma = ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, soDiem);
            pstmt.setInt(2, maKH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Trừ điểm khi đổi thưởng
    public boolean truDiem(int maKH, int soDiem) {
        String sql = "UPDATE khach_hang SET diem = diem - ? WHERE ma = ? AND diem >= ?";
        try (PreparedStatement pstmt = layKetNoi().prepareStatement(sql)) {
            pstmt.setInt(1, soDiem);
            pstmt.setInt(2, maKH);
            pstmt.setInt(3, soDiem);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // Nạp tiền + tự động cộng giờ + điểm (10,000đ/giờ, 1 giờ = 1 điểm)
    public boolean napTienVaCongGioDiem(int maKH, double soTien) {
        double soGio = soTien / 10000.0;
        int soDiem = (int) soGio;
        try {
            napTien(maKH, soTien);
            congGio(maKH, soGio);
            if (soDiem > 0) { congDiem(maKH, soDiem); }
            return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public int demTatCa() {
        String sql = "SELECT COUNT(*) FROM khach_hang";
        try (Statement stmt = layKetNoi().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private KhachHang chuyenDoi(ResultSet rs) throws SQLException {
        KhachHang kh = new KhachHang();
        kh.setMa(rs.getInt("ma"));
        kh.setTen(rs.getString("ten"));
        kh.setSdt(rs.getString("sdt"));
        kh.setSoDu(rs.getDouble("so_du"));
        kh.setTongGio(rs.getDouble("tong_gio"));
        kh.setDiem(rs.getInt("diem"));
        kh.setNgayTao(rs.getTimestamp("ngay_tao"));
        return kh;
    }
}
