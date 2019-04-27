package uk.co.cichocki.fuelcost.debug;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
@AllArgsConstructor
public class DebugResponse {
    Integer number;
    String message;
}
