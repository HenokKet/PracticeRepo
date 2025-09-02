package org.example;

public class ShoppingCart {
    public double checkoutShoppingCart (Item[] items, double taxRate, double discountCode) {

        double subtotal = 0.00;
        for (int i = 0; i < items.length; i++) {
            subtotal += items[i].getPrice();
        }

        double discount = subtotal * discountCode;

        subtotal -= discount;

        double tax = (subtotal) * taxRate;

        return subtotal + tax;
    }
}
