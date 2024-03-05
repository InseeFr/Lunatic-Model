package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class VariableSerializationTest {

    private final String simpleVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "COLLECTED",
                  "name": "SIMPLE_VAR",
                  "variableDimension": 0,
                  "values": {
                    "PREVIOUS": null,
                    "COLLECTED": null,
                    "FORCED": null,
                    "EDITED": null,
                    "INPUTED": null
                  }
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
                  "variableDimension": 1,
                  "values": {
                    "PREVIOUS": [],
                    "COLLECTED": [],
                    "FORCED": [],
                    "EDITED": [],
                    "INPUTED": []
                  }
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
                  "variableDimension": 2,
                  "values": {
                    "PREVIOUS": [[]],
                    "COLLECTED": [[]],
                    "FORCED": [[]],
                    "EDITED": [[]],
                    "INPUTED": [[]]
                  }
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
        VariableType simpleVariable = new VariableType();
        simpleVariable.setName("SIMPLE_VAR");
        simpleVariable.setVariableType(VariableTypeEnum.COLLECTED);
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
        VariableTypeArray loopVariable = new VariableTypeArray();
        loopVariable.setName("LOOP_VAR");
        loopVariable.setVariableType(VariableTypeEnum.COLLECTED);
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
        VariableTypeTwoDimensionsArray loopVariable = new VariableTypeTwoDimensionsArray();
        loopVariable.setName("PAIRWISE_VAR");
        loopVariable.setVariableType(VariableTypeEnum.COLLECTED);
        questionnaire.getVariables().add(loopVariable);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(pairwiseVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeVariable() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(simpleVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        VariableType variableType = assertInstanceOf(VariableType.class, questionnaire.getVariables().getFirst());
        assertEquals("SIMPLE_VAR", variableType.getName());
        assertEquals(VariableTypeEnum.COLLECTED, variableType.getVariableType());
        assertEquals(VariableDimension.SCALAR, variableType.getVariableDimension());
        assertNotNull(variableType.getValues());
    }

    @Test
    void deserializeVariableArray() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(loopVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        VariableTypeArray variableTypeArray = assertInstanceOf(VariableTypeArray.class,
                questionnaire.getVariables().getFirst());
        assertEquals("LOOP_VAR", variableTypeArray.getName());
        assertEquals(VariableTypeEnum.COLLECTED, variableTypeArray.getVariableType());
        assertEquals(VariableDimension.ARRAY, variableTypeArray.getVariableDimension());
        assertNotNull(variableTypeArray.getValues());
    }

    @Test
    void deserializeVariableTwoDimensionsArray() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(pairwiseVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        VariableTypeTwoDimensionsArray variableTypeTwoDimensionsArray = assertInstanceOf(
                VariableTypeTwoDimensionsArray.class, questionnaire.getVariables().getFirst());
        assertEquals("PAIRWISE_VAR", variableTypeTwoDimensionsArray.getName());
        assertEquals(VariableTypeEnum.COLLECTED, variableTypeTwoDimensionsArray.getVariableType());
        assertEquals(VariableDimension.DOUBLE_ARRAY, variableTypeTwoDimensionsArray.getVariableDimension());
        assertNotNull(variableTypeTwoDimensionsArray.getValues());
    }

}
