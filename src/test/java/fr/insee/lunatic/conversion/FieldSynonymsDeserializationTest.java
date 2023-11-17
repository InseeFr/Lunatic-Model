package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldSynonymsDeserializationTest {

    @Test
    void deserializeSynonymsFromQuestionnaire() throws SerializationException {
        //
        String jsonQuestionnaire = """
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
        //
        JsonDeserializer deserializer = new JsonDeserializer();
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(jsonQuestionnaire.getBytes()));
        //
        assertEquals(1, questionnaire.getSuggesters().size());
        assertEquals(1, questionnaire.getSuggesters().get(0).getFields().size());
        assertEquals(1, questionnaire.getSuggesters().get(0).getFields().get(0).getSynonyms().size());
        assertEquals(List.of("synonym 1", "synonym 2"),
                questionnaire.getSuggesters().get(0).getFields().get(0).getSynonyms().get("some word"));
    }
}
