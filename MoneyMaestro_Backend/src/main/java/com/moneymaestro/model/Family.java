package com.moneymaestro.model;

import java.util.Date;

public class Family {
    private int familyId;
    private String familyName;
    private Date createdAt;

    // Constructors
    public Family() {
    }

    public Family(String familyName) {
        this.familyName = familyName;
    }

    // Getters and setters
    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static class Category {
        private int categoryId;
        private String name;
        private String type;
        private String icon;
        private int createdBy;

        // Constructors
        public Category() {
        }

        public Category(String name, String type, String icon, int createdBy) {
            this.name = name;
            this.type = type;
            this.icon = icon;
            this.createdBy = createdBy;
        }

        // Getters and setters
        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(int createdBy) {
            this.createdBy = createdBy;
        }
    }
}