package com;

import com.comparators.OrderTimeComparator;
import com.comparators.OrderWaiterComparator;
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
    private Set<Dish> orderedDishesOfToday = new HashSet<>();

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
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orderList) {
                writer.println(
                        order.getNumberOfOrder() + "." + " "
                                + order.getDish() + " "
                                + order.getNumberOfDishesIfMoreThenOne() + " " + "("
                                + (order.getDish().getPrice().multiply(BigDecimal.valueOf(order.getNumberOfOrderedDishes()))) + " Kč" + ")"
                                + ":" + "\t"
                                + order.formatTime(order.getOrderedTime())
                                + "–"
                                + order.formatTime(order.getFulfilmentTime()) + "\t"
                                + "číšník č. " + order.getWaiter().getWaiterId()
                );
            }
        } catch (IOException e) {
            throw new OrderException("Nepodařilo se nahrát data do souboru: " + fileName);
        }
    }

    // 1.
    public String getNumberOfUncompletedOrders() {
        int numberOfUncompletedOrders = 0;
        for (Order order : orderList) {
            if (order.getFulfilmentTime() == null) {
                numberOfUncompletedOrders++;
            }
        }
        return "Počet rozpracovaných/nedokončených objednávek: " + numberOfUncompletedOrders;
    }

    // 2.
    public void sortByTimeOfOrder() {
        for (Order order : orderList) {
            Collections.sort(orderList, new OrderTimeComparator());
            System.out.println(orderList);
        }
    }

    public void sortByWaiterId() {
        for (Order order : orderList) {
            Collections.sort(orderList, new OrderWaiterComparator());
            System.out.println("Objednávky podle ID čišníků: " + orderList);
        }
    }

    // 3.

    public String getInfoOfOrdersPerWaiter(Waiter waiter) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int numberOfOrders = 0;
        for (Order order : orderList) {
            if (order.getWaiter() == waiter) {
                numberOfOrders++;
                totalPrice = totalPrice.add(order.getDish().getPrice().multiply(BigDecimal.valueOf(order.getNumberOfOrderedDishes())));
            }
        }
        return waiter.toString()+ " [počet objednávek: " + numberOfOrders + "] [celková cena objednávek: " + totalPrice + " Kč]";
    }

    // 4.
    public String averageTimeOfOrdersToComplete() throws OrderException {
        long timeToCompleteOrder = 0;
        long timeToCompleteAllOrders = 0;
        int numberOfOrders = 0;
        long averageTimeOfOrdersToComplete = 0;
        try {
            for (Order order : orderList) {
                if (order.getFulfilmentTime() == order.getFulfilmentTime()) {
                    numberOfOrders++;
                    timeToCompleteOrder = ChronoUnit.MINUTES.between(order.getOrderedTime(), order.getFulfilmentTime());
                    timeToCompleteAllOrders = timeToCompleteAllOrders + timeToCompleteOrder;
                    averageTimeOfOrdersToComplete = timeToCompleteAllOrders / numberOfOrders;
                }
            }
            return "Průměrná doba zpracování všech objednávek: " + averageTimeOfOrdersToComplete + " minut";
        } catch (NullPointerException e) {
            throw new OrderException("Některé objednávky stále nejsou uzavřené!");
        }
    }

    // 5.
    public void getOrderedDishesOfToday() {
        for (Order order : orderList) {
            if (order.getOrderedTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                orderedDishesOfToday.add(order.getDish());
        }
        System.out.println("Seznam objednaných jídel z dnešního dne");
        orderedDishesOfToday.forEach(System.out::println);
    }

    // 6.
    public void getOrdersPerTable(Table table) {
        if (table.getNumberOfTable() < 9) {
            System.out.println("** Objednávky pro stůl č. " + " " + table.getNumberOfTable());
        } else {
            System.out.println("** Objednávky pro stůl č. " + table.getNumberOfTable());
        }
        System.out.println("*******");
        for (Order order : orderList) {
            if (order.getTable().getNumberOfTable() == table.getNumberOfTable()) {
                System.out.println(order.getNumberOfOrder() + "." + " "
                        + order.getDish() + " "
                        + order.getNumberOfDishesIfMoreThenOne() + " " + "("
                        + (order.getDish().getPrice().multiply(BigDecimal.valueOf(order.getNumberOfOrderedDishes()))) + " Kč" + ")"
                        + ":" + "\t"
                        + order.formatTime(order.getOrderedTime())
                        + "–"
                        + order.formatTime(order.getFulfilmentTime()) + "\t"
                        + "číšník č. " + order.getWaiter().getWaiterId()
                );
            }
        }
        System.out.println("*******");
    }
}







