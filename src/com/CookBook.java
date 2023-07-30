package com;

import com.exceptions.OrderException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CookBook extends Menu{
    private List <Dish> dishes = new ArrayList<>();

    public void addDish(Dish dish) {
        dishes.add(dish);
        dish.setInCookBook(true);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public void removeAll() {
        dishes.removeAll(dishes);
    }

    public void getDish(int index) {
        dishes.get(index);
    }

    public void saveToFile(String filename, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : dishes) {
                writer.println(
                        dish.getMainImage()+ " "
                                +dish.getCategory().toString()+ ": "
                                +dish.getTitle()+ ", čas přípravy: "
                                +dish.getPreparationTime()+ " minut"
                );
            }
        } catch (IOException e) {
            throw new OrderException("Chyba zápisu do souboru :" + filename + ": " + e.getLocalizedMessage());
        }
    }

    public void printCookbook () {
        System.out.println("Zásobník jídel");
        for (Dish dish : dishes) {
            System.out.println(dish.getMainImage()+ " " +dish.getCategory().toString()+ ": " +dish.getTitle()+ ", čas přípravy: "
                    +dish.getPreparationTime()+ " minut");
        }
    }
}
