package com.moneymaestro.model;

public class Category {
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
