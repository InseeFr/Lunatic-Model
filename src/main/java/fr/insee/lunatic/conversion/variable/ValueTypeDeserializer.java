package fr.insee.lunatic.conversion.variable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.insee.lunatic.model.flat.variable.ValueType;

import java.io.IOException;

public class ValueTypeDeserializer extends StdDeserializer<ValueType> {

    public ValueTypeDeserializer() {
        this(null);
    }

    ValueTypeDeserializer(Class<ValueType> valueTypeClass) {
        super(valueTypeClass);
    }

    @Override
    public ValueType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        return deserializeJsonValue(jsonNode);
    }

    static ValueType deserializeJsonValue(JsonNode jsonValue) throws JsonParseException {
        return switch (jsonValue.getNodeType()) {
            case ARRAY, MISSING, OBJECT, POJO ->
                    throw new JsonParseException("Invalid variable value of type " + jsonValue.getNodeType());
            case BINARY -> throw new JsonParseException("Binary json data is not supported by Lunatic-Model.");
            case BOOLEAN -> new ValueType.BooleanValue(jsonValue.booleanValue());
            case NUMBER -> new ValueType.NumberValue(jsonValue.intValue());
            case STRING -> new ValueType.StringValue(jsonValue.textValue());
            case NULL -> null;
        };
    }

}
