package uk.co.cichocki.fuelcost.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.cichocki.fuelcost.FuelCostApplication;
import uk.co.cichocki.fuelcost.model.PriceRecord;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FuelCostApplication.class)
public class FuelCostDataStoreTest {

    @Autowired
    FuelCostDataStore dataStore;

    @Test
    public void get1() {
        LocalDate date = LocalDate.of(2018, 1, 31);
        Optional<PriceRecord> maybePriceRecord = dataStore.get(date);
        assertTrue(maybePriceRecord.isPresent());
        PriceRecord priceRecord = maybePriceRecord.get();
        assertEquals(LocalDate.of(2018, 2, 5), priceRecord.getDate());
    }


    @Test
    public void get2() {
        LocalDate date = LocalDate.of(2007, 6, 23);
        Optional<PriceRecord> maybePriceRecord = dataStore.get(date);
        assertTrue(maybePriceRecord.isPresent());
        PriceRecord priceRecord = maybePriceRecord.get();
        assertEquals(LocalDate.of(2007, 6, 25), priceRecord.getDate());
    }


    @Test
    public void get3() {
        LocalDate date = LocalDate.of(2017, 1, 2);
        Optional<PriceRecord> maybePriceRecord = dataStore.get(date);
        assertTrue(maybePriceRecord.isPresent());
        PriceRecord priceRecord = maybePriceRecord.get();
        assertEquals(LocalDate.of(2017, 1, 9), priceRecord.getDate());
    }

    @Test
    public void getNotFound() {
        LocalDate date = LocalDate.of(2000, 1, 31);
        Optional<PriceRecord> maybeRecord = dataStore.get(date);
        assertFalse(maybeRecord.isPresent());
    }
}