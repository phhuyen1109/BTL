package com.mycompany.quanlyquaninternet.dao;

import java.sql.*;

public class RewardHistoryDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }

    /**
     * Ghi lại lịch sử đổi thưởng
     */
    public boolean addHistory(int customerId, int foodDrinkId, String foodDrinkName, int pointsUsed) {
        String sql = "INSERT INTO reward_history (customer_id, food_drink_id, food_drink_name, points_used) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, foodDrinkId);
            pstmt.setString(3, foodDrinkName);
            pstmt.setInt(4, pointsUsed);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
