package uk.co.cichocki.fuelcost.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuelData {
    SmartFloat pumpPrice;
    SmartFloat dutyRate;
    SmartFloat vatRate;
}
