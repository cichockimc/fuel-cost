package uk.co.cichocki.fuelcost.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.co.cichocki.fuelcost.model.PriceRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * this implementation is neither thread safe nor suitable for large data sets
 * this one is just tailored for the specific requirements
 */
@Component
@Slf4j
public class FuelCostDataStore implements DataStore<PriceRecord, LocalDate> {
    private final LinkedMultiValueMap<Integer, LinkedPriceRecord> map = new LinkedMultiValueMap<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public void save(PriceRecord priceRecord) {
        throw new NotImplementedException();
    }

    @Override
    public void saveAll(List<PriceRecord> list) {
        if (!map.isEmpty()) {
            throw new IllegalStateException("This data store implementation is supposed to be immutable");
        }
        Optional<LinkedPriceRecord> reduced = list.stream().map(record -> {
            LinkedPriceRecord linked = new LinkedPriceRecord(record);
            map.add(key(record), linked);
            return linked;
        }).reduce((a, b) -> {
            a.next = b;
            return b;
        });
        log.debug("Saved {} records, last element: {}", map.size(), reduced);
    }

    // this method could be done better
    @Override
    public Optional<PriceRecord> get(LocalDate criteria) {
        log.trace("looking for {}", criteria);
        if (Objects.isNull(criteria)) {
            log.warn("Null criteria");
            return Optional.empty();
        }
        Integer key = key(criteria);
        log.trace("Using key {}", key);
        List<LinkedPriceRecord> list = map.get(key);
        if (Objects.isNull(list)) {
            log.warn("Key {} not found", key);
            return Optional.empty();
        }
        Optional<LinkedPriceRecord> maybeRecord = maybeLastElement(list.stream().filter(a -> a.self.getDate().isAfter(criteria)));
        if (maybeRecord.isPresent()) {
            Optional<PriceRecord> self = Optional.ofNullable(maybeRecord.get().self);
            log.debug("Returning {}", self);
            return self;
        }
        LinkedPriceRecord potentialResult = maybeLastElement(list.stream()).orElseThrow(() -> {
            String message = "Last element not found in list. This should not happen";
            log.error(message);
            return new RuntimeException(message);
        }).next;
        log.trace("potential result {}", potentialResult);
        Optional<PriceRecord> result = Optional.ofNullable(potentialResult).map(LinkedPriceRecord::getSelf);
        log.debug("returning {}", result);
        return result;
    }

    private <T> Optional<T> maybeLastElement(Stream<T> stream) {
        return stream.reduce((first, second) -> second);
    }

    private Integer key(PriceRecord record) {
        return key(record.getDate());
    }

    private Integer key(LocalDate date) {
        return Integer.valueOf(date.format(formatter));
    }

    @Data
    private class LinkedPriceRecord {
        PriceRecord self;
        LinkedPriceRecord next;

        private LinkedPriceRecord(PriceRecord self) {
            this.self = self;
        }
    }
}
