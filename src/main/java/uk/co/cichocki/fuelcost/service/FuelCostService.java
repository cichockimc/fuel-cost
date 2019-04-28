package uk.co.cichocki.fuelcost.service;

import org.springframework.stereotype.Service;
import uk.co.cichocki.fuelcost.model.FuelCostResponse;
import uk.co.cichocki.fuelcost.model.Journey;

@Service
public class FuelCostService {
    public FuelCostResponse calculateCost(Journey journey) {
        FuelCostResponse response = FuelCostResponse.builder().journey(journey).build();
        return response;
    }
}
