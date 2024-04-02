package fr.insee.lunatic.conversion.variable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.insee.lunatic.model.flat.variable.ExternalVariableValue;
import fr.insee.lunatic.model.flat.variable.ValueType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fr.insee.lunatic.conversion.variable.CollectedVariableValuesDeserializer.isOneDimensionArray;
import static fr.insee.lunatic.conversion.variable.CollectedVariableValuesDeserializer.isTwoDimensionsArray;
import static fr.insee.lunatic.conversion.variable.ValueTypeDeserializer.deserializeJsonValue;

public class ExternalVariableValueDeserializer extends StdDeserializer<ExternalVariableValue> {

    public ExternalVariableValueDeserializer() {
        this(null);
    }

    ExternalVariableValueDeserializer(Class<ExternalVariableValue> valueClass) {
        super(valueClass);
    }

    @Override
    public ExternalVariableValue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode valueNode = jsonParser.getCodec().readTree(jsonParser);
        if (valueNode.isValueNode())
            return deserializeScalarValue(valueNode);
        if (isOneDimensionArray(valueNode))
            return deserializeArrayValue(valueNode);
        if (isTwoDimensionsArray(valueNode))
            return deserializeDoubleArrayValue(valueNode);
        throw new JsonParseException("Invalid content in collected variable 'values'.");
    }

    private ExternalVariableValue.Scalar deserializeScalarValue(JsonNode valueNode) throws JsonParseException {
        ExternalVariableValue.Scalar externalVariableValue = new ExternalVariableValue.Scalar();
        externalVariableValue.setScalarValue(deserializeJsonValue(valueNode));
        return externalVariableValue;
    }

    private ExternalVariableValue.Array deserializeArrayValue(JsonNode valueNode) throws JsonParseException {
        ExternalVariableValue.Array externalVariableValue = new ExternalVariableValue.Array();
        for (JsonNode jsonNode : valueNode) {
            externalVariableValue.getArrayValue().add(deserializeJsonValue(jsonNode));
        }
        return externalVariableValue;
    }

    private ExternalVariableValue.DoubleArray deserializeDoubleArrayValue(JsonNode valueNode) throws JsonParseException {
        ExternalVariableValue.DoubleArray externalVariableValue = new ExternalVariableValue.DoubleArray();

        externalVariableValue.getDoubleArrayValue().clear(); // two dimension value is initialized with one empty list
        for (JsonNode jsonArray : valueNode) {
            List<ValueType> valuesList = new ArrayList<>();
            for (JsonNode jsonNode : jsonArray) {
                valuesList.add(deserializeJsonValue(jsonNode));
            }
            externalVariableValue.getDoubleArrayValue().add(valuesList);
        }

        return externalVariableValue;
    }

}
