package com;

import java.util.ArrayList;
import java.util.List;

public class ListOfImages {
    private List<Image> listOfImages = new ArrayList<>();

    public void addImage (Image image) {
        listOfImages.add(image);
    }

    public void removeImage (Image image) {
        listOfImages.remove(image);
    }

    public String getImage (Image image) {
        return "" + image;
    }
}
