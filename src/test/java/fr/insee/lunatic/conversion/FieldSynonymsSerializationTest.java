package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.SuggesterField;
import fr.insee.lunatic.model.flat.SuggesterType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.List;
import java.util.Map;

class FieldSynonymsSerializationTest {

    @Test
    void serializeSynonyms() throws JsonProcessingException, JSONException {
        //
        var synonyms = Map.of("some word", List.of("synonym 1", "synonym 2"));
        //
        String result = new ObjectMapper().writeValueAsString(synonyms);
        //
        String expectedJson = """
                {
                  "some word": [
                    "synonym 1",
                    "synonym 2"
                  ]
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeSynonymsFromQuestionnaire() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        SuggesterType suggesterType = new SuggesterType();
        SuggesterField suggesterField = new SuggesterField();
        suggesterField.setSynonyms(Map.of("some word", List.of("synonym 1", "synonym 2")));
        suggesterType.getFields().add(suggesterField);
        questionnaire.getSuggesters().add(suggesterType);

        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);

        //
        String expectedJson = """
                {
                  "suggesters": [
                    {
                      "fields": [
                        {
                          "synonyms": {
                            "some word": ["synonym 1", "synonym 2"]
                          }
                        }
                      ]
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
