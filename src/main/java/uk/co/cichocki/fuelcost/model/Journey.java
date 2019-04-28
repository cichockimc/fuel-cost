package uk.co.cichocki.fuelcost.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Journey {
    LocalDate date;
    FuelType fuelType;
    SmartFloat mpg;
    SmartFloat mileage;
}
