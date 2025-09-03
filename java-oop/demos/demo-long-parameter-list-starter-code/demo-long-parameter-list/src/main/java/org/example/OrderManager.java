package org.example;

import java.util.List;

public class OrderManager {
    public double orderTotal(List<Item> items, double taxRate, double discountRate) {
        double orderSum = 0.0;

        for (Item i: items
             ) {
            orderSum += i.getPrice();
        }

        orderSum = orderSum - (orderSum * discountRate);

        orderSum += orderSum*taxRate;

        return orderSum;
    }
}
