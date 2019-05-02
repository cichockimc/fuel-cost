package uk.co.cichocki.fuelcost.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import uk.co.cichocki.fuelcost.model.json.SmartFloatPoundSerializer;

@Data
@AllArgsConstructor
public class JourneyCost {

    @JsonSerialize(using = SmartFloatPoundSerializer.class)
    SmartFloat fuelCost;

    @JsonSerialize(using = SmartFloatPoundSerializer.class)
    SmartFloat dutyPaid;
}
