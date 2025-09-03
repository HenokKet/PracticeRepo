package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        Before proceeding with the Main class, perform the following:
        1) Create three classes that implement the `Connectable` interface within the project.
            You should have the following devices:
            * TV
            * Fridge
            * Toaster
        2) Modify those classes to implement the desired behaviours within each:
            * Each class needs a property to capture the state, a boolean, to capture whether the device is on or off
            * Modify the methods as appropriate
            * The getName() method simply can return a hardcoded value representing the device type
        */

        // Define an array of devices to store 5 devices
        Connectable[] devices = new Connectable[5];

        System.out.println("Welcome to the Device Manager App!!");
        System.out.println("===================================\n");

        System.out.println("Setup the devices");
        System.out.println("=================\n");
        // Loop through the array of devices
        // - Prompt user for device type to add
        // - Create the device instance
        // - Store the instance in the array
        for (int i = 0; i < devices.length; i++) {
            System.out.println("Choose a device type to add for slot " + (i + 1) + ":");
            System.out.println("1) TV");
            System.out.println("2) Fridge");
            System.out.println("3) Toaster");
            int choice = promptInt("Enter 1-3:", 1, 3);

            switch (choice) {
                case 1:
                    devices[i] = new TV();
                    break;
                case 2 :
                    devices[i] = new Fridge();
                    break;
                case 3 :
                    devices[i] = new Toaster();
                    break;
            }
            System.out.println("Added " + devices[i].getName() + " to slot " + (i + 1) + ".\n");
        }

        System.out.println("Interact with the devices");
        System.out.println("=========================\n");
        // Come up with a menu system which will provide the following:
        // Select the device (one of the 5 in our array)
        // Device Menu Options:
        // - 1. Get Device Name
        // - 2. Turn On Device
        // - 3. Turn Off Device
        // - 4. Get Device Status
        // - 5. Quit
        // This process should continue until the user enters '5' to Quit
        boolean quit = false;
        while (!quit){
            int index = promptInt("Select a device (1-5):", 1, 5) - 1;
            Connectable d = devices[index];
            while (!quit) {
                System.out.println("\nDevice: Slot " + (index + 1) + " (" + d.getName() + ")");
                System.out.println("1. Get Device Name");
                System.out.println("2. Turn On Device");
                System.out.println("3. Turn Off Device");
                System.out.println("4. Get Device Status");
                System.out.println("5. Quit");
                int action = promptInt("Choose 1-5:", 1, 5);

                switch (action){
                    case 1:
                        System.out.println("Name: " + d.getName());
                        break;
                    case 2:
                        d.turnOn();
                        System.out.println(d.getName()+ " is turned ON");
                        break;
                    case 3:
                        d.turnOff();
                        System.out.println(d.getName()+ " is turned OFF");
                        break;
                    case 4:
                        if(d.getState()){
                            System.out.println(d.getName() +" is ON");
                        }else {
                            System.out.println(d.getName() +" is OFF");
                        }
                        break;
                    case 5:
                        quit = true;
                        break;
                }
            }
        }

        System.out.println("\nThanks for using the Device Manager App. Bye!");
    }

    // Utility method to prompt user for integer input
    public static int promptInt(String message) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        while (true) {

            try {
                System.out.println(message);
                String input = sc.nextLine();
                result = Integer.parseInt(input);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input");
            }
        }
        return result;
    }

    // Utility method to prompt user for integer input within a range
    public static int promptInt(String message, int min, int max) {
        int result = 0;

        boolean isValid = false;
        while (!isValid) {
            result = promptInt(message);
            if (result > max || result < min) {
                System.out.println("Entry out of range: " + min + " - " + max + ". Try again.");
            }
            else {
                isValid = true;
            }
        }
        return result;
    }
}