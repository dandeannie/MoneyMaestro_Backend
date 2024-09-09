package com.moneymaestro.dao;

import com.moneymaestro.model.Family;
import com.moneymaestro.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyDAO {
    public void addFamily(Family family) throws Exception {
        String sql = "INSERT INTO Families (family_name) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, family.getFamilyName());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    family.setFamilyId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Family getFamilyById(int familyId) throws Exception {
        String sql = "SELECT * FROM Families WHERE family_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, familyId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Family family = new Family();
                    family.setFamilyId(rs.getInt("family_id"));
                    family.setFamilyName(rs.getString("family_name"));
                    return family;
                }
            }
        }
        return null;
    }

    public List<Family> getAllFamilies() throws Exception {
        List<Family> families = new ArrayList<>();
        String sql = "SELECT * FROM Families";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Family family = new Family();
                family.setFamilyId(rs.getInt("family_id"));
                family.setFamilyName(rs.getString("family_name"));
                families.add(family);
            }
        }
        return families;
    }

    public void updateFamily(Family family) throws Exception {
        String sql = "UPDATE Families SET family_name = ? WHERE family_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, family.getFamilyName());
            pstmt.setInt(2, family.getFamilyId());
            pstmt.executeUpdate();
        }
    }

    public void deleteFamily(int familyId) throws Exception {
        String sql = "DELETE FROM Families WHERE family_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, familyId);
            pstmt.executeUpdate();
        }
    }

    public Family getFamily(int id) {
        return null;
    }
}