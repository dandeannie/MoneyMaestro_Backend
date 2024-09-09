package com.moneymaestro.dao;

import com.moneymaestro.model.Budget;
import com.moneymaestro.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetDAO {
    public void addBudget(Budget budget) throws Exception {
        String sql = "INSERT INTO Budgets (user_id, category_id, amount, start_date, end_date, family_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, budget.getUserId());
                pstmt.setInt(2, budget.getCategoryId());
                pstmt.setDouble(3, budget.getAmount());
                pstmt.setDate(4, new Date(budget.getStartDate().getTime()));
                pstmt.setDate(5, new Date(budget.getEndDate().getTime()));
                pstmt.setInt(6, budget.getFamilyId());
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        budget.setBudgetId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    public Budget getBudgetById(int budgetId) throws Exception {
        String sql = "SELECT * FROM Budgets WHERE budget_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, budgetId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractBudgetFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Budget> getAllBudgets() throws Exception {
        List<Budget> budgets = new ArrayList<>();
        String sql = "SELECT * FROM Budgets";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                budgets.add(extractBudgetFromResultSet(rs));
            }
        }
        return budgets;
    }

    public List<Budget> getBudgetsByUserId(int userId) throws Exception {
        List<Budget> budgets = new ArrayList<>();
        String sql = "SELECT * FROM Budgets WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    budgets.add(extractBudgetFromResultSet(rs));
                }
            }
        }
        return budgets;
    }

    public void updateBudget(Budget budget) throws Exception {
        String sql = "UPDATE Budgets SET user_id = ?, category_id = ?, amount = ?, start_date = ?, end_date = ?, family_id = ? WHERE budget_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, budget.getUserId());
            pstmt.setInt(2, budget.getCategoryId());
            pstmt.setDouble(3, budget.getAmount());
            pstmt.setDate(4, new java.sql.Date(budget.getStartDate().getTime()));
            pstmt.setDate(5, new java.sql.Date(budget.getEndDate().getTime()));
            pstmt.setInt(6, budget.getFamilyId());
            pstmt.setInt(7, budget.getBudgetId());
            pstmt.executeUpdate();
        }
    }

    public void deleteBudget(int budgetId) throws Exception {
        String sql = "DELETE FROM Budgets WHERE budget_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, budgetId);
            pstmt.executeUpdate();
        }
    }

    private Budget extractBudgetFromResultSet(ResultSet rs) throws SQLException {
        Budget budget = new Budget();
        budget.setBudgetId(rs.getInt("budget_id"));
        budget.setUserId(rs.getInt("user_id"));
        budget.setCategoryId(rs.getInt("category_id"));
        budget.setAmount(rs.getDouble("amount"));
        budget.setStartDate(rs.getDate("start_date"));
        budget.setEndDate(rs.getDate("end_date"));
        budget.setFamilyId(rs.getInt("family_id"));
        return budget;
    }
}