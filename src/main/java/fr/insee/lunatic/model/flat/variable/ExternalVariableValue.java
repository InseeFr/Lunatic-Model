package fr.insee.lunatic.model.flat.variable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.insee.lunatic.conversion.variable.ExternalVariableValueDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object for external variables.
 * This is an abstract class since the value can have several forms in Lunatic.
 */
@JsonDeserialize(using = ExternalVariableValueDeserializer.class)
public abstract class ExternalVariableValue {

    private ExternalVariableValue() {}

    @Getter
    @Setter
    public static class Scalar extends ExternalVariableValue {
        @JsonValue
        ValueType scalarValue;
    }

    @Getter
    @Setter
    public static class Array extends ExternalVariableValue {
        @JsonValue
        List<ValueType> arrayValue = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class DoubleArray extends ExternalVariableValue {
        @JsonValue
        List<List<ValueType>> doubleArrayValue;

        public DoubleArray() {
            doubleArrayValue = new ArrayList<>();
            doubleArrayValue.add(new ArrayList<>());
        }
    }

}
