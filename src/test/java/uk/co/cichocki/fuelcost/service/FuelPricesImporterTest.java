package uk.co.cichocki.fuelcost.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.cichocki.fuelcost.data.FuelCostDataStore;
import uk.co.cichocki.fuelcost.model.FuelData;
import uk.co.cichocki.fuelcost.model.PriceRecord;
import uk.co.cichocki.fuelcost.model.SmartFloat;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FuelPricesImporterTest {

    @Mock
    FuelCostDataStore dataStore;

    private FuelPricesImporter importer;

    @Before
    public void setup() {
        importer = new FuelPricesImporter(dataStore);
    }

    @Test
    public void lineToRecordHappyPath() {
        // 4/23/2018,
        // 121.44,57.95,20,
        // 125.39,57.95,20
        String line = "4/23/2018,121.44,57.95,20,125.39,57.95,20";
        PriceRecord expected = PriceRecord.builder()
                .date(LocalDate.of(2018, 4, 23))
                .petrol(
                        FuelData.builder()
                                .pumpPrice(SmartFloat.of(121.44))
                                .dutyRate(SmartFloat.of(57.95))
                                .vatRate(SmartFloat.of(20))
                                .build()
                )
                .diesel(
                        FuelData.builder()
                                .pumpPrice(SmartFloat.of(125.39))
                                .dutyRate(SmartFloat.of(57.95))
                                .vatRate(SmartFloat.of(20))
                                .build()
                )
                .build();
        PriceRecord result = importer.lineToRecord(line);
        assertEquals(expected, result);

    }
}