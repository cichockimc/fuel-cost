package uk.co.cichocki.fuelcost.model;

import lombok.Getter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * To avoid floating point numbers rounding errors
 * we'll store all values as 100 * value
 * We deal with relatively small values and we won't need precision better than .2 decimal points,
 * hence there is no need for
 * BigInteger or even Long. Regular int should be good enough and serve the purpose well
 */
@Getter
public class SmartFloat {

    private final static int PRECISION = 10000;
    private final static MathContext context = new MathContext(PRECISION, RoundingMode.HALF_EVEN); // banker's precision
    private final static int SCALE = 2;

    @Positive(message = "Value must be positive")
    private final BigDecimal value;

    public SmartFloat(double value) {
        this.value = new BigDecimal(String.valueOf(value), context);
    }

    public SmartFloat(int value) {
        this.value = new BigDecimal(value, context);
    }

    public SmartFloat(@Positive(message = "Value must be positive") BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return isIntegerValue() ? String.valueOf(value.intValueExact()) : value.setScale(SCALE, RoundingMode.HALF_EVEN).toString();
    }

    public SmartFloat divideBy(SmartFloat divisor) {

        return new SmartFloat(this.value.divide(divisor.value, context));
    }

    public SmartFloat multiplyBy(SmartFloat multiplier) {
        return new SmartFloat(this.value.multiply(multiplier.value, context));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartFloat)) return false;

        SmartFloat that = (SmartFloat) o;

        return value.compareTo(that.value) == 0;

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    private boolean isIntegerValue() {
        return value.stripTrailingZeros().scale() <= 0;
    }

    
    public static SmartFloat of(double d) {
        return new SmartFloat(d);
    }

    public static SmartFloat of(int d) {
        return new SmartFloat(d);
    }


}
