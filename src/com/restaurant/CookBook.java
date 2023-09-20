package com.restaurant;

import com.restaurant.enums.Category;
import com.restaurant.exception.OrderException;
import com.restaurant.model.Dish;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CookBook {

    private List<Dish> cookBook = new ArrayList<>();

    public void addDish(Dish dish) {
        cookBook.add(dish);
        dish.setInCookBook(true);
    }

    public void addDish(Dish... dishes) {
        for (Dish dish : dishes) {
            cookBook.add(dish);
            dish.setInCookBook(true);
        }
    }

    public void removeDish(Dish dish) {
        cookBook.remove(dish);
    }

    public void removeAll() {
        cookBook.clear();
    }

    public void getDish(int index) {
        cookBook.get(index);
    }

    public void loadFromFile(String filename, String delimiter) throws OrderException {
        String[] items = new String[0];
        String line = "";
        int lineNumber = 1;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                items = line.split(delimiter);
                if (items.length != 6)
                    throw new OrderException("Špatný počet položek na řádku: " + lineNumber + " " + line);
                String title = items[0];
                BigDecimal price = new BigDecimal(items[1]);
                Integer preparationTime = Integer.valueOf(items[2]);
                Category category = Category.valueOf(items[3]);
                String mainImage = items[4];
                List images = List.of(items[5]);
                Dish newDish = new Dish(title, price, preparationTime, category, mainImage, images);
                cookBook.add(newDish);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new OrderException("Soubor: " + filename + " nebyl nalezen! " + e.getLocalizedMessage());
        }
    }

    public void saveToFile(String filename, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : cookBook) {
                writer.println(
                        dish.getTitle() + delimiter
                                + dish.getPrice() + delimiter
                                + dish.getPreparationTime() + delimiter
                                + dish.getCategory() + delimiter
                                + dish.getMainImage() + delimiter
                                + dish.getImages() + delimiter
                );
            }
        } catch (IOException e) {
            throw new OrderException("Chyba při zápisu do souboru :" + filename + ": " + e.getLocalizedMessage());
        }
    }

    public void printCookbook() {
        System.out.println("Zásobník jídel");
        for (Dish dish : cookBook) {
            System.out.println(dish.getMainImage() + " " + dish.getImages() + " " + dish.getCategory().getDescription() + ": " + dish.getTitle() + ", čas přípravy: "
                    + dish.getPreparationTime() + " minut");
        }
    }
}


