package uk.co.cichocki.fuelcost.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.cichocki.fuelcost.debug.DebugResponse;

/**
 * Simple controller for liveness probe
 * readiness will be served by actuator health endpoint
 */
@RestController
@Slf4j
public class LivenessController {

    @GetMapping("/isAlive")
    public DebugResponse isALive() {
        log.debug("isAlive endpoint called");
        return new DebugResponse("Up and ready");
    }
}
