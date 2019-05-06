package uk.co.cichocki.fuelcost.debug;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
@AllArgsConstructor
public class DebugResponse {
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime dateTime;

    String message;

    public DebugResponse(String message) {
        this.message = message;
    }
}
