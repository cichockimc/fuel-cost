package uk.co.cichocki.fuelcost.model;

import lombok.Getter;

import javax.validation.constraints.Positive;

/**
 * To avoid floating point numbers rounding errors
 * we'll store all values as 100 * value
 * We deal with relatively small values and we won't need precision better than .2 decimal points,
 * hence there is no need for
 * BigInteger or even Long. Regular int should be good enough and serve the purpose well
 */
@Getter
public class SmartFloat {

    @Positive(message = "Value must be positive")
    private final int value; // unit * 100

    public SmartFloat(double value) {
        this.value = (int) (value * 100);
    }

    public SmartFloat(int value) {
        this.value = value * 100;
    }

    @Override
    public String toString() {
        if (value % 100 == 0) {
            return String.valueOf(value / 100);
        } else {
            return String.valueOf(value * 0.01);
        }
    }

    public static SmartFloat of(double d) {
        return new SmartFloat(d);
    }

    public static SmartFloat of(int d) {
        return new SmartFloat(d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartFloat)) return false;

        SmartFloat that = (SmartFloat) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return value;
    }
}
