package uk.co.cichocki.fuelcost;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Configuration
public class AppConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                df.setTimeZone(TimeZone.getTimeZone("UTC"));
                builder.dateFormat(df);
                builder.serializationInclusion(NON_NULL);
            }
        };
    }
}
