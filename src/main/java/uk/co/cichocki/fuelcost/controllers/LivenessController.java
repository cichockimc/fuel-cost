package uk.co.cichocki.fuelcost.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.cichocki.fuelcost.debug.DebugResponse;

/**
 * Simple controller for liveness probe
 * readiness will be served by actuator health endpoint
 */
@RestController
public class LivenessController {

    @GetMapping("/isAlive")
    public DebugResponse isALive() {
        return new DebugResponse(0, "Up and ready");
    }
}
