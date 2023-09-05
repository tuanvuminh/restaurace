package com.restaurant;

public enum Category {

    STARTER ("předkrm"), MAIN ("hlavní jídlo"), DESSERT ("dezert");

    private String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
