package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class VariableValuesSerializationTest {

    @Test
    void serializeVariableWithNullValues() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        //
        VariableType simpleVariable = new VariableType();
        simpleVariable.setName("SIMPLE_VAR");
        simpleVariable.setVariableType(VariableTypeEnum.COLLECTED);
        simpleVariable.setValues(new ValuesType());
        questionnaire.getVariables().add(simpleVariable);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expectedJson = """
                {
                  "variables": [
                    {
                      "variableType": "COLLECTED",
                      "name": "SIMPLE_VAR",
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
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeVariableArrayWithEmptyValues() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        //
        VariableTypeArray loopVariable = new VariableTypeArray();
        loopVariable.setName("LOOP_VAR");
        loopVariable.setVariableType(VariableTypeEnum.COLLECTED);
        loopVariable.setValues(new ValuesTypeArray());
        questionnaire.getVariables().add(loopVariable);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expectedJson = """
                {
                  "variables": [
                    {
                      "variableType": "COLLECTED",
                      "name": "LOOP_VAR",
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
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
