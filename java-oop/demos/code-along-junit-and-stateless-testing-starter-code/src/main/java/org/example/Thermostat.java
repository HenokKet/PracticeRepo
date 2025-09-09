package org.example;

public class Thermostat {
    public enum ThermostatBehavior {
        ON_HEAT,
        ON_AC,
        OFF
    }

    private int targetTemperature;
    private int tolerance;

    public int getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(int targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public ThermostatBehavior readSensorData(int[] temps) {
        int sumTemps = 0;
        for (int i = 0; i < temps.length; i++) {
            sumTemps += temps[i];
        }
        int avgTemp = (int) sumTemps / temps.length;
        System.out.println("* Avg Temp: " + avgTemp);

        if ( Math.abs(avgTemp - targetTemperature) <= tolerance) {
            return ThermostatBehavior.OFF;
        } else if (avgTemp > targetTemperature) {
            return ThermostatBehavior.ON_AC;
        } else {
            return ThermostatBehavior.ON_HEAT;
        }
    }

}
