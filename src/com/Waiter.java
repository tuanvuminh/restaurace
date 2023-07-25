package com;

public class Waiter {
    private int waiterId;
    private int numberOfOrders;

    public Waiter(int waiterId, int numberOfOrders) {
        this.waiterId = waiterId;
        this.numberOfOrders = numberOfOrders;
    }

    public Waiter(int waiterId) {
        this(waiterId, 0);
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
