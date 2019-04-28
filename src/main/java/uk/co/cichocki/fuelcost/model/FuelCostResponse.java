package uk.co.cichocki.fuelcost.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuelCostResponse {
    Journey journey;
    SmartFloat dutyPaid;
    SmartFloat difference; // How much cheaper/more expensive the journey would be today
}
