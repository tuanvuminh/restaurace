package com;

import com.exceptions.OrderException;
import com.settings.Settings;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CookBook extends Menu {
    private List<Dish> dishes = new ArrayList<>();

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

    public void loadFromFile(String filename, String delimiter) throws OrderException {
        String[] items = new String[0];
        String line = "";
        int lineNumber = 1;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                items = line.split(delimiter);
                if (items.length != 5)
                    throw new OrderException("Špatný počet položek na řádku: " + lineNumber + " " + line);
                String title = items[0];
                BigDecimal price = new BigDecimal(items[1]);
                int preparationTime = Integer.parseInt(items[2]);
                Category category = Category.valueOf(items[3]);
                String mainImage = items[4];
                if (mainImage.isEmpty()) mainImage = "blank";
                Dish newDish = new Dish(title, price, preparationTime, category, mainImage);
                dishes.add(newDish);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new OrderException("Soubor: " + filename + " nebyl nalezen! " + e.getLocalizedMessage());
        }
    }

    public void saveToFile(String filename, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : dishes) {
                writer.println(
                        dish.getTitle() + delimiter
                        + dish.getPrice() + delimiter
                        + dish.getPreparationTime() + delimiter
                        + dish.getCategory() +delimiter
                        + dish.getMainImage() +delimiter
                );
            }
        } catch (IOException e) {
            throw new OrderException("Chyba při zápisu do souboru :" + filename + ": " + e.getLocalizedMessage());
        }
    }

    public void printCookbook() {
        System.out.println("Zásobník jídel");
        for (Dish dish : dishes) {
            System.out.println(dish.getMainImage() + " " + dish.getCategory().toString() + ": " + dish.getTitle() + ", čas přípravy: "
                    + dish.getPreparationTime() + " minut");
        }
    }
}
