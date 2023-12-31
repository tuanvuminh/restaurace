package com.engeto.restaurant;

import com.engeto.restaurant.exception.OrderException;
import com.engeto.restaurant.model.Dish;
import com.engeto.restaurant.enums.Category;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    List<Dish> menu = new ArrayList<>();

    public List<Dish> getDishes() {
        return new ArrayList<>(menu);
    }

    public void getDish(int index) {
        menu.get(index);
    }

    public void setDishes(List<Dish> dishList) {
        this.menu = dishList;
    }

    public void removeDish(Dish dish) {
        menu.remove(dish);
    }

    public void removeAllDishes() {
        menu.clear();
    }

    public void addDish(Dish dish) {
        if (dish.getInCookBook()) {
            menu.add(dish);
            dish.setOnMenu(true);
        } else {
            System.err.println("\"" + dish + "\" nelze zařadit do menu, protože není v zásobníku receptů!");
        }
    }

    public void addDish(Dish... dishes) {
        for (Dish dish : dishes) {
            if (dish.getInCookBook()) {
                menu.add(dish);
                dish.setOnMenu(true);
            } else {
                System.err.println("\"" + dish + "\" nelze zařadit do menu, protože není v zásobníku receptů!");
            }
        }
    }

    public void saveToFile(String filename, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : menu) {
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
                menu.add(newDish);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new OrderException("Soubor: " + filename + " nebyl nalezen! " + e.getLocalizedMessage());
        }
    }

    public void printMenu() {
        System.out.println("Menu");
        for (Dish dish : menu) {
            System.out.println("“" + dish.getCategory().getDescription() + "“ "
                    + dish.getTitle() + ", cena: " + dish.getPrice() + " Kč"
            );
        }
    }
}
