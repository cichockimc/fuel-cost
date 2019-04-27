package uk.co.cichocki.fuelcost.debug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is for debug-only endpoints
 * not for production use, hence restricted to one spring profile
 */
@Profile("debug")
@RestController
@Slf4j
public class DebugController {
    @GetMapping("/dummy")
    public DebugResponse foo() {
        log.trace("Received debug get request");
        return DebugResponse.builder().message("I am alive").number(100).build();
    }

    @GetMapping("/problem")
    public DebugResponse problem() {
        throw new RuntimeException("bad things happened");
    }

}
