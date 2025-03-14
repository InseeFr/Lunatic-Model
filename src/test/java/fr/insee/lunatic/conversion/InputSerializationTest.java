package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Input;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.ResponseType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputSerializationTest {

    private final JsonSerializer serializer = new JsonSerializer();
    private final JsonDeserializer deserializer = new JsonDeserializer();

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void input_withMandatory(Boolean mandatory) throws SerializationException, JSONException {
        //
        Input input = new Input();
        input.setId("input-id");
        input.setMandatory(mandatory);
        input.setResponse(new ResponseType());
        input.getResponse().setName("INPUT_VAR");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        questionnaire.getComponents().add(input);
        //
        String result = serializer.serialize(questionnaire);
        //
        String expected = String.format("""
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "input-id",
                      "componentType": "Input",
                      "isMandatory": %s,
                      "response": {
                        "name": "INPUT_VAR"
                      }
                    }
                  ]
                }""", mandatory);
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void input_withoutMandatory() throws SerializationException, JSONException {
        //
        Input input = new Input();
        input.setId("input-id");
        input.setResponse(new ResponseType());
        input.getResponse().setName("INPUT_VAR");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        questionnaire.getComponents().add(input);
        //
        String result = serializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "input-id",
                      "componentType": "Input",
                      "response": {
                        "name": "INPUT_VAR"
                      }
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void deserializeInput_withMandatory(Boolean mandatory) throws SerializationException {
        //
        String jsonInput = String.format("""
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "input-id",
                      "componentType": "Input",
                      "isMandatory": %s,
                      "response": {
                        "name": "INPUT_VAR"
                      }
                    }
                  ]
                }""", mandatory);
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        Input input = assertInstanceOf(Input.class, questionnaire.getComponents().getFirst());
        assertEquals(mandatory, input.getMandatory());
    }

    @Test
    void deserializeInput_withoutMandatory() throws SerializationException {
        //
        String jsonInput = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "input-id",
                      "componentType": "Input",
                      "response": {
                        "name": "INPUT_VAR"
                      }
                    }
                  ]
                }""";
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        Input input = assertInstanceOf(Input.class, questionnaire.getComponents().getFirst());
        assertNull(input.getMandatory());
    }

}
