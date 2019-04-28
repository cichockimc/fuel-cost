package uk.co.cichocki.fuelcost.service;

import org.junit.Test;
import uk.co.cichocki.fuelcost.model.SmartFloat;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void getGallons() {
        SmartFloat miles = SmartFloat.of(534.9);
        SmartFloat mpg = SmartFloat.of(56.8);
        String result = calculator.getGallons(miles, mpg).toString();
        assertEquals("9.42", result);
    }

    @Test
    public void gallonsToLitres() {
        SmartFloat gallons = SmartFloat.of(9.42);
        assertEquals("42.82", calculator.gallonsToLitres(gallons).toString());
    }
}