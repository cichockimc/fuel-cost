package uk.co.cichocki.fuelcost.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JourneyCost {
    SmartFloat fuelCost;
    SmartFloat dutyPaid;
}
