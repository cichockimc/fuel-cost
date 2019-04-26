package uk.co.cichocki.fuelcost.dummy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DummyResponse {
    int number;
    String message;
}
