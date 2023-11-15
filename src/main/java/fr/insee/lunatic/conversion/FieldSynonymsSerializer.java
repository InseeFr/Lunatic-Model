package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.insee.lunatic.model.flat.FieldSynonym;
import fr.insee.lunatic.model.flat.FieldSynonyms;

import java.io.IOException;

public class FieldSynonymsSerializer extends StdSerializer<FieldSynonyms> {

    @SuppressWarnings("unused") // default constructor is required by jackson
    public FieldSynonymsSerializer() {
        this(null);
    }

    public FieldSynonymsSerializer(Class<FieldSynonyms> t) {
        super(t);
    }

    @Override
    public void serialize(FieldSynonyms fieldSynonyms, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        jsonGenerator.writeStartObject();
        for(FieldSynonym fieldSynonym : fieldSynonyms) {
            jsonGenerator.writeArrayFieldStart(fieldSynonym.getSource());
            for (String synonymString : fieldSynonym.getTarget()) {
                jsonGenerator.writeString(synonymString);
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }

}