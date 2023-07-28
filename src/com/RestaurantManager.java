package com;

import com.comparators.OrderTimeComparator;
import com.exceptions.OrderException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class RestaurantManager {
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        if (order.getDish().getOnMenu()) {
            orderList.add(order);
        } else {
            System.err.println(order.getDish() + " Jídlo není v nabídce menu!");
        }
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orderList);
    }

    public void setOrders(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void saveToFile(String fileName, String delimiter) throws OrderException {
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orderList) {
                outputWriter.println(order.exportOrder());
            }
        } catch (IOException e) {
            throw new OrderException("Nepodařilo se nahrát data do souboru: " + fileName);
        }
    }

    // 1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
    public String getNumberOfUncompletedOrders() {
        int numberOfUncompletedOrders = 0;
        for (Order order : orderList) {
            if (order.getFulfilmentTime() == null) {
                numberOfUncompletedOrders++;
            }
        }
        return "Počet rozpracovaných/nedokončených objednávek: " + numberOfUncompletedOrders;
    }

    // 2. Možnost seřadit objednávky podle číšníka nebo času zadání.

    public void sortByTimeOfOrder() {
        for (Order order : orderList) {
            Collections.sort(orderList, new OrderTimeComparator());
        }
    }

    // 3. Celková cena objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).




}
