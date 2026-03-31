package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers ORDER BY id";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Customer getById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapCustomer(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> search(String keyword) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE name LIKE ? OR phone LIKE ? ORDER BY id";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            pstmt.setString(1, like);
            pstmt.setString(2, like);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(Customer customer) {
        String sql = "INSERT INTO customers (name, phone, balance, total_hours, points) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setDouble(3, customer.getBalance());
            pstmt.setDouble(4, customer.getTotalHours());
            pstmt.setInt(5, customer.getPoints());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) {
                    customer.setId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Customer customer) {
        String sql = "UPDATE customers SET name=?, phone=?, balance=?, total_hours=?, points=? WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setDouble(3, customer.getBalance());
            pstmt.setDouble(4, customer.getTotalHours());
            pstmt.setInt(5, customer.getPoints());
            pstmt.setInt(6, customer.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM customers WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Nạp tiền cho khách hàng
     */
    public boolean topUp(int customerId, double amount) {
        String sql = "UPDATE customers SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, customerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Trừ tiền từ tài khoản khách hàng
     */
    public boolean deductBalance(int customerId, double amount) {
        String sql = "UPDATE customers SET balance = balance - ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, customerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Cộng số giờ sử dụng
     */
    public boolean addHours(int customerId, double hours) {
        String sql = "UPDATE customers SET total_hours = total_hours + ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDouble(1, hours);
            pstmt.setInt(2, customerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Cộng điểm cho khách hàng
     */
    public boolean addPoints(int customerId, int points) {
        String sql = "UPDATE customers SET points = points + ? WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, points);
            pstmt.setInt(2, customerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Trừ điểm khi đổi thưởng
     */
    public boolean deductPoints(int customerId, int points) {
        String sql = "UPDATE customers SET points = points - ? WHERE id = ? AND points >= ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, points);
            pstmt.setInt(2, customerId);
            pstmt.setInt(3, points);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Nạp tiền + tự động cộng giờ chơi + tích điểm
     * Giá máy Thường = 10,000đ/giờ, 1 giờ = 1 điểm
     */
    public boolean topUpWithHoursAndPoints(int customerId, double amount) {
        double hours = amount / 10000.0;
        int points = (int) hours;
        try {
            topUp(customerId, amount);
            addHours(customerId, hours);
            if (points > 0) {
                addPoints(customerId, points);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countAll() {
        String sql = "SELECT COUNT(*) FROM customers";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setPhone(rs.getString("phone"));
        c.setBalance(rs.getDouble("balance"));
        c.setTotalHours(rs.getDouble("total_hours"));
        c.setPoints(rs.getInt("points"));
        c.setCreatedAt(rs.getTimestamp("created_at"));
        return c;
    }
}
