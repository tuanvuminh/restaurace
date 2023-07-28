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

    //1. Načti stav evidence z disku.
    public void exportToFile(String fileName) throws OrderException {
        try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : orderList) {
                outputWriter.println(order.exportOrder());
            }
        } catch (IOException e) {
            throw new OrderException("Nepodařilo se nahrát data do souboru: " + fileName);
        }
    }

    //5.1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
    public String getCompletedAndUncompletedOrders() {
        int numOfUncompletedOrders = 0;
        for (Order order : orderList) {
            if (!order.getCompletedOrder()) {
                numOfUncompletedOrders++;
            }
        }
        return "Počet rozpracovaných a nedokončených objednávek: " + numOfUncompletedOrders;
    }

    //5.2. Možnost seřadit objednávky podle času zadání.
    public void sortOrdersByTime() {
        Collections.sort(orderList, new OrderTimeComparator());
    }


    //5.3. Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
    public BigDecimal getOrderPricePerWaiter(int waiterNumber) {
        BigDecimal orderPrice = BigDecimal.ZERO;
        for (Order order : orderList) {
            if (order.getWaiter().getWaiterId() == waiterNumber) {
                orderPrice = orderPrice.add(order.getDish().getPrice());
            }
        }
        return orderPrice;
    }

    //5.3. Celkový počet objednávek na číšníka
    public int getNumOfOrdersPerWaiter(int waiterNumber) {
        int numOfOrders = 0;
        for (Order order : orderList) {
            if (order.getWaiter().getWaiterId() == waiterNumber) {
                numOfOrders = numOfOrders + 1;
            }
        }
        return numOfOrders;
    }

    //5.4. Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.
    public long averageTimeToCompleteOrderBetweenGivenTimes(LocalDateTime time1, LocalDateTime time2) throws OrderException {
        long timeToCompleteAllOrders = 0;
        long timeToCompleteOrder;
        int numOfOrders = 0;
        try {
            for (Order order : orderList) {
                if (order.getOrderedTime().isAfter(time1) && order.getOrderedTime().isBefore(time2) && order.getCompletedOrder()) {
                    timeToCompleteOrder = ChronoUnit.MINUTES.between(order.getOrderedTime(), order.getFulfilmentTime());
                    numOfOrders = numOfOrders + 1;
                    timeToCompleteAllOrders = timeToCompleteAllOrders + timeToCompleteOrder;
                }
            }
            return timeToCompleteAllOrders / numOfOrders;
        } catch (ArithmeticException e) {
            throw new OrderException("V daném časovém období nebyly zadané žádné objednávky!");
        }
    }

    //5.5. Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.
    public void getOrderedDishesToday() {
        Set<Dish> setOfDishes = new HashSet<>();
        for (Order order : orderList) {
            if (order.getOrderedTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
                setOfDishes.add(order.getDish());
            }
        }
        System.out.println(setOfDishes);
    }

    //5.6. Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku)
    public void getOrdersPerTable(int tableNumber) {
        int orderNumber = 1;
        if (tableNumber < 9) {
            System.out.println("** Objednávky pro stůl č. " + " " + tableNumber);
        } else {
            System.out.println("** Objednávky pro stůl č. " + tableNumber);
        }
        System.out.println("*******");
        for (Order order : orderList) {
            if (order.getNumberOfTable() == tableNumber) {
                System.out.println(orderNumber + order.orderToPrint());
            }
            orderNumber += 1;
        }
        System.out.println("*******");
    }

}
