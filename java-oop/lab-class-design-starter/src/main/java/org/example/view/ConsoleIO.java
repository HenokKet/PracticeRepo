package org.example.view;

import java.util.Scanner;

public class ConsoleIO {
    Scanner myScanner = new Scanner(System.in);

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public void prompt(String message) {
        System.out.print(message);
    }
    public int getInteger(String prompt) {
        boolean goodInput = false;
        int number = -1;

        while(!goodInput) {
            prompt(prompt);
            try {
                number = Integer.parseInt(myScanner.nextLine());
                goodInput = true;
            } catch(Exception e) {
                writeMessage("Input a valid number");
            }
        }
        return number;
    }

    public int getIntegerInBetween(String prompt, int min, int max) {
        boolean goodInput = false;
        int number = -1;

        while(!goodInput) {
            try {
                number = getInteger(prompt);
                if(number >= min && number <= max) {
                    goodInput = true;
                }
                else {
                    System.out.println("Enter a number between " + min + " and " + max);
                }
            } catch(Exception e) {
                writeMessage("Input a valid number");
            }
        }
        return number;
    }

    public String getInput(String prompt) {
        prompt(prompt);
        return myScanner.nextLine();
    }

    public String getNonNullNonEmptyString (String prompt) {
        boolean goodInput = false;
        String result = null;

        while(!goodInput) {
            result = getInput(prompt);
            if(result == null || result.length() <= 0) {
                writeMessage("Enter a valid response.");
            } else {
                goodInput = true;
            }
        }

        return result;
    }

    public void printBreak() {
        writeMessage("\n\n");
    }
}
