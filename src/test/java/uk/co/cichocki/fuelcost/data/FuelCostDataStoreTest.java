package uk.co.cichocki.fuelcost.data;

import org.junit.Before;
import org.junit.Test;
import uk.co.cichocki.fuelcost.exception.MultipleRecordsMatchCriteria;
import uk.co.cichocki.fuelcost.model.PriceRecord;
import uk.co.cichocki.fuelcost.service.FuelPricesImporter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class FuelCostDataStoreTest {

    private FuelCostDataStore dataStore = new FuelCostDataStore();

    @Before
    public void setup() throws IOException, URISyntaxException {
        // this line makes this test far from definition of unit testing (time restrictions, need to carry on)
        FuelPricesImporter importer = new FuelPricesImporter(dataStore);
        importer.importData();
    }

    @Test
    public void get() throws MultipleRecordsMatchCriteria {
        LocalDate date = LocalDate.of(2018, 1, 31);
        Optional<PriceRecord> maybePriceRecord = dataStore.get(date);
        assertTrue(maybePriceRecord.isPresent());
        PriceRecord priceRecord = maybePriceRecord.get();
        assertEquals(LocalDate.of(2018, 2, 5), priceRecord.getDate());
    }


    @Test
    public void getNotFound() throws MultipleRecordsMatchCriteria {
        LocalDate date = LocalDate.of(2000, 1, 31);
        Optional<PriceRecord> maybeRecord = dataStore.get(date);
        assertFalse(maybeRecord.isPresent());
    }
}