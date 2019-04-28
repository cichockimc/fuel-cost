package uk.co.cichocki.fuelcost.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

// first week ends 9th June 2003
@Data
@Builder
public class PriceRecord {
    LocalDate date; // ie last day of the week statistics refer to
    FuelData petrol;
    FuelData diesel;
}
