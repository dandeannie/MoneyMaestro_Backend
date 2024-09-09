package com.moneymaestro.dao;

import com.moneymaestro.model.Transaction;
import com.moneymaestro.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public void addTransaction(Transaction transaction) throws Exception {
        String sql = "INSERT INTO Transactions (user_id, category_id, amount, description, date, type, family_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, transaction.getUserId());
            pstmt.setInt(2, transaction.getCategoryId());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getDescription());
            pstmt.setDate(5, new java.sql.Date(transaction.getDate().getTime()));
            pstmt.setString(6, transaction.getType());
            pstmt.setInt(7, transaction.getFamilyId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransactionId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Transaction> getAllTransactions() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                transactions.add(extractTransactionFromResultSet(rs));
            }
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByUserId(int userId) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(extractTransactionFromResultSet(rs));
                }
            }
        }
        return transactions;
    }

    public void updateTransaction(Transaction transaction) throws Exception {
        String sql = "UPDATE Transactions SET user_id = ?, category_id = ?, amount = ?, description = ?, date = ?, type = ?, family_id = ? WHERE transaction_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getUserId());
            pstmt.setInt(2, transaction.getCategoryId());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getDescription());
            pstmt.setDate(5, new java.sql.Date(transaction.getDate().getTime()));
            pstmt.setString(6, transaction.getType());
            pstmt.setInt(7, transaction.getFamilyId());
            pstmt.setInt(8, transaction.getTransactionId());
            pstmt.executeUpdate();
        }
    }

    public void deleteTransaction(int transactionId) throws Exception {
        String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            pstmt.executeUpdate();
        }
    }

    private Transaction extractTransactionFromResultSet(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rs.getInt("transaction_id"));
        transaction.setUserId(rs.getInt("user_id"));
        transaction.setCategoryId(rs.getInt("category_id"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setDescription(rs.getString("description"));
        transaction.setDate(rs.getDate("date"));
        transaction.setType(rs.getString("type"));
        transaction.setFamilyId(rs.getInt("family_id"));
        return transaction;
    }
}