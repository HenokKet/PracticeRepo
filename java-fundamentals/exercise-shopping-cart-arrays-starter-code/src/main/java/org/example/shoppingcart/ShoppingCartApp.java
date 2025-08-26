package org.example.shoppingcart;

import java.util.Scanner;

public class ShoppingCartApp {
  public static void main(String[] args) {
    Scanner console = new java.util.Scanner(System.in);

    System.out.println("Welcome to the shopping cart app!");

    // Create arrays to contain addresses and sizes
      String[] addresses = new String[3];
      String[] sizes = new String[3];

    // Prompt for shipping address
      for (int i = 0; i < addresses.length; i++) {
          System.out.print("Enter shipping address for item " + (i + 1) + ": ");
          addresses[i] = console.nextLine();
      }
      // Prompt for Size
      for (int i = 0; i < sizes.length; i++) {
          System.out.print("Enter size for item " + (i + 1) + " (S, M, L, XL): ");
          sizes[i] = console.nextLine();
      }
      // Print details
      System.out.println("\nDetails:");
      for (int i = 0; i < addresses.length; i++) {
          System.out.println("Item " + (i + 1) + " - Address: " + addresses[i] + ", Size: " + sizes[i]);
      }

      System.out.println("Bye");
  }
}
