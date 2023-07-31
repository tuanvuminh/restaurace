package com.restaurant.comparators;

import com.restaurant.Order;

import java.util.Comparator;

public class OrderWaiterComparator implements Comparator<Order> {
    @Override
    public int compare(Order p1, Order p2) {
        return p1.getWaiter().getWaiterId() - (p2.getWaiter().getWaiterId());
    }
}