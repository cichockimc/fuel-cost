package uk.co.cichocki.fuelcost.dummy;

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
public class DummyController {
    @GetMapping("/dummy")
    public DummyResponse foo() {
        log.trace("Received dummy get request");
        return DummyResponse.builder().message("I am alive").number(100).build();
    }

    @GetMapping("/problem")
    public DummyResponse problem() {
        String a = null;
        return DummyResponse.builder().message(a).build();
    }

}
