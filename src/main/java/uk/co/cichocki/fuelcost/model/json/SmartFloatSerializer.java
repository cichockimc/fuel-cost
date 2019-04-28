package uk.co.cichocki.fuelcost.model.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import uk.co.cichocki.fuelcost.model.SmartFloat;

import java.io.IOException;

@JsonComponent
public class SmartFloatSerializer extends JsonSerializer<SmartFloat> {
    @Override
    public void serialize(SmartFloat smartFloat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeNumber(smartFloat.toString());
    }
}
