package com;

import java.time.LocalDate;

public class Order {
    private Table table;
    private Waiter waiter;
    private LocalDate orderedTime;
    private LocalDate fulfilmentTime;
    private Dish dish;
    private String notes;
    private Boolean completedOrder = false;

    public Order(Table table, Waiter waiter, LocalDate orderedTime, LocalDate fulfilmentTime, Dish dish, String notes) {
        this.table = table;
        this.waiter = waiter;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.dish = dish;
        this.notes = notes;
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

    public LocalDate getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDate orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDate getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDate fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getCompletedOrder() {
        return completedOrder;
    }

    public void setCompletedOrder(Boolean completedOrder) {
        this.completedOrder = completedOrder;
    }

    public String exportToString() {
        return table.getNumberOfTable() + "\t" + orderedTime + "\t" + fulfilmentTime + "\t" + waiter.getWaiterId() + "\t" + dish + "\t" + notes + "\t" + completedOrder;
    }
}
