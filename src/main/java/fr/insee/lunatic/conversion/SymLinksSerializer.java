package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.insee.lunatic.model.flat.SymLinksType;

import java.io.IOException;

public class SymLinksSerializer  extends StdSerializer<SymLinksType> {

    public SymLinksSerializer() {
        this(null);
    }

    public SymLinksSerializer(Class<SymLinksType> t) {
        super(t);
    }

    @Override
    public void serialize(
            SymLinksType symLinksType, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
            jgen.writeObjectFieldStart(symLinksType.getName());
            for(SymLinksType.LINK link : symLinksType.getLink()) {
                jgen.writeStringField(link.getSource(), link.getTarget());
            }
            jgen.writeEndObject();
        jgen.writeEndObject();
    }
}