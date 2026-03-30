package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.Computer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }

    public List<Computer> getAll() {
        List<Computer> list = new ArrayList<>();
        String sql = "SELECT * FROM computers ORDER BY id";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapComputer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Computer getById(int id) {
        String sql = "SELECT * FROM computers WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapComputer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Computer> getByStatus(String status) {
        List<Computer> list = new ArrayList<>();
        String sql = "SELECT * FROM computers WHERE status = ? ORDER BY id";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapComputer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(Computer computer) {
        String sql = "INSERT INTO computers (name, type, price_per_hour, status, specs) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, computer.getName());
            pstmt.setString(2, computer.getType());
            pstmt.setDouble(3, computer.getPricePerHour());
            pstmt.setString(4, computer.getStatus());
            pstmt.setString(5, computer.getSpecs());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Computer computer) {
        String sql = "UPDATE computers SET name=?, type=?, price_per_hour=?, status=?, specs=? WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, computer.getName());
            pstmt.setString(2, computer.getType());
            pstmt.setDouble(3, computer.getPricePerHour());
            pstmt.setString(4, computer.getStatus());
            pstmt.setString(5, computer.getSpecs());
            pstmt.setInt(6, computer.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE computers SET status=? WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM computers WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM computers WHERE status = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM computers";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Computer mapComputer(ResultSet rs) throws SQLException {
        Computer c = new Computer();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setType(rs.getString("type"));
        c.setPricePerHour(rs.getDouble("price_per_hour"));
        c.setStatus(rs.getString("status"));
        c.setSpecs(rs.getString("specs"));
        return c;
    }
}
