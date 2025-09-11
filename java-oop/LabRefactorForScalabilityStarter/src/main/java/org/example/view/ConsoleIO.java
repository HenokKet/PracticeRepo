package org.example.view;

import java.util.Scanner;

public class ConsoleIO {
    private static ConsoleIO instance;
    Scanner scanner;

    public static ConsoleIO getInstance() {
        if (instance == null) {
            instance = new ConsoleIO();
        }

        return instance;
    }

    private ConsoleIO() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void prompt(String message) {
        System.out.print(message + ": ");
    }

    public String getString(String prompt) {
        prompt(prompt);
        return scanner.nextLine();
    }

    public String getStringRequired(String prompt) {
        boolean validInput = false;
        String result = null;

        while (!validInput) {
            result = getString(prompt);
            if (result != null && result.length() > 0) {
                validInput = true;
            }
        }

        return result;
    }
}
