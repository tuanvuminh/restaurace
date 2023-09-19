package com.restaurant.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Order {

    private static int orderCounter = 1;
    private Integer numberOfOrder= orderCounter++;
    private Integer tableNumber;
    private Waiter waiter;
    private Dish dish;
    private Integer numberOfOrderedDishes;
    private LocalDateTime orderedTime;
    private String notesOfCustomer;
    private LocalDateTime fulfilmentTime;

    public Order(Integer numberOfOrder, Integer tableNumber, Waiter waiter, Dish dish, Integer numberOfOrderedDishes,
                 LocalDateTime orderedTime, LocalDateTime fulfilmentTime) {
    }

    public Order(Integer tableNumber, Waiter waiter, Dish dish, Integer numberOfOrderedDishes, LocalDateTime orderedTime, String notesOfCustomer) {
        this.tableNumber = tableNumber;
        this.waiter = waiter;
        this.dish = dish;
        this.numberOfOrderedDishes = numberOfOrderedDishes;
        this.orderedTime = orderedTime;
        this.notesOfCustomer = notesOfCustomer;
    }

    public Order(Integer tableNumber, Waiter waiter, Dish dish, LocalDateTime orderedTime) {
        this(tableNumber, waiter, dish, 1, orderedTime, "");
    }

    public Order(Integer tableNumber, Waiter waiter, Dish dish, LocalDateTime orderedTime, String notesOfCustomer) {
        this(tableNumber, waiter, dish, 1, orderedTime, notesOfCustomer);
    }

    public Order(Integer tableNumber, Waiter waiter, Dish dish, Integer numberOfOrderedDishes, LocalDateTime orderedTime) {
        this(tableNumber, waiter, dish, numberOfOrderedDishes, orderedTime, "");
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
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

    public static Integer getOrderCounter() {
        return orderCounter;
    }

    public static void setOrderCounter(Integer orderCounter) {
        Order.orderCounter = orderCounter;
    }

    public Integer getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(Integer numberOfOrder) {
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

    public Integer getNumberOfOrderedDishes() {
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

    public String formatTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "Objednávka č. " + numberOfOrder + " " + dish +" [čas objednávky: "
                + formatTime(getOrderedTime()) + "] [čišník: č. "
                + waiter.getWaiterId() + "]";
    }
}
