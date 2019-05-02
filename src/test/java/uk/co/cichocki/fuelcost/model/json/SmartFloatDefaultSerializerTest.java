package uk.co.cichocki.fuelcost.model.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.cichocki.fuelcost.model.SmartFloat;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JsonTest
public class SmartFloatDefaultSerializerTest {

    @Autowired
    ObjectMapper mapper;

    @Test
    public void serialize1() throws JsonProcessingException {
        String expected = "{\"value\":10.23}";
        SmartFloat sfloat = new SmartFloat(10.23);
        String result = mapper.writeValueAsString(sfloat);
        assertEquals(expected, result);
    }

    @Test
    public void serialize2() throws JsonProcessingException {
        String expected = "{\"value\":34}";
        SmartFloat sfloat = new SmartFloat(34);
        String result = mapper.writeValueAsString(sfloat);
        assertEquals(expected, result);
    }
}