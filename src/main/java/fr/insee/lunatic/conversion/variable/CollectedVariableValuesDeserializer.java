package fr.insee.lunatic.conversion.variable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.insee.lunatic.model.flat.variable.CollectedVariableValues;
import fr.insee.lunatic.model.flat.variable.ValueType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fr.insee.lunatic.conversion.variable.ValueTypeDeserializer.deserializeJsonValue;

public class CollectedVariableValuesDeserializer extends StdDeserializer<CollectedVariableValues> {

    private static final String COLLECTED_KEY = "COLLECTED";
    private static final String FORCED_KEY = "FORCED";
    private static final String EDITED_KEY = "EDITED";
    private static final String PREVIOUS_KEY = "PREVIOUS";
    private static final String INPUTTED_KEY = "INPUTTED";

    public CollectedVariableValuesDeserializer() {
        this(null);
    }

    CollectedVariableValuesDeserializer(Class<CollectedVariableValues> valuesClass) {
        super(valuesClass);
    }

    @Override
    public CollectedVariableValues deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode valuesNode = jsonParser.getCodec().readTree(jsonParser);
        if (! valuesNode.isObject())
            throw new JsonParseException("Collected variable 'values' must be an object.");
        // If the object is empty, we cannot infer the type of values, return null
        if (valuesNode.isEmpty())
            return null;
        // Otherwise, we look at one of the properties to infer the values type
        JsonNode collectedNode = valuesNode.get(COLLECTED_KEY);
        if (collectedNode.isValueNode())
            return deserializeScalarValues(valuesNode);
        if (isOneDimensionArray(collectedNode))
            return deserializeArrayValues(valuesNode);
        if (isTwoDimensionsArray(collectedNode))
            return deserializeDoubleArrayValues(valuesNode);
        throw new JsonParseException("Invalid content in collected variable 'values'.");
    }

    static boolean isOneDimensionArray(JsonNode jsonNode) {
        if (!jsonNode.isArray())
            return false;
        if (jsonNode.isArray() && jsonNode.isEmpty())
            return true;
        return jsonNode.get(0).isValueNode();
    }

    static boolean isTwoDimensionsArray(JsonNode jsonNode) {
        if (!jsonNode.isArray())
            return false;
        if (jsonNode.isArray() && jsonNode.isEmpty())
            return false;
        return jsonNode.get(0).isArray();
    }

    private CollectedVariableValues.Scalar deserializeScalarValues(JsonNode valuesNode) throws JsonParseException {
        CollectedVariableValues.Scalar collectedVariableValues = new CollectedVariableValues.Scalar();
        collectedVariableValues.setCollected(deserializeJsonValue(valuesNode.get(COLLECTED_KEY)));
        collectedVariableValues.setCollected(deserializeJsonValue(valuesNode.get(FORCED_KEY)));
        collectedVariableValues.setCollected(deserializeJsonValue(valuesNode.get(EDITED_KEY)));
        collectedVariableValues.setCollected(deserializeJsonValue(valuesNode.get(PREVIOUS_KEY)));
        collectedVariableValues.setCollected(deserializeJsonValue(valuesNode.get(INPUTTED_KEY)));
        return collectedVariableValues;
    }

    private CollectedVariableValues.Array deserializeArrayValues(JsonNode valuesNode) throws JsonParseException {
        CollectedVariableValues.Array collectedVariableValues = new CollectedVariableValues.Array();
        for (JsonNode jsonNode : valuesNode.get(COLLECTED_KEY)) {
            collectedVariableValues.getCollected().add(deserializeJsonValue(jsonNode));
        }
        for (JsonNode jsonNode : valuesNode.get(FORCED_KEY)) {
            collectedVariableValues.getForced().add(deserializeJsonValue(jsonNode));
        }
        for (JsonNode jsonNode : valuesNode.get(EDITED_KEY)) {
            collectedVariableValues.getEdited().add(deserializeJsonValue(jsonNode));
        }
        for (JsonNode jsonNode : valuesNode.get(PREVIOUS_KEY)) {
            collectedVariableValues.getPrevious().add(deserializeJsonValue(jsonNode));
        }
        for (JsonNode jsonNode : valuesNode.get(INPUTTED_KEY)) {
            collectedVariableValues.getInputted().add(deserializeJsonValue(jsonNode));
        }
        return collectedVariableValues;
    }

    private CollectedVariableValues.DoubleArray deserializeDoubleArrayValues(JsonNode valuesNode) throws JsonParseException {
        CollectedVariableValues.DoubleArray collectedVariableValues = new CollectedVariableValues.DoubleArray();

        collectedVariableValues.getCollected().clear(); // two dimension values are initialized with one empty list
        for (JsonNode jsonArray : valuesNode.get(COLLECTED_KEY)) {
            List<ValueType> valuesList = new ArrayList<>();
            for (JsonNode jsonNode : jsonArray) {
                valuesList.add(deserializeJsonValue(jsonNode));
            }
            collectedVariableValues.getCollected().add(valuesList);
        }

        collectedVariableValues.getForced().clear();
        for (JsonNode jsonArray : valuesNode.get(FORCED_KEY)) {
            List<ValueType> valuesList = new ArrayList<>();
            for (JsonNode jsonNode : jsonArray) {
                valuesList.add(deserializeJsonValue(jsonNode));
            }
            collectedVariableValues.getForced().add(valuesList);
        }

        collectedVariableValues.getEdited().clear();
        for (JsonNode jsonArray : valuesNode.get(EDITED_KEY)) {
            List<ValueType> valuesList = new ArrayList<>();
            for (JsonNode jsonNode : jsonArray) {
                valuesList.add(deserializeJsonValue(jsonNode));
            }
            collectedVariableValues.getEdited().add(valuesList);
        }

        collectedVariableValues.getPrevious().clear();
        for (JsonNode jsonArray : valuesNode.get(PREVIOUS_KEY)) {
            List<ValueType> valuesList = new ArrayList<>();
            for (JsonNode jsonNode : jsonArray) {
                valuesList.add(deserializeJsonValue(jsonNode));
            }
            collectedVariableValues.getPrevious().add(valuesList);
        }

        collectedVariableValues.getInputted().clear();
        for (JsonNode jsonArray : valuesNode.get(INPUTTED_KEY)) {
            List<ValueType> valuesList = new ArrayList<>();
            for (JsonNode jsonNode : jsonArray) {
                valuesList.add(deserializeJsonValue(jsonNode));
            }
            collectedVariableValues.getInputted().add(valuesList);
        }

        return collectedVariableValues;
    }

}
