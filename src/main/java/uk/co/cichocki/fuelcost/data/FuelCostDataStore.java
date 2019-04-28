package uk.co.cichocki.fuelcost.data;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.co.cichocki.fuelcost.exception.MultipleRecordsMatchCriteria;
import uk.co.cichocki.fuelcost.model.PriceRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * this implementation is neither thread safe nor suitable for large data sets
 * this one is just tailored for the specific requirements
 */
@Component
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
        LinkedPriceRecord previous = new LinkedPriceRecord(null);
        list.forEach(record -> {
            LinkedPriceRecord linked = new LinkedPriceRecord(record);
            previous.setNext(linked);
            map.add(key(record), linked);
        });
    }

    @Override
    public Optional<PriceRecord> get(LocalDate criteria) throws MultipleRecordsMatchCriteria {
        return Optional.empty();
    }


    private Integer key(PriceRecord record) {
        return Integer.valueOf(record.getDate().format(formatter));
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
