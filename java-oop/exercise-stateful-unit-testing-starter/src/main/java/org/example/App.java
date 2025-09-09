package org.example;

import org.example.controller.Controller;
import org.example.model.Product;
import org.example.service.VendingMachine;
import org.example.service.VendingMachineImpl;
import org.example.view.ConsoleUI;
import org.example.view.UI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        VendingMachine vm = new VendingMachineImpl();
        UI ui = new ConsoleUI();

        Product apple = new Product("Apple", .25);
        Product candyBar = new Product("Candy Bar", 1.00);
        Product soda = new Product("Soda", .50);

        vm.loadProduct("A1", apple, 10);
        vm.loadProduct("A2", candyBar, 10);
        vm.loadProduct("A3", soda, 10);

        Controller controller = new Controller(vm, ui);
        controller.run();
    }
}
