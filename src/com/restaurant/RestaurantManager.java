package com.restaurant;

import com.restaurant.exception.OrderException;
import com.restaurant.model.Dish;
import com.restaurant.model.Order;
import com.restaurant.model.Waiter;

import java.io.*;
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
            System.err.println("Jídlo “" + order.getDish() + "“ není v nabídce menu!");
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

    public void loadFromFile(String fileName, String delimiter) throws OrderException {
        String[] items = new String[0];
        String line = "";
        int lineNumber = 1;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                items = line.split(delimiter);
                if (items.length != 7)
                    throw new OrderException("Špatný počet položek na řádku: " + lineNumber + " " + line);
                Integer numberOfOrder = Integer.valueOf(items[0]);
                Integer tableNumber = Integer.valueOf(items[1]);
                Waiter waiter = new Waiter(Integer.valueOf(items[2]));
                Dish dish = new Dish(items[3]);
                Integer numberOfOrderedDishes = Integer.valueOf(items[4]);
                LocalDateTime orderedTime = LocalDateTime.parse(items[6]);
                LocalDateTime fulfilmentTime = LocalDateTime.parse(items[7]);
                Order newOrder = new Order(numberOfOrder, tableNumber, waiter, dish, numberOfOrderedDishes,
                        orderedTime, fulfilmentTime);
                orderList.add(newOrder);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new OrderException("Soubor: " + fileName + " nebyl nalezen! " + e.getLocalizedMessage());
        }
    }

    public void saveToFile(String fileName, String delimiter) throws OrderException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orderList) {
                writer.println(
                        order.getNumberOfOrder() + delimiter
                                + order.getTableNumber() + delimiter
                                + order.getWaiter().getWaiterId() + delimiter
                                + order.getDish() + delimiter
                                + order.getNumberOfOrderedDishes() + delimiter
                                + order.formatTime(order.getOrderedTime()) + delimiter
                                + order.formatTime(order.getFulfilmentTime()) + delimiter
                );
            }
        } catch (IOException e) {
            throw new OrderException("Nepodařilo se nahrát data do souboru: " + fileName);
        } catch (NullPointerException e) {
            throw new OrderException("Některé objednávky stále nejsou uzavřené!");
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
    public void sort(Comparator comparator) {
            Collections.sort(orderList, comparator);
    }

    // 3.
    public String getInfoOfOrdersPerWaiter(Waiter waiter) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int numberOfOrders = 0;
        for (Order order : orderList) {
            if (order.getWaiter().equals(waiter)) {
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
        try {
            for (Order order : orderList) {
                if (!(order.getFulfilmentTime() == null)) {
                    numberOfOrders++;
                    timeToCompleteOrder = ChronoUnit.MINUTES.between(order.getOrderedTime(), order.getFulfilmentTime());
                    timeToCompleteAllOrders = timeToCompleteAllOrders + timeToCompleteOrder;
                }
            }
            return "Průměrná doba zpracování všech objednávek: " + timeToCompleteAllOrders / numberOfOrders + " minut";
        } catch (NullPointerException e) {
            throw new OrderException("Některé objednávky stále nejsou uzavřené!");
        }
    }

    // 5.
    public void getOrderedDishesOfToday() {
        Set<Dish> orderedDishesOfToday = new HashSet<>();
        for (Order order : orderList) {
            if (order.getOrderedTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                orderedDishesOfToday.add(order.getDish());
        }
        System.out.println("Seznam objednaných jídel z dnešního dne");
        orderedDishesOfToday.forEach(System.out::println);
    }

    // 6.
    public void getOrdersPerTable(Integer tableNumber) throws OrderException {
        try {
            if (tableNumber < 9) {
                System.out.println("** Objednávky pro stůl č. " + " " + tableNumber);
            } else {
                System.out.println("** Objednávky pro stůl č. " + tableNumber);
            }
            System.out.println("*******");
            for (Order order : orderList) {
                if (order.getTableNumber() == tableNumber) {
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
        } catch (NullPointerException e) {
            throw new OrderException("Některé objednávky stále nejsou uzavřené!");
        }
    }
}







