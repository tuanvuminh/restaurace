
package com;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.awt.SystemColor.menu;

public class Order {
    private static int orderCounter = 1;
    private int numberOfOrder= orderCounter++;
    private Table table;
    private Waiter waiter;
    private Dish dish;
    private int numberOfOrderedDishes;
    private LocalDateTime orderedTime;
    private String notesOfCustomer;
    private LocalDateTime fulfilmentTime;
    private BigDecimal totalPriceOfOrder;

    public Order(Table table, Waiter waiter, Dish dish, int numberOfOrderedDishes, LocalDateTime orderedTime, String notesOfCustomer) {
        this.table = table;
        this.waiter = waiter;
        this.dish = dish;
        this.numberOfOrderedDishes = numberOfOrderedDishes;
        this.orderedTime = orderedTime;
        this.notesOfCustomer = notesOfCustomer;
    }

    public Order(Table table, Waiter waiter, Dish dish, LocalDateTime orderedTime) {
        this(table, waiter, dish, 1, orderedTime, "");
    }

    public Order(Table table, Waiter waiter, Dish dish, LocalDateTime orderedTime, String notesOfCustomer) {
        this(table, waiter, dish, 1, orderedTime, notesOfCustomer);
    }

    public Order(Table table, Waiter waiter, Dish dish, int numberOfOrderedDishes, LocalDateTime orderedTime) {
        this(table, waiter, dish, numberOfOrderedDishes, orderedTime, "");
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public static int getOrderCounter() {
        return orderCounter;
    }

    public static void setOrderCounter(int orderCounter) {
        Order.orderCounter = orderCounter;
    }

    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    public String getNotesOfCustomer() {
        return notesOfCustomer;
    }

    public void setNotesOfCustomer(String notesOfCustomer) {
        this.notesOfCustomer = notesOfCustomer;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public int getNumberOfOrderedDishes() {
        return numberOfOrderedDishes;
    }

    public String getNumberOfDishesIfMoreThenOne() {
        String result = "";
        if (numberOfOrderedDishes > 1) {
            result = numberOfOrderedDishes + "x";
        }
        return result;
    }

    public void setNumberOfOrderedDishes(int numberOfOrderedDishes) {
        this.numberOfOrderedDishes = numberOfOrderedDishes;
    }

    public BigDecimal getTotalPriceOfOrder() {
        return totalPriceOfOrder;
    }

    public void setTotalPriceOfOrder(BigDecimal totalPriceOfOrder) {
        this.totalPriceOfOrder = totalPriceOfOrder;
    }

    public String formatTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "Objednávka č. " + numberOfOrder + " " + dish + " čas objednávky " + orderedTime;
    }
}
