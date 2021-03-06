# fuel-cost
Web service to calculate the fuel cost of a journey. The project is dockerised, cloud ready. 

## where the solution is hosted
Currently unavailable, due to free tier expiration issue. 
Used to be hosted in Google Cloud and was accessible from the Internet at `fuel-cost.cichocki.co.uk`
Endpoint: `/journey`.  
There is a plan to resurrect it :)

## Artifactory
[Bintray](https://bintray.com/cichockimc/fuel-cost/fuel-cost)


## build
The command below will run tests and build application
```
mvn clean install
```
## run locally
```
java -jar fuel-cost.jar
```

## What could be done with more time
- More tests, including manual testing
- Cucumber 
- Spring docs / Swagger
- Code coverage plugin
- Not ideal csv file loading could be improved
- Better error handling / error messages
- secure connection (https)
- small angularjs frontend would be a nice to have

### Examples
#### sample request
```json
{
	"date": "2017-01-02",
	"mpg": 128.01,
	"mileage": 116.2,
	"fuelType": "DIESEL"
}
```

#### sample response
```json
{
    "journey": {
        "date": "2017-01-02",
        "fuelType": "DIESEL",
        "mpg": 128.01,
        "mileage": 116.2
    },
    "cost": {
        "fuelCost": 5.01,
        "dutyPaid": 2.39
    },
    "difference": 0.52
}
```
