package com;

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

    public void getCookbook () {
        System.out.println("Zásobník jídel");
        for (Dish dish : dishes) {
            System.out.println(dish.getMainImage()+ " " +dish.getCategory().toString()+ ": " +dish.getTitle()+ ", čas přípravy: "
                    +dish.getPreparationTime()+ " minut");
        }
    }
}
