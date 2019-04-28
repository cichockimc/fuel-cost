package uk.co.cichocki.fuelcost.service;

public class Calculator {

    double getGallons(int miles, double mpg) {
        double gallons = miles / mpg;
        return gallons;
    }
}
