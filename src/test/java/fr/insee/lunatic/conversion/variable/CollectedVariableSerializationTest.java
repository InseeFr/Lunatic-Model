package fr.insee.lunatic.conversion.variable;

import fr.insee.lunatic.conversion.JsonDeserializer;
import fr.insee.lunatic.conversion.JsonSerializer;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.variable.CollectedVariableType;
import fr.insee.lunatic.model.flat.variable.CollectedVariableValues;
import fr.insee.lunatic.model.flat.variable.VariableTypeEnum;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class CollectedVariableSerializationTest {

    private final String simpleVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "COLLECTED",
                  "name": "SIMPLE_VAR",
                  "values": {"COLLECTED": null}
                }
              ]
            }""";

    private final String loopVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "COLLECTED",
                  "name": "LOOP_VAR",
                  "values": {"COLLECTED": []}
                }
              ]
            }""";

    private final String pairwiseVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "COLLECTED",
                  "name": "PAIRWISE_VAR",
                  "values": {"COLLECTED": [[]]}
                }
              ]
            }""";

    private final JsonSerializer jsonSerializer = new JsonSerializer();
    private final JsonDeserializer jsonDeserializer = new JsonDeserializer();

    @Test
    void serializeVariable() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        //
        CollectedVariableType simpleVariable = new CollectedVariableType();
        simpleVariable.setName("SIMPLE_VAR");
        simpleVariable.setValues(new CollectedVariableValues.Scalar());
        questionnaire.getVariables().add(simpleVariable);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(simpleVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeVariableArray() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        //
        CollectedVariableType loopVariable = new CollectedVariableType();
        loopVariable.setName("LOOP_VAR");
        loopVariable.setValues(new CollectedVariableValues.Array());
        questionnaire.getVariables().add(loopVariable);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(loopVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeVariableTwoDimensionsArray() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        //
        CollectedVariableType pairwiseVariable = new CollectedVariableType();
        pairwiseVariable.setName("PAIRWISE_VAR");
        pairwiseVariable.setValues(new CollectedVariableValues.DoubleArray());
        questionnaire.getVariables().add(pairwiseVariable);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(pairwiseVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeVariable_noValues() throws SerializationException {
        String jsonInput = """
                {
                  "componentType": "Questionnaire",
                  "variables": [
                    {
                      "variableType": "COLLECTED",
                      "name": "SIMPLE_VAR"
                    }
                  ]
                }""";
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        CollectedVariableType variableType = assertInstanceOf(CollectedVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals("SIMPLE_VAR", variableType.getName());
        assertEquals(VariableTypeEnum.COLLECTED, variableType.getVariableType());
        assertNull(variableType.getValues());
    }

    @Test
    void deserializeVariable_emptyValues() throws SerializationException {
        String jsonInput = """
                {
                  "componentType": "Questionnaire",
                  "variables": [
                    {
                      "variableType": "COLLECTED",
                      "name": "SIMPLE_VAR",
                      "values": {}
                    }
                  ]
                }""";
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        CollectedVariableType variableType = assertInstanceOf(CollectedVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals("SIMPLE_VAR", variableType.getName());
        assertEquals(VariableTypeEnum.COLLECTED, variableType.getVariableType());
        assertNull(variableType.getValues());
    }

    @Test
    void deserializeVariable() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(simpleVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        CollectedVariableType collectedVariableType = assertInstanceOf(
                CollectedVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals("SIMPLE_VAR", collectedVariableType.getName());
        assertEquals(VariableTypeEnum.COLLECTED, collectedVariableType.getVariableType());
        assertInstanceOf(CollectedVariableValues.Scalar.class, collectedVariableType.getValues());
    }

    @Test
    void deserializeVariableArray() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(loopVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        CollectedVariableType collectedVariableType = assertInstanceOf(
                CollectedVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals("LOOP_VAR", collectedVariableType.getName());
        assertEquals(VariableTypeEnum.COLLECTED, collectedVariableType.getVariableType());
        assertInstanceOf(CollectedVariableValues.Array.class, collectedVariableType.getValues());
    }

    @Test
    void deserializeVariableTwoDimensionsArray() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(pairwiseVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        CollectedVariableType collectedVariableType = assertInstanceOf(
                CollectedVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals("PAIRWISE_VAR", collectedVariableType.getName());
        assertEquals(VariableTypeEnum.COLLECTED, collectedVariableType.getVariableType());
        assertInstanceOf(CollectedVariableValues.DoubleArray.class, collectedVariableType.getValues());
    }

}
