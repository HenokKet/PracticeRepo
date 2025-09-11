package org.example.service;

import org.example.model.Printer;
import org.example.view.ConsoleIO;

import java.util.Timer;
import java.util.TimerTask;

public class PrinterMonitor extends TimerTask {
    Timer timer;
    Printer printer;
    int cycle;
    ConsoleIO io = ConsoleIO.getInstance();
    private static final int SECONDS = 1000; //milliseconds per second

    public PrinterMonitor(Printer printer) {
        timer = new Timer();
        this.printer = printer;
        timer.scheduleAtFixedRate(this, 3 * SECONDS, 3 * SECONDS);
    }

    @Override
    public void run() {
        switch (printer.getStatus()) {
            case READY:
                handleReady();
                break;
            case WARMING_UP:
                handleWarmup();
                break;
            case PRINTING:
                handlePrinting();
                break;
            case COMPLETE:
                handleComplete();
                break;
        }
    }

    private void handleReady() {
        cycle = 0;
    }

    private void handleWarmup() {
        if (cycle >= Printer.cyclesPerWarmup) {
            printer.setStatus(Printer.PrinterStatus.PRINTING);
            io.displayMessage("STATUS UPDATE:" + printer.toString());
            cycle = 0;
        } else {
            cycle++;
        }
    }

    private void handlePrinting() {
        if (cycle >= Printer.cyclesPerPrint) {
            printer.setStatus(Printer.PrinterStatus.COMPLETE);
            io.displayMessage("STATUS UPDATE:" + printer.toString());
            cycle = 0;
        } else {
            cycle++;
        }
    }

    private void handleComplete() {
        cycle = 0;
    }
}
