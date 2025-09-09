package org.example;

/**
 * The Thermostat application
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to the Thermostat App" );

        // create a new thermostat
        Thermostat thermostat = new Thermostat();
        // set target at 72, tolerance 2
        thermostat.setTargetTemperature(72);
        thermostat.setTolerance(2);

        // declare some temperature readings - avg 73
        int[] tempReadings = {71, 72, 73, 74, 75};

        System.out.println("Reading the sensor data...");
        System.out.println("Result: " + thermostat.readSensorData(tempReadings));

        System.out.println("bye");
    }
}
