package uk.co.cichocki.fuelcost.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import uk.co.cichocki.fuelcost.model.json.SmartFloatDefaultSerializer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class Journey {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Invalid date")
    LocalDate date;

    @NotNull
    FuelType fuelType;

    @NotNull
    @Valid
    @JsonSerialize(using = SmartFloatDefaultSerializer.class)
    SmartFloat mpg;

    @NotNull
    @Valid
    @JsonSerialize(using = SmartFloatDefaultSerializer.class)
    SmartFloat mileage;
}
