package com;

import com.comparators.OrderTimeComparator;
import com.exceptions.OrderException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public void exportToFile(String fileName) throws OrderException {
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orderList) {
                outputWriter.println(order.exportOrder());
            }
        } catch (IOException e) {
            throw new OrderException("Nepodařilo se nahrát data do souboru: " + fileName);
        }
    }
}
