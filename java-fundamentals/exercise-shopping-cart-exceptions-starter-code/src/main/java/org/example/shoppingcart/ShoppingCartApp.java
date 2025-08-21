package org.example.shoppingcart;

import java.util.Scanner;

public class ShoppingCartApp {

  public static void main(String[] args) {
    System.out.println("Welcome to the shopping cart app!");
    String[] addresses = { "123 Main St", "456 Main St", "789 Main St" };
    String[] sizes = { "small", "medium", "large" };

    java.util.Scanner console = new java.util.Scanner(System.in);
    boolean confirm = false;
    String taxExempt = "";
    String shipping = "";
    String promoCode = "";

    while (!confirm) {
      int addressIndex = 0;
      int sizeIndex = 0;
      // Prompt for tax exempt

      taxExempt = promptUserForString("Are you tax-exempt? (y/n)");

      // Prompt for shipping address
      displayChoices(addresses);
      addressIndex = promptUserForInt("Shipping address?");
      // Prompt for shipping
      shipping = promptUserForString("Shipping? (standard/overnight/twoday)");

      // Prompt for order quantity
      int orderQuantity = promptUserForInt("Order quantity?");

      // Prompt for Size
      displayChoices(sizes);
      sizeIndex = promptUserForInt("Size?");

      // Prompt for promo code
      promoCode = promptUserForString("Promo code for free shipping?");

      // Print details
      System.out.println("\nDetails:");
      System.out.println("Tax-exempt: " + taxExempt);
      System.out.println("Address: " + addresses[addressIndex - 1]);
      System.out.println("Shipping: " + shipping);
      System.out.println("Size: " + sizes[sizeIndex - 1]);
      System.out.println("Order quantity: " + orderQuantity);
      System.out.println("Promo code: " + promoCode);
      System.out.println("Confirm Order y/n");
      confirm = "y".equals(console.nextLine());
    }

    System.out.println("Bye");
  }

  // Method for display choices
  private static void displayChoices(String[] choices) {
    for (int i = 0; i < choices.length; i++) {
      System.out.println(i + 1 + ": " + choices[i]);
    }
  }

  // Method for prompt user for string
  private static String promptUserForString(String prompt) {
    Scanner console = new java.util.Scanner(System.in);
    System.out.println(prompt);
    return console.nextLine();
  }

  // Method for prompt user for int
  private static int promptUserForInt(String prompt) {
    Scanner console = new java.util.Scanner(System.in);
    System.out.println(prompt);
    return Integer.parseInt(console.nextLine());
  }
}
