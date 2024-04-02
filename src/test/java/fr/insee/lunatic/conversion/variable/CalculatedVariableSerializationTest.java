package fr.insee.lunatic.conversion.variable;

import fr.insee.lunatic.conversion.JsonDeserializer;
import fr.insee.lunatic.conversion.JsonSerializer;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.variable.CalculatedVariableType;
import fr.insee.lunatic.model.flat.variable.VariableTypeEnum;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CalculatedVariableSerializationTest {

    private final String calculatedVarJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "CALCULATED",
                  "name": "FOO_CALCULATED_VAR",
                  "expression": {
                    "value": "VAR1 + VAR2",
                    "type": "VTL"
                  },
                  "bindingDependencies": [
                    "VAR1",
                    "VAR2"
                  ]
                }
              ]
            }""";

    private final String calculatedVarWithShapeJson = """
            {
              "componentType": "Questionnaire",
              "variables": [
                {
                  "variableType": "CALCULATED",
                  "name": "FOO_CALCULATED_VAR",
                  "expression": {
                    "value": "<VTL expression>",
                    "type": "VTL"
                  },
                  "shapeFrom": "SOME_COLLECTED_VARIABLE"
                }
              ]
            }""";

    private final JsonSerializer jsonSerializer = new JsonSerializer();
    private final JsonDeserializer jsonDeserializer = new JsonDeserializer();

    @Test
    void serializeCalculatedVariable() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        CalculatedVariableType calculatedVariableType = new CalculatedVariableType();
        calculatedVariableType.setName("FOO_CALCULATED_VAR");
        calculatedVariableType.setExpression(new LabelType());
        calculatedVariableType.getExpression().setValue("VAR1 + VAR2");
        calculatedVariableType.getExpression().setType(LabelTypeEnum.VTL);
        calculatedVariableType.getBindingDependencies().addAll(List.of("VAR1", "VAR2"));
        questionnaire.getVariables().add(calculatedVariableType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(calculatedVarJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeCalculatedVariable_withShapeFrom() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        CalculatedVariableType calculatedVariableType = new CalculatedVariableType();
        calculatedVariableType.setName("FOO_CALCULATED_VAR");
        calculatedVariableType.setExpression(new LabelType());
        calculatedVariableType.getExpression().setValue("<VTL expression>");
        calculatedVariableType.getExpression().setType(LabelTypeEnum.VTL);
        calculatedVariableType.setShapeFrom("SOME_COLLECTED_VARIABLE");
        questionnaire.getVariables().add(calculatedVariableType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(calculatedVarWithShapeJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeCalculatedVariable() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                new ByteArrayInputStream(calculatedVarJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        CalculatedVariableType calculatedVariableType = assertInstanceOf(CalculatedVariableType.class,
                questionnaire.getVariables().getFirst());
        assertEquals(VariableTypeEnum.CALCULATED, calculatedVariableType.getVariableType());
        assertEquals("FOO_CALCULATED_VAR", calculatedVariableType.getName());
        assertEquals("VAR1 + VAR2", calculatedVariableType.getExpression().getValue());
        assertEquals(LabelTypeEnum.VTL, calculatedVariableType.getExpression().getTypeEnum());
        assertEquals(List.of("VAR1", "VAR2"), calculatedVariableType.getBindingDependencies());
    }

    @Test
    void deserializeCalculatedVariable_withShapeFrom() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                new ByteArrayInputStream(calculatedVarWithShapeJson.getBytes()));
        //
        assertEquals(1, questionnaire.getVariables().size());
        CalculatedVariableType calculatedVariableType = assertInstanceOf(CalculatedVariableType.class,
                questionnaire.getVariables().getFirst());
        assertEquals("SOME_COLLECTED_VARIABLE", calculatedVariableType.getShapeFrom());
    }

}
