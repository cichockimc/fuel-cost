package uk.co.cichocki.fuelcost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.cichocki.fuelcost.model.FuelCostResponse;
import uk.co.cichocki.fuelcost.model.Journey;
import uk.co.cichocki.fuelcost.service.FuelCostService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

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
        log.error("Handling HttpMessageNotReadableException");
        String message = Optional.ofNullable(ex.getCause()).map(t -> t.getClass().getSimpleName()).orElse("Invalid json");

        ErrorResponse errorResponse = new ErrorResponse("Problem occurred while processing message", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handling MethodArgumentNotValidException");
        var message = ex.getBindingResult().getAllErrors().stream().map(
                err -> err.getObjectName() + ": " + err.getDefaultMessage()
        ).collect(Collectors.joining(System.lineSeparator()));
        return new ResponseEntity<>(new ErrorResponse("Validation failed", message), HttpStatus.BAD_REQUEST);
    }
}
