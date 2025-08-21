package org.example;

import java.util.Scanner;

public class UserRegistration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = 0;
        String email = "";
        int pin = 0;

        // Handling age input
        while (true) {
            try {
                System.out.print("Enter your age: ");
                age = Integer.parseInt(scanner.nextLine());
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be greater than 0.");
                }
                break; // Exit loop if input is valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for age.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Handling email input
        while (true) {
            try {
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                if (email == null || email.trim().isEmpty()) {
                    throw new IllegalArgumentException("Email cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Handling PIN input
        while (true) {
            try {
                System.out.print("Enter your 4-digit PIN: ");
                String pinInput = scanner.nextLine();

                if (!pinInput.matches("\\d{4}")) {  // Ensures exactly 4 digits
                    throw new IllegalArgumentException("PIN must be exactly 4 digits.");
                }

                pin = Integer.parseInt(pinInput); // Convert string PIN to integer
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Successful registration message
        System.out.println("\nRegistration Successful!");
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("PIN: " + pin);

        // Finally block message
        System.out.println("\nRegistration Attempt Complete!");

        scanner.close(); // Closing the scanner to prevent resource leaks
    }
}
