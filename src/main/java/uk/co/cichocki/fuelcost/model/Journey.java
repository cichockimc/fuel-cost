package uk.co.cichocki.fuelcost.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class Journey {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-M-d")
    @PastOrPresent(message = "Invalid date")
    LocalDate date;

    @NotNull
    FuelType fuelType;

    @NotNull
    @Valid
    SmartFloat mpg;

    @NotNull
    @Valid
    SmartFloat mileage;
}
