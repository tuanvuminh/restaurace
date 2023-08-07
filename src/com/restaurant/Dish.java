package com.restaurant;

import com.restaurant.exceptions.OrderException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String title;
    private BigDecimal price;
    private Integer preparationTime;
    private Category category;
    private String mainImage;
    private List<String> images = new ArrayList<>();
    private Boolean isInCookBook = false;
    private Boolean isOnMenu = false;

    public Dish(String title) {
        this.title = title;
    }

    public Dish(String title, BigDecimal price, Integer preparationTime, Category category, String mainImage, List<String> images) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.category = category;
        this.mainImage = mainImage;
        this.images = images;
    }

    public Dish(String title, BigDecimal price, Integer preparationTime, Category category, String mainImage) throws OrderException  {
        this.title = title;
        this.price = price;
        this.setPreparationTime(preparationTime);
        this.category = category;
        this.mainImage = mainImage;
    }

    public Dish(String title, BigDecimal price, Integer preparationTime, Category category) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.category = category;
        this.mainImage = "blank";
    }

    // Metody přidání a smazáni obrázků

    public void removeMainImage() {
        this.mainImage = "blank";
    }

    public void addImageToList(String image) {
        images.add(image);
    }

    public void removeImageFromList(String image) {
        images.remove(image);
    }

    // Gettery a settery

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

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
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
