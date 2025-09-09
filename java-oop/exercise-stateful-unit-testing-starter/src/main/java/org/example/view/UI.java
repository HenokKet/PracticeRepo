package org.example.view;

import org.example.model.MenuChoice;
import org.example.service.VendingMachine;

public interface UI {
    String getString(String prompt);
    double getDouble(String prompt);
    void displayMessage(String message);

    void displayPrompt(String prompt);

    void hitEnterToContinue();
    MenuChoice getMenuChoice(VendingMachine vendingMachine);
    String asCurrency(double money);
}
