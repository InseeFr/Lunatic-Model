package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.ComponentTypeEnum;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.ResponseType;
import fr.insee.lunatic.model.flat.Suggester;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class SuggesterSerializationTest {

    private final String jsonSuggester = """
            {
              "id": "questionnaire-id",
              "componentType": "Questionnaire",
              "components": [
                {
                  "id": "foo-id",
                  "componentType": "Suggester",
                  "storeName": "FOO_CODE_LIST",
                  "response": {
                    "name": "FOO"
                  }
                }
              ]
            }""";

    @Test
    void serializeSuggesterComponent() throws SerializationException, JSONException {
        //
        Suggester suggester = new Suggester();
        suggester.setId("foo-id");
        suggester.setComponentType(ComponentTypeEnum.SUGGESTER);
        suggester.setStoreName("FOO_CODE_LIST");
        suggester.setResponse(new ResponseType());
        suggester.getResponse().setName("FOO");
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        questionnaire.setComponentType(ComponentTypeEnum.QUESTIONNAIRE);
        questionnaire.getComponents().add(suggester);

        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);

        //
        JSONAssert.assertEquals(jsonSuggester, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeSuggesterComponent() throws SerializationException {
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonSuggester.getBytes()));
        //
        Suggester suggester = assertInstanceOf(Suggester.class, questionnaire.getComponents().getFirst());
        assertEquals("foo-id", suggester.getId());
        assertEquals(ComponentTypeEnum.SUGGESTER, suggester.getComponentType());
        assertEquals("FOO_CODE_LIST", suggester.getStoreName());
        assertEquals("FOO", suggester.getResponse().getName());
    }

}
