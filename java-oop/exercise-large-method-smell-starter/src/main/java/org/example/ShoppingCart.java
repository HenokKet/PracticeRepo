package org.example;

public class ShoppingCart {
    public double checkoutShoppingCart (Item[] items, double taxRate, double discountCode) {
        //Calculate Subtotal
        double subtotal = calcItemSubtotal(items);
        //Calculating the discount
        subtotal -= calcDiscount(subtotal,discountCode);
        //Calculating the Tax
        subtotal += calcTax(subtotal,taxRate);
        //Returning the final result
        return subtotal;
    }
    public double calcItemSubtotal(Item[] items){
        //Gets all the items and calculates the subtotal
        double subtotal = 0.00;
        for (int i = 0; i < items.length; i++) {
            subtotal += items[i].getPrice();
        }
        //returns final amount
        return subtotal;
    }

    public double calcDiscount(double subtotal, double discountCode){
        //Calculates and return price after discount
        return subtotal * discountCode;
    }

    public double calcTax(double subtotal, double taxRate){
        //Calculates and return price after tax
        return subtotal * taxRate;
    }

}
