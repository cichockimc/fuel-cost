package uk.co.cichocki.fuelcost.model;

import lombok.Getter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Avoiding floating point numbers rounding errors
 */
@Getter
public class SmartFloat {

    private final static int PRECISION = 10000;
    private final static MathContext context = new MathContext(PRECISION, RoundingMode.HALF_EVEN); // banker's precision
    private final static int SCALE = 2;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

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

    private static boolean isIntegerValue(BigDecimal val) {
        return val.setScale(SCALE, ROUNDING_MODE).stripTrailingZeros().scale() <= 0;
    }

    public SmartFloat divideBy(SmartFloat divisor) {

        return new SmartFloat(this.value.divide(divisor.value, context));
    }

    public SmartFloat multiplyBy(SmartFloat multiplier) {
        return new SmartFloat(this.value.multiply(multiplier.value, context));
    }

    @Override
    public String toString() {
        BigDecimal scaled = value.setScale(SCALE, RoundingMode.HALF_EVEN);
        return isIntegerValue(scaled) ? String.valueOf(scaled.intValueExact()) : scaled.toString();
    }

    public String toPounds() {
        BigDecimal scaled = value.divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN).setScale(SCALE, RoundingMode.HALF_EVEN);
        return isIntegerValue(scaled) ? String.valueOf(scaled.intValueExact()) : scaled.toString();
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

    public SmartFloat subtract(SmartFloat subtrahend) {
        return new SmartFloat(this.value.subtract(subtrahend.value, context));
    }


    public static SmartFloat of(double d) {
        return new SmartFloat(d);
    }

    public static SmartFloat of(int d) {
        return new SmartFloat(d);
    }


}
