package com;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int numberOfTable;
    private Waiter waiter;
    private Dish dish;
    private static int orderCounter = 1;
    private int numberOfOrder= orderCounter++;
    private String notesOfCustomer;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private int numOfOrderedDishes = 1;

    public Order(int numberOfTable, Waiter waiter, Dish dish, String notesOfCustomer,
                 LocalDateTime orderedTime, LocalDateTime fulfilmentTime) {
        this(numberOfTable, waiter, dish, notesOfCustomer, orderedTime);
        setFulfilmentTime(fulfilmentTime);
    }

    public Order(int numberOfTable, Waiter waiter, Dish dish, String notesOfCustomer, LocalDateTime orderedTime) {
        this.numberOfTable = numberOfTable;
        this.waiter = waiter;
        this.dish = dish;
        this.notesOfCustomer = notesOfCustomer;
        this.orderedTime = orderedTime;
    }

    public Order(int numberOfTable, Waiter waiter, Dish dish, LocalDateTime orderedTime) {
        this(numberOfTable, waiter, dish, "", orderedTime);
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
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

    public int getNumOfOrderedDishes() {
        return numOfOrderedDishes;
    }

    public void setNumOfOrderedDishes(int numOfOrderedDishes) {
        this.numOfOrderedDishes = numOfOrderedDishes;
    }

    public String formatTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


    public String exportOrder() {
        return "" + dish + " " + "(" + (dish.getPrice().multiply(BigDecimal.valueOf(numOfOrderedDishes))) + " Kč" + ")"
                + ": " + formatTime(orderedTime) + "–"
                + formatTime(fulfilmentTime)
                + " číšník č. " + waiter.getWaiterId();
    }
}
