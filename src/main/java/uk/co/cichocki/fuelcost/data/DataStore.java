package uk.co.cichocki.fuelcost.data;

import uk.co.cichocki.fuelcost.exception.MultipleRecordsMatchCriteria;

import java.util.List;
import java.util.Optional;

/**
 * abstraction layer for data storage
 * currently, in memory solution is used, due to requirements:
 * - can't use external dependencies (standalone database is off the table then)
 * - input data set is relatively small (~800 records), no harm in storing it in memory
 */
public interface DataStore<MODEL, SEARCH> {

    void save(MODEL model);

    void saveAll(List<MODEL> list);

    Optional<MODEL> get(SEARCH criteria) throws MultipleRecordsMatchCriteria;
}
