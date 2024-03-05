package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.FieldRules;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FieldRulesDeserializerTest {

    @Test
    void deserializeRules_stringCase() throws JsonProcessingException {
        String jsonRules = "\"soft\"";
        FieldRules fieldRules = new ObjectMapper().readValue(jsonRules, FieldRules.class);
        assertEquals("soft", fieldRules.getRule());
    }

    @Test
    void deserializeRules_arrayCase() throws JsonProcessingException {
        String jsonRules = "[\"pattern 1\", \"pattern 2\"]";
        FieldRules fieldRules = new ObjectMapper().readValue(jsonRules, FieldRules.class);
        assertEquals(List.of("pattern 1", "pattern 2"), fieldRules.getPatterns());
    }

    @Test
    void deserializeRulesFromQuestionnaire_stringCase() throws SerializationException {
        //
        String jsonQuestionnaire = """
                {
                  "componentType": "Questionnaire",
                  "suggesters": [
                    {
                      "fields": [
                        {
                          "rules": "soft"
                        }
                      ]
                    }
                  ]
                }""";
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonQuestionnaire.getBytes()));
        //
        assertEquals(1, questionnaire.getSuggesters().size());
        assertEquals(1, questionnaire.getSuggesters().get(0).getFields().size());
        assertNotNull(questionnaire.getSuggesters().get(0).getFields().get(0).getRules());
        assertEquals("soft", questionnaire.getSuggesters().get(0).getFields().get(0).getRules().getRule());
    }

    @Test
    void deserializeRulesFromQuestionnaire_arrayCase() throws SerializationException {
        String jsonQuestionnaire = """
                {
                  "componentType": "Questionnaire",
                  "suggesters": [
                    {
                      "fields": [
                        {
                          "rules": ["pattern 1", "pattern 2"]
                        }
                      ]
                    }
                  ]
                }""";
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonQuestionnaire.getBytes()));
        //
        assertEquals(1, questionnaire.getSuggesters().size());
        assertEquals(1, questionnaire.getSuggesters().get(0).getFields().size());
        assertNotNull(questionnaire.getSuggesters().get(0).getFields().get(0).getRules());
        assertEquals(List.of("pattern 1", "pattern 2"),
                questionnaire.getSuggesters().get(0).getFields().get(0).getRules().getPatterns());
    }

}
