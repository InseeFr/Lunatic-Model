package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DropdownSerializationTest {

    @Test
    void serializeDropdown_dynamicOptions() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        Dropdown dropdown = new Dropdown();
        dropdown.setId("dropdown-dyn-id");
        dropdown.setOptionSource("FIRST_NAME");
        dropdown.setOptionFilter(new OptionFilter("VTL", "AGE >= 18", "FIRST_NAME"));
        dropdown.setResponse(new ResponseType());
        dropdown.getResponse().setName("FOO");
        questionnaire.getComponents().add(dropdown);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        // "options" is always serialized in the Lunatic model.
        // The static vs dynamic choice is handled by business logic upstream.
        String expectedJson = """
            {
              "id": "questionnaire-id",
              "componentType": "Questionnaire",
              "components": [
                {
                  "id": "dropdown-dyn-id",
                  "componentType": "Dropdown",
                  "options": [],
                  "optionSource": "FIRST_NAME",
                  "optionFilter": {
                    "type": "VTL",
                    "value": "AGE >= 18",
                    "shapeFrom": "FIRST_NAME"
                  },
                  "response": {
                    "name": "FOO"
                  }
                }
              ]
            }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeDropdown_dynamicOptions() throws SerializationException {
        //
        String jsonInput = """
            {
              "id": "questionnaire-id",
              "componentType": "Questionnaire",
              "components": [
                {
                  "id": "dropdown-dyn-id",
                  "componentType": "Dropdown",
                  "optionSource": "FIRST_NAME",
                  "optionFilter": {
                    "type": "VTL",
                    "value": "AGE >= 18",
                    "shapeFrom": "FIRST_NAME"
                  },
                  "response": {
                    "name": "FOO"
                  }
                }
              ]
            }""";
        //
        JsonDeserializer deserializer = new JsonDeserializer();
        Questionnaire questionnaire = deserializer.deserialize(
                new ByteArrayInputStream(jsonInput.getBytes())
        );
        //
        Dropdown dropdown =
                assertInstanceOf(Dropdown.class, questionnaire.getComponents().getFirst());
        assertEquals("FIRST_NAME", dropdown.getOptionSource());
        assertInstanceOf(OptionFilter.class, dropdown.getOptionFilter());
        assertEquals("VTL", dropdown.getOptionFilter().getType());
        assertEquals("AGE >= 18", dropdown.getOptionFilter().getValue());
        assertEquals("FIRST_NAME", dropdown.getOptionFilter().getShapeFrom());
    }

}