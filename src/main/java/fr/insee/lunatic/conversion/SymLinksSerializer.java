package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.insee.lunatic.model.flat.SymLinksType;

import java.io.IOException;

public class SymLinksSerializer extends StdSerializer<SymLinksType> {

    public SymLinksSerializer() {
        this(null);
    }

    public SymLinksSerializer(Class<SymLinksType> t) {
        super(t);
    }

    @Override
    public void serialize(
            SymLinksType symLinksType, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart(symLinksType.getName());
        for(SymLinksType.LINK link : symLinksType.getLink()) {
            jsonGenerator.writeStringField(link.getSource(), link.getTarget());
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }

}