package org.example;

public class TemperatureConverter {
    public double celsiusToFahrenheit(double temp){
        return (temp * 9/5) + 32;
    }
    public double fahrenheitToCelsius(double temp){
        return (temp - 32) * 5/9;
    }
    public double fahrenheitToKelvin(double temp){
        return (temp - 32) * 5/9 + 273.15  ;
    }
    public double celsiusToKelvin(double temp){
        return temp + 273.15 ;
    }
    public double kelvinToCelsius(double temp){
        return temp - 273.15 ;
    }
    public double kelvinToFahrenheit(double temp){
        return (temp - 273.15) * 9/5 + 32 ;
    }
}
