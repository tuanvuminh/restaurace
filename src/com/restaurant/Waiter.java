package com.restaurant;

public class Waiter {
    private Integer waiterId;

    public Waiter(Integer waiterId) {
        this.waiterId = waiterId;
    }

    public Integer getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Integer waiterId) {
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        return "Čišník " + waiterId;
    }
}
