package com.mycompany.quanlyquaninternet.dao;

import com.mycompany.quanlyquaninternet.entity.FoodDrink;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDrinkDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }

    public List<FoodDrink> getAll() {
        List<FoodDrink> list = new ArrayList<>();
        String sql = "SELECT * FROM food_drinks ORDER BY category, name";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapFoodDrink(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<FoodDrink> getAvailable() {
        List<FoodDrink> list = new ArrayList<>();
        String sql = "SELECT * FROM food_drinks WHERE available = true ORDER BY category, name";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapFoodDrink(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<FoodDrink> getByCategory(String category) {
        List<FoodDrink> list = new ArrayList<>();
        String sql = "SELECT * FROM food_drinks WHERE category = ? ORDER BY name";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapFoodDrink(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public FoodDrink getById(int id) {
        String sql = "SELECT * FROM food_drinks WHERE id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapFoodDrink(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(FoodDrink fd) {
        String sql = "INSERT INTO food_drinks (name, price, category, available) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, fd.getName());
            pstmt.setDouble(2, fd.getPrice());
            pstmt.setString(3, fd.getCategory());
            pstmt.setBoolean(4, fd.isAvailable());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(FoodDrink fd) {
        String sql = "UPDATE food_drinks SET name=?, price=?, category=?, available=? WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, fd.getName());
            pstmt.setDouble(2, fd.getPrice());
            pstmt.setString(3, fd.getCategory());
            pstmt.setBoolean(4, fd.isAvailable());
            pstmt.setInt(5, fd.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM food_drinks WHERE id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private FoodDrink mapFoodDrink(ResultSet rs) throws SQLException {
        FoodDrink fd = new FoodDrink();
        fd.setId(rs.getInt("id"));
        fd.setName(rs.getString("name"));
        fd.setPrice(rs.getDouble("price"));
        fd.setCategory(rs.getString("category"));
        fd.setPointsCost(rs.getInt("points_cost"));
        fd.setAvailable(rs.getBoolean("available"));
        return fd;
    }
}
