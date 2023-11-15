package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.List;

class FieldSynonymsSerializationTest {

    @Test
    void serializeSynonyms() throws JsonProcessingException, JSONException {
        //
        FieldSynonyms fieldSynonyms = new FieldSynonyms();
        FieldSynonym fieldSynonym = new FieldSynonym();
        fieldSynonym.setSource("some word");
        fieldSynonym.setTarget(List.of("synonym 1", "synonym 2"));
        fieldSynonyms.add(fieldSynonym);
        //
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("FieldSynonymsSerializer");
        module.addSerializer(FieldSynonyms.class, new FieldSynonymsSerializer());
        mapper.registerModule(module);
        String result = mapper.writeValueAsString(fieldSynonyms);
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
        //
        FieldSynonym fieldSynonym = new FieldSynonym();
        fieldSynonym.setSource("some word");
        fieldSynonym.setTarget(List.of("synonym 1", "synonym 2"));
        //
        suggesterField.getSynonyms().add(fieldSynonym);
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
