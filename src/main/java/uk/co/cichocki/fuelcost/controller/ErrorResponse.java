package uk.co.cichocki.fuelcost.controller;

import lombok.Value;

@Value
class ErrorResponse {
    String message;
    String exception;
}
