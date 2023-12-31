package com.engeto.restaurant.comparator;

import com.engeto.restaurant.model.Order;

import java.util.Comparator;
public class OrderWaiterComparator implements Comparator<Order> {
    @Override
    public int compare(Order p1, Order p2) {
        return p1.getWaiter().getWaiterId().compareTo(p2.getWaiter().getWaiterId());
    }
}
