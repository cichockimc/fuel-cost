package uk.co.cichocki.fuelcost.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FuelCostControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void journey() throws JSONException {
        String request = "{\n" +
                "  \"date\": \"2017-01-02\",\n" +
                "  \"mpg\": 128.01,\n" +
                "  \"mileage\": 116.2,\n" +
                "  \"fuelType\": \"DIESEL\"\n" +
                "}";
        String expected = "{\n" +
                "    \"journey\": {\n" +
                "        \"date\": \"2017-01-02\",\n" +
                "        \"fuelType\": \"DIESEL\",\n" +
                "        \"mpg\": 128.01,\n" +
                "        \"mileage\": 116.2\n" +
                "    },\n" +
                "    \"cost\": {\n" +
                "        \"fuelCost\": 5.01,\n" +
                "        \"dutyPaid\": 2.39\n" +
                "    },\n" +
                "    \"difference\": 0.52\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity httpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(
                "http://localhost:" + this.port + "/journey", httpEntity, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        JSONAssert.assertEquals(expected, entity.getBody(), JSONCompareMode.STRICT);
    }
}