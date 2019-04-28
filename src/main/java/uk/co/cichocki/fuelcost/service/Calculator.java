package uk.co.cichocki.fuelcost.service;

import org.springframework.stereotype.Component;
import uk.co.cichocki.fuelcost.model.*;

@Component
public class Calculator {

    SmartFloat getGallons(SmartFloat miles, SmartFloat mpg) {
        return miles.divideBy(mpg);
    }

    SmartFloat gallonsToLitres(SmartFloat gallons) {
        return gallons.divideBy(SmartFloat.of(0.21997));
    }

    SmartFloat getLitres(SmartFloat miles, SmartFloat mpg) {
        return gallonsToLitres(getGallons(miles, mpg));
    }

    JourneyCost calculateCost(Journey journey, PriceRecord data) {
        SmartFloat litres = getLitres(journey.getMileage(), journey.getMpg());
        FuelData fuelData = data.getFuelData(journey.getFuelType());
        SmartFloat totalCost = litres.multiplyBy(fuelData.getPumpPrice());
        SmartFloat dutyPaid = litres.multiplyBy(fuelData.getDutyRate());
        return new JourneyCost(totalCost, dutyPaid);
    }
}
