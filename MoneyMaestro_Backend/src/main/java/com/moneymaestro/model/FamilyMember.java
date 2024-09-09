package com.moneymaestro.model;

public class FamilyMember {
    private int familyMemberId;
    private int familyId;
    private int userId;
    private String role;

    // Constructors
    public FamilyMember() {
    }

    public FamilyMember(int familyId, int userId, String role) {
        this.familyId = familyId;
        this.userId = userId;
        this.role = role;
    }

    // Getters and setters
    public int getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(int familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
