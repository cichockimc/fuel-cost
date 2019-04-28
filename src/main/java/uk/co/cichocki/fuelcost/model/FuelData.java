package uk.co.cichocki.fuelcost.model;

import lombok.Data;

@Data
public class FuelData {
    SmartFloat pumpPrice;
    SmartFloat dutyRate;
    SmartFloat vatRate;
}
