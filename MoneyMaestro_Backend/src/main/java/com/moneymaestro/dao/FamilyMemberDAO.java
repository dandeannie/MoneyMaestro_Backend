package com.moneymaestro.dao;

import com.moneymaestro.model.FamilyMember;
import com.moneymaestro.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyMemberDAO {
    public void addFamilyMember(FamilyMember familyMember) throws Exception {
        String sql = "INSERT INTO Family_Members (family_id, user_id, role) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, familyMember.getFamilyId());
            pstmt.setInt(2, familyMember.getUserId());
            pstmt.setString(3, familyMember.getRole());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    familyMember.setFamilyMemberId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public FamilyMember getFamilyMemberById(int familyMemberId) throws Exception {
        String sql = "SELECT * FROM Family_Members WHERE family_member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, familyMemberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    FamilyMember familyMember = new FamilyMember();
                    familyMember.setFamilyMemberId(rs.getInt("family_member_id"));
                    familyMember.setFamilyId(rs.getInt("family_id"));
                    familyMember.setUserId(rs.getInt("user_id"));
                    familyMember.setRole(rs.getString("role"));
                    return familyMember;
                }
            }
        }
        return null;
    }

    public List<FamilyMember> getFamilyMembersByFamilyId(int familyId) throws Exception {
        List<FamilyMember> familyMembers = new ArrayList<>();
        String sql = "SELECT * FROM Family_Members WHERE family_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, familyId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FamilyMember familyMember = new FamilyMember();
                    familyMember.setFamilyMemberId(rs.getInt("family_member_id"));
                    familyMember.setFamilyId(rs.getInt("family_id"));
                    familyMember.setUserId(rs.getInt("user_id"));
                    familyMember.setRole(rs.getString("role"));
                    familyMembers.add(familyMember);
                }
            }
        }
        return familyMembers;
    }

    public void updateFamilyMember(FamilyMember familyMember) throws Exception {
        String sql = "UPDATE Family_Members SET family_id = ?, user_id = ?, role = ? WHERE family_member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, familyMember.getFamilyId());
            pstmt.setInt(2, familyMember.getUserId());
            pstmt.setString(3, familyMember.getRole());
            pstmt.setInt(4, familyMember.getFamilyMemberId());
            pstmt.executeUpdate();
        }
    }

    public void deleteFamilyMember(int familyMemberId) throws Exception {
        String sql = "DELETE FROM Family_Members WHERE family_member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, familyMemberId);
            pstmt.executeUpdate();
        }
    }

    public List<FamilyMember> getAllFamilyMembers() {
        return null;
    }

    public FamilyMember getFamilyMember(int id) {
        return null;
    }
}