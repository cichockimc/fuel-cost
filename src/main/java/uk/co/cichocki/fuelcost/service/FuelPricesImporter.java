package uk.co.cichocki.fuelcost.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import uk.co.cichocki.fuelcost.data.FuelCostDataStore;
import uk.co.cichocki.fuelcost.model.FuelData;
import uk.co.cichocki.fuelcost.model.PriceRecord;
import uk.co.cichocki.fuelcost.model.SmartFloat;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class FuelPricesImporter {

    private final FuelCostDataStore dataStore;

    public FuelPricesImporter(FuelCostDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        log.debug("About to import csv data");
        StopWatch stopWatch = new StopWatch("data-import");
        stopWatch.start();
        importData();
        stopWatch.stop();
        log.info("Data imported in {} milliseconds", stopWatch.getTotalTimeMillis());
    }

    // for simplicity, hardcoded path, separator; no custom error handling either
    private void importData() throws IOException {

        // todo mc to be revisited, java hates reading class path files
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("fuel-stats.csv").getInputStream()));
        try (Stream<String> lines = reader.lines()) {
            List<PriceRecord> data = lines
                    .map(this::lineToRecord)
                    .sorted(Comparator.comparing(PriceRecord::getDate))
                    .collect(Collectors.toList());
            dataStore.saveAll(data);
        }
    }

    /**
     * expected record format is
     * Date, ULSP Pump price, ULSP Duty rate, ULSP VAT (% rate),
     * ULSD Pump price, ULSD Duty rate, ULSD VAT (% rate)
     *
     * @param line data read from csv file
     * @return PriceRecord instance equivalent to parsed input data
     */
    PriceRecord lineToRecord(String line) {
        String[] split = line.split(",");
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("M/d/yyyy");
        FuelData petrolData = FuelData.builder()
                .pumpPrice(strToSmartFloat(split[1]))
                .dutyRate(strToSmartFloat(split[2]))
                .vatRate(strToSmartFloat(split[3]))
                .build();

        FuelData dieselData = FuelData.builder()
                .pumpPrice(strToSmartFloat(split[4]))
                .dutyRate(strToSmartFloat(split[5]))
                .vatRate(strToSmartFloat(split[6]))
                .build();

        return PriceRecord.builder()
                .date(LocalDate.parse(split[0], ft))
                .diesel(dieselData)
                .petrol(petrolData)
                .build();
    }

    private SmartFloat strToSmartFloat(String str) {
        return SmartFloat.of(Double.parseDouble(str));
    }
}
