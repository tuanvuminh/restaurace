package com;

import java.math.BigDecimal;
import java.util.List;

public class Dish {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private Category category;
    private String mainImage;
    private List<String> images;

    public Dish(String title, BigDecimal price, int preparationTime, Category category, String mainImage, List<String> images) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.category = category;
        this.mainImage = mainImage;
        this.images = images;
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

    public String exportToString() {
        return title + "\t" + price + "\t" + preparationTime + "\t" + mainImage + "\t" + images + "\t" + category;
    }

}
