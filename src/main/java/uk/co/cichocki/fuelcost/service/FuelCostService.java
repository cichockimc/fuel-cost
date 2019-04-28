package uk.co.cichocki.fuelcost.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.co.cichocki.fuelcost.data.FuelCostDataStore;
import uk.co.cichocki.fuelcost.model.FuelCostResponse;
import uk.co.cichocki.fuelcost.model.Journey;
import uk.co.cichocki.fuelcost.model.PriceRecord;

import java.util.Optional;

@Service
@Slf4j
public class FuelCostService {

    private final FuelCostDataStore dataStore;

    public FuelCostService(FuelCostDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public FuelCostResponse calculateCost(Journey journey) {
        Optional<PriceRecord> maybePriceRecord = dataStore.get(journey.getDate());
        PriceRecord journeyPriceRecord = maybePriceRecord.orElseThrow(() -> new RuntimeException("Data not found"));
        journey.getMileage();
        return null;
    }


}
