# fuel-cost
Web service to calculate the fuel cost of a journey. The project is dockerised, cloud ready. 
No external dependencies are required to run it. 
note: I'd call it early beta version. 

## where the solution is hosted
It is hosted in Google Cloud. It is accessible from the Internet at `fuel-cost.cichocki.co.uk`
Endpoint: `/journey`.  

Sample request:
```
curl fuel-cost.cichocki.co.uk -d '{"date": "2017-01-02","mpg": 128.01,"mileage": 116.2,"fuelType": "DIESEL"}'
```

## build
The command below will run tests and build application
```
mvn clean install
```
## run locally
```
java -jar fuel-cost-1.0.0.jar
```

## What could be done with more time
More tests, including manual testing
Cucumber 
Spring docs / Swagger
Code coverage plugin
Not ideal csv file loading could be improved
Better error handling / error messages

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
        "fuelCost": 500.56,
        "dutyPaid": 239.14
    },
    "difference": 52.37
}
```