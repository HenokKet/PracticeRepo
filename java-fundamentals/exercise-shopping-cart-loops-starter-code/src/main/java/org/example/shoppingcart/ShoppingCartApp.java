package org.example.shoppingcart;

import java.util.Scanner;

public class ShoppingCartApp {
  public static void main(String[] args) {
    Scanner console = new java.util.Scanner(System.in);

    System.out.println("Welcome to the shopping cart app!");

    boolean confirm = false;

    String taxExempt = "";
    String shipping = "";
    String promoCode = "";
    int orderQuantity = 0;

    // Prompt for tax exempt
    System.out.println("Are you tax-exempt? (y/n)");
    taxExempt = console.nextLine();

    // Prompt for shipping
    System.out.println("Shipping? (standard/overnight/twoday)");
    shipping = console.nextLine();

    // Prompt for order quantity
    System.out.println("Order quantity?");
    orderQuantity = Integer.parseInt(console.nextLine());

    // Prompt for promo code
    System.out.println("Promo code for free shipping?");
    promoCode = console.nextLine();

    // Print details
    System.out.println("\nDetails:");
    System.out.println("Tax-exempt: " + taxExempt);
    System.out.println("Shipping: " + shipping);
    System.out.println("Order quantity: " + orderQuantity);
    System.out.println("Promo code: " + promoCode);


    System.out.println("Bye");
  }
}