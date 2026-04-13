package fr.insee.lunatic.conversion.variable;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.lunatic.conversion.JsonDeserializer;
import fr.insee.lunatic.conversion.JsonSerializer;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.variable.ExternalVariableType;
import fr.insee.lunatic.model.flat.variable.ExternalVariableValue;
import fr.insee.lunatic.model.flat.variable.VariableTypeEnum;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ExternalVariableSerializationTest {

    private final String scalarExternalVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "EXTERNAL",
                  "name": "SCALAR_EXTERNAL_VAR",
                  "value": null
                }
              ]
            }""";

    private final String loopExternalVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "EXTERNAL",
                  "name": "LOOP_EXTERNAL_VAR",
                  "value": []
                }
              ]
            }""";

    private final String pairwiseExternalVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "EXTERNAL",
                  "name": "PAIRWISE_EXTERNAL_VAR",
                  "value": [[]]
                }
              ]
            }""";

    private final JsonSerializer jsonSerializer = new JsonSerializer();
    private final JsonDeserializer jsonDeserializer = new JsonDeserializer();

    @Test
    void serializeScalarExternalVariable() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        ExternalVariableType externalVariableType = new ExternalVariableType();
        externalVariableType.setName("SCALAR_EXTERNAL_VAR");
        externalVariableType.setValue(new ExternalVariableValue.Scalar());
        questionnaire.getVariables().add(externalVariableType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(scalarExternalVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeArrayExternalVariable() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        ExternalVariableType externalVariableType = new ExternalVariableType();
        externalVariableType.setName("LOOP_EXTERNAL_VAR");
        externalVariableType.setValue(new ExternalVariableValue.Array());
        questionnaire.getVariables().add(externalVariableType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(loopExternalVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeDoubleArrayExternalVariable() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        ExternalVariableType externalVariableType = new ExternalVariableType();
        externalVariableType.setName("PAIRWISE_EXTERNAL_VAR");
        externalVariableType.setValue(new ExternalVariableValue.DoubleArray());
        questionnaire.getVariables().add(externalVariableType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(pairwiseExternalVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeScalarExternalVariable() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                new ByteArrayInputStream(scalarExternalVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        ExternalVariableType externalVariableType = assertInstanceOf(
                ExternalVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals(VariableTypeEnum.EXTERNAL, externalVariableType.getVariableType());
        assertEquals("SCALAR_EXTERNAL_VAR", externalVariableType.getName());
        assertNull(externalVariableType.getValue());
    }

    @Test
    void deserializeArrayExternalVariable() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                new ByteArrayInputStream(loopExternalVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        ExternalVariableType externalVariableType = assertInstanceOf(
                ExternalVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals(VariableTypeEnum.EXTERNAL, externalVariableType.getVariableType());
        assertEquals("LOOP_EXTERNAL_VAR", externalVariableType.getName());
        assertInstanceOf(ExternalVariableValue.Array.class, externalVariableType.getValue());
    }

    @Test
    void deserializeDoubleArrayExternalVariable() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                new ByteArrayInputStream(pairwiseExternalVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        ExternalVariableType externalVariableType = assertInstanceOf(
                ExternalVariableType.class, questionnaire.getVariables().getFirst());
        assertEquals(VariableTypeEnum.EXTERNAL, externalVariableType.getVariableType());
        assertEquals("PAIRWISE_EXTERNAL_VAR", externalVariableType.getName());
        assertInstanceOf(ExternalVariableValue.DoubleArray.class, externalVariableType.getValue());
    }

    @Test
    void serializeIsDeletedOnReset() throws JsonProcessingException, JSONException {
        ExternalVariableType externalVariableType = new ExternalVariableType();
        // Default case: null
        JSONAssert.assertEquals("""
                {
                  "variableType": "EXTERNAL"
                }""",
                jsonSerializer.getMapper().writeValueAsString(externalVariableType),
                JSONCompareMode.STRICT);
        // True value
        externalVariableType.setIsDeletedOnReset(true);
        JSONAssert.assertEquals("""
                {
                  "variableType": "EXTERNAL",
                  "isDeletedOnReset": true
                }""",
                jsonSerializer.getMapper().writeValueAsString(externalVariableType),
                JSONCompareMode.STRICT);
        // False value
        externalVariableType.setIsDeletedOnReset(false);
        JSONAssert.assertEquals("""
                {
                  "variableType": "EXTERNAL",
                  "isDeletedOnReset": false
                }""",
                jsonSerializer.getMapper().writeValueAsString(externalVariableType),
                JSONCompareMode.STRICT);
    }

    @Test
    void deserializeIsDeletedOnReset() throws JsonProcessingException, JSONException {
        ExternalVariableType external1 = jsonDeserializer.getMapper().readValue("""
                {
                  "variableType": "EXTERNAL"
                }""", ExternalVariableType.class);
        assertNull(external1.getIsDeletedOnReset());

        ExternalVariableType external2 = jsonDeserializer.getMapper().readValue("""
                {
                  "variableType": "EXTERNAL",
                  "isDeletedOnReset": true
                }""", ExternalVariableType.class);
        assertTrue(external2.getIsDeletedOnReset());

        ExternalVariableType external3 = jsonDeserializer.getMapper().readValue("""
                {
                  "variableType": "EXTERNAL",
                  "isDeletedOnReset": false
                }""", ExternalVariableType.class);
        assertFalse(external3.getIsDeletedOnReset());
    }

}
