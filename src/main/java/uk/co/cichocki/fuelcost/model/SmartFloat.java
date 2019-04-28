package uk.co.cichocki.fuelcost.model;

/**
 * To avoid floating point numbers rounding errors
 * we'll store all values as 100 * value
 * We deal with relatively small values and we won't need precision better than .2 decimal points,
 * hence there is no need for
 * BigInteger or even Long. Regular int should be good enough and serve the purpose well
 */
public class SmartFloat {
    private final int value; // unit * 100

    private SmartFloat(int value) {
        this.value = value;
    }

    public SmartFloat(float value) {
        this((int) (value * 100));
    }

    public SmartFloat(double value) {
        this((int) (value * 100));
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value % 100 == 0) {
            return String.valueOf(value / 100);
        } else {
            return String.valueOf(value * 0.01);
        }
    }
}
