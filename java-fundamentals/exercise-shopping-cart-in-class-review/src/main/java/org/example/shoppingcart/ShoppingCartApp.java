package org.example.shoppingcart;
import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");

        // Define an instance of Scanner
        Scanner console = new Scanner(System.in);

        // Prompt for tax exempt
        String taxExempt;
        // Prompt for shipping
        String shipping;
        // Prompt for order quantity
        int orderQuantity =0;
        // Prompt for promo code
        String promoCode;
        // Print details
        //Sure see below
        //Extra Credit
        boolean isValid = false;

//        Print appropriate strings to the console to collect the following information from the user:
//        1. Prompt: Are you tax-exempt?
        System.out.println("Are you tax exempt? (y/n)");
        taxExempt = console.nextLine();

//       2. Prompt: Shipping?
        System.out.println("Shipping? (y/n)");
        shipping = console.nextLine();
//       3. Prompt: Order quantity
        while (!isValid) {
            System.out.println("How many beanie babies are you ordering? (Whole Numbers Only) ");
            try {
                orderQuantity = Integer.parseInt(console.nextLine());
                isValid = true;
                break;
            } catch (Exception e) {
                isValid = false;
                continue;
            }
        }

//        4. Prompt: Promo code for free shipping.
        System.out.println("Enter Your Promo Code for Free Shipping: (NA if no promo code): ");
        promoCode = console.nextLine();

//        5. Print details
        System.out.println("The user is tax exempt: " + taxExempt);
        System.out.println("The User Requires Shipping: " + shipping);
        System.out.println("The user ordered: " + orderQuantity + " beanie babies!");
        System.out.println("Your shipping promo code is: " + promoCode);
        System.out.println("Bye");
    }

}
