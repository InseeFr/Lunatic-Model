package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.SuggesterType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuggesterTypeSerializationTest {

    private final String jsonSuggesters = """
            {
              "id": "questionnaire-id",
              "componentType": "Questionnaire",
              "suggesters": [
                {
                  "fields": [],
                  "version": 1
                }
              ]
            }""";

    @Test
    void serializeSuggesters() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        SuggesterType suggesterType = new SuggesterType();
        suggesterType.setVersion(BigInteger.ONE);
        questionnaire.getSuggesters().add(suggesterType);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonSuggesters, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeSuggesters() throws SerializationException {
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(
                new ByteArrayInputStream(jsonSuggesters.getBytes()));
        //
        assertEquals(BigInteger.ONE, questionnaire.getSuggesters().getFirst().getVersion());
    }

}
