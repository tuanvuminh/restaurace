package com;

public class Waiter {
    private int waiterId;

    public Waiter(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        return "Čišník " + waiterId;
    }
}
