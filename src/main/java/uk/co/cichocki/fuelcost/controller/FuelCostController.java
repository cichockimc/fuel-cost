package uk.co.cichocki.fuelcost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.cichocki.fuelcost.model.FuelCostResponse;
import uk.co.cichocki.fuelcost.model.Journey;
import uk.co.cichocki.fuelcost.service.FuelCostService;

import javax.validation.Valid;

@RestController
@Slf4j
public class FuelCostController {

    private final FuelCostService service;

    public FuelCostController(FuelCostService service) {
        this.service = service;
    }

    @PostMapping("/journey")
    public FuelCostResponse journey(@Valid @RequestBody Journey journey) {
        return service.calculateCost(journey);
    }

}
