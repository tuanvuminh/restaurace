package com;

public enum Category {
    STARTER ("předkrm"), MAIN ("hlavní jídlo"), DESSERT ("dezert"),
    ALCOHOL ("alkoholické pití"), NONALCOHOLIC ("nealkoholické pití");
    private String description;

    Category(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
