package org.example;

import org.example.model.Printer;
import org.example.service.PrinterMonitor;
import org.example.view.ConsoleIO;

/**
 * App class for the 3D Printer Manager Application
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConsoleIO io = ConsoleIO.getInstance();
        Printer myPrinter = new Printer("My Cool 3D Printer");
        PrinterMonitor monitor = new PrinterMonitor(myPrinter);

        boolean running = true;

        io.displayMessage("Printer monitor online");

        while (running) {
            io.displayMessage("[D]isplay printer status");
            io.displayMessage("[P]rint an object");
            io.displayMessage("[C]lear the print bed");
            io.displayMessage("e[X]it");
            String choice = io.getStringRequired("");

            switch (choice) {
                case "D":
                    io.displayMessage(myPrinter.toString());
                    break;
                case "P":
                    if (myPrinter.getStatus().equals(Printer.PrinterStatus.READY)) {
                        String file = io.getStringRequired("Object to print");
                        myPrinter.print(file);
                    } else {
                        io.displayMessage("Printer not ready to accept a new print.");
                    }
                    break;
                case "C":
                    if (myPrinter.getStatus().equals(Printer.PrinterStatus.COMPLETE)) {
                        io.displayMessage("Retrieving " + myPrinter.getPrintModelName());
                        myPrinter.clearBed();
                        io.displayMessage(myPrinter.toString());
                    } else {
                        io.displayMessage("Print incomplete or not started.");
                    }
                    break;
                case "X":
                    running = false;
                    break;
            }
        }
        io.displayMessage("Halting printer monitors");
        monitor.cancel();
        io.displayMessage("Goodbye!");
        System.exit(0);
    }
}
