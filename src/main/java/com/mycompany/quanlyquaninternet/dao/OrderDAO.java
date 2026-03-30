package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.Order;
import com.mycompany.quanlyquaninternet.entity.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }

    /**
     * Tạo đơn hàng mới và trả về ID
     */
    public int createOrder(Order order) {
        String sql = "INSERT INTO orders (session_id, computer_id, total_price, order_time) VALUES (?, ?, ?, NOW())";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (order.getSessionId() != null) {
                pstmt.setInt(1, order.getSessionId());
            } else {
                pstmt.setNull(1, Types.INTEGER);
            }
            pstmt.setInt(2, order.getComputerId());
            pstmt.setDouble(3, order.getTotalPrice());
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int orderId = keys.getInt(1);
                order.setId(orderId);
                // Thêm các items
                for (OrderItem item : order.getItems()) {
                    item.setOrderId(orderId);
                    addOrderItem(item);
                }
                return orderId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean addOrderItem(OrderItem item) {
        String sql = "INSERT INTO order_items (order_id, food_drink_id, food_drink_name, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getFoodDrinkId());
            pstmt.setString(3, item.getFoodDrinkName());
            pstmt.setInt(4, item.getQuantity());
            pstmt.setDouble(5, item.getPrice());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Lấy tất cả đơn hàng theo session
     */
    public List<Order> getBySessionId(int sessionId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE session_id = ? ORDER BY order_time";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = mapOrder(rs);
                order.setItems(getOrderItems(order.getId()));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<OrderItem> getOrderItems(int orderId) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setFoodDrinkId(rs.getInt("food_drink_id"));
                item.setFoodDrinkName(rs.getString("food_drink_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Tổng tiền dịch vụ theo session
     */
    public double getOrderTotalBySession(int sessionId) {
        String sql = "SELECT COALESCE(SUM(total_price), 0) FROM orders WHERE session_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Doanh thu dịch vụ hôm nay
     */
    public double getTodayOrderRevenue() {
        String sql = "SELECT COALESCE(SUM(total_price), 0) FROM orders WHERE DATE(order_time) = CURDATE()";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Order mapOrder(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getInt("id"));
        int sessId = rs.getInt("session_id");
        o.setSessionId(rs.wasNull() ? null : sessId);
        o.setComputerId(rs.getInt("computer_id"));
        o.setTotalPrice(rs.getDouble("total_price"));
        o.setOrderTime(rs.getTimestamp("order_time"));
        return o;
    }
}
