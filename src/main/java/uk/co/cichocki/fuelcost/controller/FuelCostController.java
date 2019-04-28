package uk.co.cichocki.fuelcost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    /**
     * stack traces like json mapping problems usually expose implementation details
     * it's better to be less informative
     * just a sample, it would be good to create ControllerAdvice class
     */
    @SuppressWarnings("unused") // intellij annoyance
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Handling HttpMessageNotReadableException", ex);
        ErrorResponse errorResponse = new ErrorResponse("Problem occurred while processing message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
