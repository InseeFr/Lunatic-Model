package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.ComponentTypeName;
import fr.insee.lunatic.model.flat.Input;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ComponentsSerialisationTest {

    @Test
    void serializeInputComponent() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        Input input = new Input();
        input.setId("input-id");
        questionnaire.getComponents().add(input);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expectedJson = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "input-id",
                      "componentType": "Input"
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeInputComponent() throws SerializationException {
        //
        String jsonInput = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "input-id",
                      "componentType": "Input"
                    }
                  ]
                }""";

        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));

        //
        assertNotNull(questionnaire);
        assertEquals("questionnaire-id", questionnaire.getId());
        assertEquals(ComponentTypeName.QUESTIONNAIRE, questionnaire.getComponentTypeName());
        //
        assertEquals(1, questionnaire.getComponents().size());
        assertEquals("input-id", questionnaire.getComponents().getFirst().getId());
        assertEquals(ComponentTypeName.INPUT, questionnaire.getComponents().getFirst().getComponentTypeName());
    }

}
