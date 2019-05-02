package uk.co.cichocki.fuelcost.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import uk.co.cichocki.fuelcost.model.json.SmartFloatPoundSerializer;

@Data
@Builder
public class FuelCostResponse {
    Journey journey;

    JourneyCost cost;

    @JsonSerialize(using = SmartFloatPoundSerializer.class)
    SmartFloat difference; // How much cheaper/more expensive the journey would be today
}
