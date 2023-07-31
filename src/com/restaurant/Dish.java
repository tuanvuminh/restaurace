package com.restaurant;

import com.restaurant.exceptions.OrderException;

import java.math.BigDecimal;

public class Dish {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private Category category;
    private String mainImage;
    private String otherImage;
    private Boolean isInCookBook = false;
    private Boolean isOnMenu = false;

    public Dish(String title, BigDecimal price, int preparationTime, Category category, String mainImage, String otherImage) throws OrderException {
        this.title = title;
        this.price = price;
        this.setPreparationTime(preparationTime);
        this.category = category;
        this.mainImage = mainImage;
        this.otherImage = otherImage;
    }

    public Dish(String title, BigDecimal price, int preparationTime, Category category, String mainImage) throws OrderException {
        this(title, price, preparationTime, category, mainImage, "blank");
    }

    public Dish(String title, BigDecimal price, int preparationTime, Category category) throws OrderException {
        this(title, price, preparationTime, category, "blank");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) throws OrderException {
        if (preparationTime <= 0) {
            throw new OrderException("Zadaná hodnota musí být kladné číslo (zadáno: " + preparationTime + ")");
        }
        this.preparationTime = preparationTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void addMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public void removeMainImage() {
        this.mainImage = "blank";
    }

    public void addOtherImage(String otherImage) {
        this.otherImage = otherImage;
    }

    public void removeOtherImage() {
        this.otherImage = "blank";
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getOtherImage() {
        return otherImage;
    }

    public void setOtherImage(String otherImage) {
        this.otherImage = otherImage;
    }

    public Boolean getInCookBook() {
        return isInCookBook;
    }

    public void setInCookBook(Boolean inCookBook) {
        isInCookBook = inCookBook;
    }

    public Boolean getOnMenu() {
        return isOnMenu;
    }

    public void setOnMenu(Boolean onMenu) {
        isOnMenu = onMenu;
    }

    @Override
    public String toString() {
        return title;
    }
}
