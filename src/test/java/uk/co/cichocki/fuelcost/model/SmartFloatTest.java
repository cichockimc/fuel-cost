package uk.co.cichocki.fuelcost.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmartFloatTest {

    @Test
    public void toStringTest() {
        SmartFloat integer = new SmartFloat(10);
        SmartFloat floating = new SmartFloat(2.34);
        SmartFloat mixed = new SmartFloat(89.00);
        assertEquals("10", integer.toString());
        assertEquals("2.34", floating.toString());
        assertEquals("89", mixed.toString());
    }

    @Test
    public void divideBy() {
        SmartFloat integer = new SmartFloat(10);
        SmartFloat floating = new SmartFloat(2.34);
        SmartFloat result = integer.divideBy(floating);
        assertEquals("4.27", result.toString());
    }

    @Test
    public void multiplyBy() {
    }
}