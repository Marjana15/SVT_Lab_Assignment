package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {

    @ParameterizedTest
    @CsvSource({
            "0, 32",
            "25, 77",
            "-40, -40"
    })
    void testCelsiusToFahrenheit(double celsius, double expectedFahrenheit) {
        assertEquals(expectedFahrenheit, TemperatureConverter.celsiusToFahrenheit(celsius),
                "Celsius to Fahrenheit conversion failed");
    }

    @Test
    void testCelsiusToFahrenheit_BelowAbsoluteZero() {
        assertThrows(IllegalArgumentException.class,
                () -> TemperatureConverter.celsiusToFahrenheit(-274),
                "Expected exception for temperature below absolute zero"
        );
    }
}
