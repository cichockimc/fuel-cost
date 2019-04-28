package uk.co.cichocki.fuelcost.model;

import java.time.LocalDate;

// first week ends 9th June 2003
// Date,ULSP Pump price,ULSP Duty rate,ULSP VAT (% rate),ULSD Pump price,ULSD Duty rate,ULSD VAT (% rate)
public class PriceRecord {
    LocalDate date; // ie last day of the week statistics refer to
    FuelData petrol;
    FuelData diesel;
}
