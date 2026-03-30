package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.Session;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }

    public List<Session> getAll() {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT s.*, c.name as computer_name, c.price_per_hour " +
                     "FROM sessions s JOIN computers c ON s.computer_id = c.id ORDER BY s.id DESC";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Session> getActiveSessions() {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT s.*, c.name as computer_name, c.price_per_hour " +
                     "FROM sessions s JOIN computers c ON s.computer_id = c.id " +
                     "WHERE s.status = 'Đang chạy' ORDER BY s.start_time DESC";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Session getById(int id) {
        String sql = "SELECT s.*, c.name as computer_name, c.price_per_hour " +
                     "FROM sessions s JOIN computers c ON s.computer_id = c.id WHERE s.id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapSession(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Session getActiveByComputerId(int computerId) {
        String sql = "SELECT s.*, c.name as computer_name, c.price_per_hour " +
                     "FROM sessions s JOIN computers c ON s.computer_id = c.id " +
                     "WHERE s.computer_id = ? AND s.status = 'Đang chạy' LIMIT 1";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, computerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapSession(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int startSession(Session session) {
        String sql = "INSERT INTO sessions (computer_id, customer_id, customer_name, start_time, status) VALUES (?, ?, ?, ?, 'Đang chạy')";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, session.getComputerId());
            if (session.getCustomerId() != null) {
                pstmt.setInt(2, session.getCustomerId());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setString(3, session.getCustomerName());
            pstmt.setTimestamp(4, new Timestamp(session.getStartTime().getTime()));
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean endSession(int sessionId, double totalCost) {
        String sql = "UPDATE sessions SET end_time = NOW(), total_cost = ?, status = 'Kết thúc' WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDouble(1, totalCost);
            pstmt.setInt(2, sessionId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Session> getSessionsByDate(Date date) {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT s.*, c.name as computer_name, c.price_per_hour " +
                     "FROM sessions s JOIN computers c ON s.computer_id = c.id " +
                     "WHERE DATE(s.start_time) = DATE(?) ORDER BY s.start_time DESC";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public double getRevenueByDate(Date date) {
        String sql = "SELECT COALESCE(SUM(total_cost), 0) FROM sessions WHERE DATE(start_time) = DATE(?) AND status = 'Kết thúc'";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getRevenueBetweenDates(Date from, Date to) {
        String sql = "SELECT COALESCE(SUM(total_cost), 0) FROM sessions WHERE DATE(start_time) BETWEEN DATE(?) AND DATE(?) AND status = 'Kết thúc'";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(from.getTime()));
            pstmt.setDate(2, new java.sql.Date(to.getTime()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Doanh thu theo từng ngày trong khoảng thời gian
     */
    public List<Object[]> getDailyRevenue(Date from, Date to) {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT DATE(start_time) as day, COALESCE(SUM(total_cost), 0) as revenue, COUNT(*) as sessions " +
                     "FROM sessions WHERE DATE(start_time) BETWEEN DATE(?) AND DATE(?) AND status = 'Kết thúc' " +
                     "GROUP BY DATE(start_time) ORDER BY day";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(from.getTime()));
            pstmt.setDate(2, new java.sql.Date(to.getTime()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getDate("day"),
                    rs.getDouble("revenue"),
                    rs.getInt("sessions")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countTodaySessions() {
        String sql = "SELECT COUNT(*) FROM sessions WHERE DATE(start_time) = CURDATE()";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTodayRevenue() {
        return getRevenueByDate(new Date());
    }

    private Session mapSession(ResultSet rs) throws SQLException {
        Session s = new Session();
        s.setId(rs.getInt("id"));
        s.setComputerId(rs.getInt("computer_id"));
        int custId = rs.getInt("customer_id");
        s.setCustomerId(rs.wasNull() ? null : custId);
        s.setCustomerName(rs.getString("customer_name"));
        s.setStartTime(rs.getTimestamp("start_time"));
        Timestamp endTs = rs.getTimestamp("end_time");
        s.setEndTime(endTs);
        s.setTotalCost(rs.getDouble("total_cost"));
        s.setStatus(rs.getString("status"));
        s.setComputerName(rs.getString("computer_name"));
        s.setPricePerHour(rs.getDouble("price_per_hour"));
        return s;
    }
}
