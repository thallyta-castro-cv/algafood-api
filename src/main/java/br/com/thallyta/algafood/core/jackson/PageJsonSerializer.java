package br.com.thallyta.algafood.core.jackson;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;


@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>>{

    @Override
    public void serialize(Page<?> page, com.fasterxml.jackson.core.JsonGenerator jsonGenerator,
                          com.fasterxml.jackson.databind.SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("content", page.getContent());
        jsonGenerator.writeNumberField("size", page.getSize());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("number", page.getNumber());

        jsonGenerator.writeEndObject();
    }
}
