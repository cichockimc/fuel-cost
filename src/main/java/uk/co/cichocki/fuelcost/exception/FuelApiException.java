package uk.co.cichocki.fuelcost.exception;

public abstract class FuelApiException extends Exception {

    public FuelApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
