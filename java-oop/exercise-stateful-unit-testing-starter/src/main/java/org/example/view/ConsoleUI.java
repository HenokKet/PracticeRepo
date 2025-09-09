package org.example.view;

import org.example.model.MenuChoice;
import org.example.model.Product;
import org.example.service.VendingMachine;

import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements UI {

    Scanner scanner = new Scanner(System.in);
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    @Override
    public String getString(String prompt) {
        displayMessage(prompt);
        return scanner.nextLine();
    }

    @Override
    public double getDouble(String prompt) {
        boolean running = true;
        double result = 0;
        while(running) {
            displayPrompt(prompt);
            String input = scanner.nextLine();
            try {
                result = Double.parseDouble(input);
                running = false;
            } catch (Exception e) {
                displayMessage("Invalid input:");
                displayMessage(e.getLocalizedMessage());
            }
        }

        return result;
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayPrompt(String prompt) {
        System.out.print(prompt + ": ");
    }

    @Override
    public void hitEnterToContinue() {
        scanner.nextLine();
    }

    @Override
    public MenuChoice getMenuChoice(VendingMachine vendingMachine) {
        displayMenu(vendingMachine);
        String choice = getString("");
        MenuChoice result = new MenuChoice();

        if (choice.trim().equals("M")) {
            result.setChoice(MenuChoice.MENU_CHOICE.ADD_MONEY);
        } else if (choice.trim().equals("C")) {
            result.setChoice(MenuChoice.MENU_CHOICE.GET_CHANGE);
        } else if (choice.trim().equals("Q")) {
            result.setChoice(MenuChoice.MENU_CHOICE.QUIT);
        } else if (choice.trim().equals("$")) {
            result.setChoice(MenuChoice.MENU_CHOICE.VIEW_CASH);
        } else if (choice.trim().equals("CASH")) {
            result.setChoice(MenuChoice.MENU_CHOICE.CASH_OUT);
        } else {
            try {
                result.setChoice(MenuChoice.MENU_CHOICE.PRODUCT);
                result.setProduct(choice);
            } catch (Exception e) {
                result.setChoice(MenuChoice.MENU_CHOICE.ERROR);
            }
        }

        return result;
    }

    @Override
    public String asCurrency(double money) {
        return currency.format(money);
    }

    private void displayMenu(VendingMachine vendingMachine) {
        displayMessage(">> Vending Machine <<");
        displayMessage("Credit:  " + asCurrency(vendingMachine.getCustomerMoney()));
        displayMessage("");
        List<String> bins = vendingMachine.getBinIds();
        for (String bin : bins) {
            Product p = vendingMachine.getBinContents(bin);
            displayMessage(bin + ": " + p.getName() + " - " + asCurrency(p.getPrice()) + "(" + vendingMachine.getBinQuantity(bin) + ")");
        }

        displayMessage("M - Add Money");
        displayMessage("C - Get Change");
        displayMessage("Q - Quit");
        displayMessage("");
        displayMessage(">> ADMIN <<");
        displayMessage("$ - View money box");
        displayMessage("CASH - Cash Out");
    }
}
