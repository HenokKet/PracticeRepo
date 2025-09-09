package org.example.controller;

import org.example.model.MenuChoice;
import org.example.model.Payload;
import org.example.model.Product;
import org.example.service.VendingMachine;
import org.example.view.UI;

public class Controller {
    private VendingMachine vendingMachine;
    private UI ui;

    public Controller(VendingMachine vendingMachine, UI ui) {
        this.vendingMachine = vendingMachine;
        this.ui = ui;
    }

    public void run() {
        boolean running = true;

        while (running) {
            MenuChoice choice = ui.getMenuChoice(vendingMachine);
            switch (choice.getChoice()) {
                case ERROR:
                    ui.displayMessage("Invalid entry.  Please try again.");
                    break;
                case ADD_MONEY:
                    double money = ui.getDouble("Insert money");
                    vendingMachine.addMoney(money);
                    break;
                case GET_CHANGE:
                    double change = vendingMachine.getChange();
                    ui.displayMessage(ui.asCurrency(change) + " dispensed.");
                    break;
                case PRODUCT:
                    Payload<Product> product = vendingMachine.vend(choice.getProduct());
                    if (product.isSuccess()) {
                        ui.displayMessage("Product Dispensed: " + product.getPayload().getName());
                        ui.displayMessage(ui.asCurrency(vendingMachine.getCustomerMoney()) + " credit remaining.");
                    } else {
                        ui.displayMessage("Error: ");
                        ui.displayPrompt(product.getErrorMessage());
                    }
                    break;
                case CASH_OUT:
                    double cash = vendingMachine.emptyMoneyBin();
                    ui.displayMessage("Cashbox emptied of: " + ui.asCurrency(cash));
                    break;
                case VIEW_CASH:
                    ui.displayMessage("Current cashbox: " + ui.asCurrency(vendingMachine.getMoneyBinContents()));
                    break;
                case QUIT:
                    running = false;
                    break;
            }
        }
    }
}
