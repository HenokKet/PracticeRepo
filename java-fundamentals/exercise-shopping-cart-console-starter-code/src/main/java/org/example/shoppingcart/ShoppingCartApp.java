package org.example.shoppingcart;
import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");

        // Define an instance of Scanner
        Scanner shopper = new Scanner(System.in);
        // Prompt for tax exempt
        System.out.println("are you tax exempt?");
        String tax = shopper.nextLine();
        // Prompt for shipping
        System.out.println("Enter Shipping Address: ");
        String ship = shopper.nextLine();
        // Prompt for order quantity
        boolean isValid = false;
        int order = 0;
        while (!isValid){
            System.out.println("Enter Quantity: ");
            try {
                order = Integer.parseInt(shopper.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. PLease Enter a Whole Number");
            }
        }
        // Prompt for promo code
        System.out.println("Enter Promo Code: ");
        String promo = shopper.nextLine();
        // Print details
        System.out.println("Hello here are your order details");
        System.out.println("Tax exemption: " + tax);
        System.out.println("Shipping Address: " + ship);
        System.out.println("Quantity: " + order);
        System.out.println("Promo Code: " + promo);
        System.out.println("Bye");
    }

}
