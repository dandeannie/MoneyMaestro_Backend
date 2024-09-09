package com.moneymaestro.dao;

import com.moneymaestro.model.Category;
import com.moneymaestro.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private int id;

    public void addCategory(Category category) throws Exception {
        String sql = "INSERT INTO Categories (name, type, icon, created_by) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getType());
            pstmt.setString(3, category.getIcon());
            pstmt.setInt(4, category.getCreatedBy());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setCategoryId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Category getCategoryById(int categoryId) throws Exception {
        String sql = "SELECT * FROM Categories WHERE category_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setCategoryId(rs.getInt("category_id"));
                    category.setName(rs.getString("name"));
                    category.setType(rs.getString("type"));
                    category.setIcon(rs.getString("icon"));
                    category.setCreatedBy(rs.getInt("created_by"));
                    return category;
                }
            }
        }
        return null;
    }

    public List<Category> getAllCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categories";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setType(rs.getString("type"));
                category.setIcon(rs.getString("icon"));
                category.setCreatedBy(rs.getInt("created_by"));
                categories.add(category);
            }
        }
        return categories;
    }

    public void updateCategory(Category category) throws Exception {
        String sql = "UPDATE Categories SET name = ?, type = ?, icon = ? WHERE category_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getType());
            pstmt.setString(3, category.getIcon());
            pstmt.setInt(4, category.getCategoryId());
            pstmt.executeUpdate();
        }
    }

    public void deleteCategory(int categoryId) throws Exception {
        String sql = "DELETE FROM Categories WHERE category_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            pstmt.executeUpdate();
        }
    }

    public Category getCategory(int id) {
        this.id = id;
        return null;
    }
}