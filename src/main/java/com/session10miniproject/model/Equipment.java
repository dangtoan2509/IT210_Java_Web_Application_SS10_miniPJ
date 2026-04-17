package com.session10miniproject.model;

public class Equipment {
    private String id;
    private String name;
    private String type; // e.g., LAB, DEVICE
    private int totalQuantity;
    private int availableQuantity;
    private String imageUrl;

    public Equipment() {
    }

    public Equipment(String id, String name, String type, int totalQuantity, int availableQuantity, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

