package uk.co.cichocki.fuelcost.data;

import java.util.List;
import java.util.Optional;

/**
 * abstraction layer for data storage
 */
public interface DataStore<MODEL, SEARCH> {

    void save(MODEL model);

    void saveAll(List<MODEL> list);

    Optional<MODEL> get(SEARCH criteria);
}
