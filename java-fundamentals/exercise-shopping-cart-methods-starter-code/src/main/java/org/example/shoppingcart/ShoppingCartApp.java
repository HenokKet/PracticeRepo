package org.example.shoppingcart;

/**
 * Refactor the code to use the following methods:
 * 1. Display a list of choices from an array. Returns nothing. Needs an array
 * of choices (strings).
 * 2. Prompt the user for strings. Returns a string. Needs a string to prompt
 * the user with.
 * 3. Prompt the user for an integer. Returns an integer. Needs a string to
 * prompt the user with.
 *
 * Note: We will not create method(s) for calculating the total cost until we
 * learn more about Object-Oriented Programming and some additional data
 * structures.
 */

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
      System.out.println("Are you tax-exempt? (y/n)");
      taxExempt = console.nextLine();

      // Prompt for shipping address
      for (int i = 0; i < addresses.length; i++) {
        System.out.println((i + 1) + ". " + addresses[i]);
      }
      System.out.println("Shipping address?");
      String address = console.nextLine();
      addressIndex = Integer.parseInt(address);
      // Prompt for shipping
      System.out.println("Shipping? (standard/overnight/twoday)");
      shipping = console.nextLine();

      // Prompt for order quantity
      System.out.println("Order quantity?");
      int orderQuantity = Integer.parseInt(console.nextLine());

      // Prompt for Size
      for (int i = 0; i < sizes.length; i++) {
        System.out.println((i + 1) + ". " + sizes[i]);
      }
      System.out.println("Size?");
      String size = console.nextLine();
      sizeIndex = Integer.parseInt(size);

      // Prompt for promo code
      System.out.println("Promo code for free shipping?");
      promoCode = console.nextLine();

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
}
