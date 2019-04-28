package uk.co.cichocki.fuelcost.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import uk.co.cichocki.fuelcost.data.FuelCostDataStore;
import uk.co.cichocki.fuelcost.model.FuelData;
import uk.co.cichocki.fuelcost.model.PriceRecord;
import uk.co.cichocki.fuelcost.model.SmartFloat;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class FuelPricesImporter {

    final FuelCostDataStore dataStore;

    public FuelPricesImporter(FuelCostDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @PostConstruct
    public void postConstruct() throws IOException, URISyntaxException {
        log.debug("About to import csv data");
        StopWatch stopWatch = new StopWatch("data-import");
        stopWatch.start();
        importData();
        stopWatch.stop();
        log.info("Data imported in {} milliseconds", stopWatch.getTotalTimeMillis());
    }

    // for simplicity, hardcoded path, separator; no custom error handling either
    public void importData() throws URISyntaxException, IOException {

        Path path = Paths.get(ClassLoader.getSystemResource("fuel-stats.csv").toURI());
        try (Stream<String> lines = Files.lines(path).parallel()) {
            List<PriceRecord> data = lines
                    .map(this::lineToRecord)
                    .sorted(Comparator.comparing(PriceRecord::getDate))
                    .collect(Collectors.toList());
            dataStore.saveAll(data);
        }
    }

    public PriceRecord lineToRecord(String line) {
        // Date,ULSP Pump price,ULSP Duty rate,ULSP VAT (% rate),ULSD Pump price,ULSD Duty rate,ULSD VAT (% rate)
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
