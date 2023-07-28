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
    private Customer notesOfCustomer;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private Boolean completedOrder = false;
    private int numOfOrderedDishes = 1;


    public Order(int numberOfTable, Waiter waiter, Dish dish, Customer notesOfCustomer,
                 LocalDateTime orderedTime, LocalDateTime fulfilmentTime) {
        this.numberOfTable = numberOfTable;
        this.waiter = waiter;
        this.dish = dish;
        this.notesOfCustomer = notesOfCustomer;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
    }

    public Order(int numberOfTable, Waiter waiter, Dish dish, Customer notesOfCustomer, LocalDateTime orderedTime) {
        this.numberOfTable = numberOfTable;
        this.waiter = waiter;
        this.dish = dish;
        this.notesOfCustomer = notesOfCustomer;
        this.orderedTime = orderedTime;
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

    public Customer getNotesOfCustomer() {
        return notesOfCustomer;
    }

    public void setNotesOfCustomer(Customer notesOfCustomer) {
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

    public Boolean getCompletedOrder() {
        return completedOrder;
    }

    public void setCompletedOrder(Boolean completedOrder) {
        this.completedOrder = completedOrder;
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

    public String getNumberOfDishesIfBiggerThenOne() {
        String result = "";
        if (numOfOrderedDishes > 1) {
            result = numOfOrderedDishes + "x";
        }
        return result;
    }

    public String orderToPrint() {
        return "." + " "
                + dish + " "
                + getNumberOfDishesIfBiggerThenOne() + " " + "(" + (dish.getPrice().multiply(BigDecimal.valueOf(numOfOrderedDishes))) + " Kč" + ")"
                + ":" + "\t"
                + formatTime(orderedTime)
                + "–"
                + formatTime(fulfilmentTime) + "\t"
                + "číšník č. " + waiter.getWaiterId();
    }

    public String exportOrder() {
        return "Stůl č." + numberOfTable +", čišník: " + waiter + ", objednáné jídlo: " + dish +
                ", objednávka " + numberOfOrder + ". , poznámky od zákazníka: " + notesOfCustomer + ", čas objednávky: "
                + orderedTime + " , čas dokončení objednávky: " + fulfilmentTime;
    }
}
