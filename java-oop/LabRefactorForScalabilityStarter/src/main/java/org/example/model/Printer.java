package org.example.model;

public class Printer {
    public enum PrinterStatus {
        READY,
        WARMING_UP,
        PRINTING,
        COMPLETE
    }

    private String name;
    private PrinterStatus status;
    private String printModelName;
    public static final int cyclesPerWarmup = 1;
    public static final int cyclesPerPrint = 2;

    public Printer(String name) {
        setName(name);
        setStatus(PrinterStatus.READY);
    }
    public PrinterStatus getStatus() {
        return status;
    }

    public void setStatus(PrinterStatus status) {
        this.status = status;
    }

    public String getPrintModelName() {
        return printModelName;
    }

    public void print(String printModelName) {
        this.printModelName = printModelName;
        this.status = PrinterStatus.WARMING_UP;
        System.out.println(toString() + " - Printing " + printModelName);
    }

    public void clearBed() {
        printModelName = null;
        status = PrinterStatus.READY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name + ": " + status;
    }
}
