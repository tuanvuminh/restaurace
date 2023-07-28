package com;

import com.exceptions.OrderException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private Category category;
    private String mainImage;
    private List<String> images = new ArrayList<>();
    private Boolean isInCookBook = false;
    private Boolean isOnMenu = false;

    public Dish(String title, BigDecimal price, int preparationTime, Category category, String mainImage) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.category = category;
        this.mainImage = mainImage;
    }

    public Dish(String title, BigDecimal price, int preparationTime, Category category) {
        this(title, price, preparationTime, category, "blank");
    }

    public void addImage(String mainImage) {
        this.images.add(mainImage);
    }

    public void removeImage(String mainImage) {
        this.images.remove(mainImage);
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

    public void setPreparationTime(int preparationTime) {
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

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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
