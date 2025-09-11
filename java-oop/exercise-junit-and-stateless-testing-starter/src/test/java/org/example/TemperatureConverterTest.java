package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemperatureConverterTest {
    private TemperatureConverter converter;

    //Set up for JUNIT testing
    @BeforeEach
    void setUp() {
        converter = new TemperatureConverter();
    }

    @Test
    void celsiusToFahrenheit_examples() {
        // Arrange
        double c1 = 0, c2 = 100;
        // Act
        double f1 = converter.celsiusToFahrenheit(c1);
        double f2 = converter.celsiusToFahrenheit(c2);
        // Assert
        assertEquals(32.0, f1);
        assertEquals(212.0, f2);
    }

    //Test for fahrenheitToCelsius
    @Test
    void fahrenheitToCelsius_examples() {
        // Arrange
        double f1 = 32, f2 = 212;
        // Act
        double c1 = converter.fahrenheitToCelsius(f1);
        double c2 = converter.fahrenheitToCelsius(f2);
        // Assert
        assertEquals(0.0, c1);
        assertEquals(100.0, c2);
    }

    //Test for celsiusToKelvin
    @Test
    void celsiusToKelvin_examples() {
        // Arrange
        double c1 = -273.15, c2 = 0, c3 = 100;
        // Act
        double k1 = converter.celsiusToKelvin(c1);
        double k2= converter.celsiusToKelvin(c2);
        double k3 = converter.celsiusToKelvin(c3);
        // Assert
        assertEquals(0.0, k1);
        assertEquals(273.15, k2);
        assertEquals(373.15, k3);
    }

    //Test for kelvinToCelsius
    @Test
    void kelvinToCelsius_examples() {
        // Arrange
        double k1 = 0, k2 = 273.15, k3 = 373.15;
        // Act
        double c1 = converter.kelvinToCelsius(k1);
        double c2 = converter.kelvinToCelsius(k2);
        double c3 = converter.kelvinToCelsius(k3);
        // Assert
        assertEquals(-273.15, c1);
        assertEquals(0.0, c2);
        assertEquals(100.0, c3);
    }

    //Test for fahrenheitToKelvin
    @Test
    void fahrenheitToKelvin_examples() {
        // Arrange
        double f1 = 32, f2 = 212;
        // Act
        double k1 = converter.fahrenheitToKelvin(f1);
        double k2 = converter.fahrenheitToKelvin(f2);
        // Assert
        assertEquals(273.15, k1);
        assertEquals(373.15, k2);
    }

    //Test for kelvinToFahrenheit
    @Test
    void kelvinToFahrenheit_examples() {
        // Arrange
        double k1 = 273.15, k2 = 373.15;
        // Act
        double f1 = converter.kelvinToFahrenheit(k1);
        double f2 = converter.kelvinToFahrenheit(k2);
        // Assert
        assertEquals(32.0, f1);
        assertEquals(212.0, f2);
    }

}