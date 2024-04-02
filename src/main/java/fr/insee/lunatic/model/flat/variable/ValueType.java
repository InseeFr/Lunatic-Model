package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.insee.lunatic.conversion.variable.ValueTypeDeserializer;

@JsonDeserialize(using = ValueTypeDeserializer.class)
public interface ValueType {

    record BooleanValue (@JsonValue Boolean value) implements ValueType {}
    record NumberValue(@JsonValue Integer value) implements ValueType {}
    record StringValue(@JsonValue String value) implements ValueType {}

}
