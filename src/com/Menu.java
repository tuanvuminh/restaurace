package com;

import java.io.*;
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
        menu.removeAll(menu);
    }

    public void addDish(Dish dish) {
        if (dish.getInCookBook()) {
            menu.add(dish);
            dish.setOnMenu(true);
        } else {
            System.err.println("“"+ dish + "“ nelze zařadit do menu, protože není v zásobníku receptů!");
        }
    }

    public void saveToFile(String filename, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : menu) {
                writer.println(
                        dish.getTitle() + delimiter
                                + dish.getPrice() + delimiter
                                + dish.getPreparationTime() + delimiter
                                + dish.getMainImage() + delimiter
                                + dish.getCategory().toString()
                );
            }
        } catch (IOException e) {
            throw new OrderException("Chyba zápisu do souboru :" + filename + ": " + e.getLocalizedMessage());
        }
    }

    public void importFromFile(String fileName) throws OrderException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                menu.add(Dish.parseDish(line)); }
        } catch (FileNotFoundException e) {
            throw new OrderException("Soubor " + fileName + " nenalezen!");
        }
    }

    public void getMenu() {
        System.out.println("Menu");
        for (Dish dish : menu) {
            System.out.println("“"+dish.getCategory().toString()+"“ " + dish.getTitle()+ ", cena: " +dish.getPrice()+ " Kč");
        }
    }
}
