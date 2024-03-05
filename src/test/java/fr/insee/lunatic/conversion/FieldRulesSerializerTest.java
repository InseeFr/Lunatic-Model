package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.FieldRules;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.SuggesterField;
import fr.insee.lunatic.model.flat.SuggesterType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class FieldRulesSerializerTest {

    @Test
    void serializeRulesProperty_stringCase() throws JsonProcessingException, JSONException {
        //
        FieldRules fieldRules = new FieldRules();
        fieldRules.setRule("soft");
        //
        String result = new ObjectMapper().writerFor(FieldRules.class).writeValueAsString(fieldRules);
        //
        JSONAssert.assertEquals("\"soft\"", result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeRulesProperty_arrayCase() throws JsonProcessingException, JSONException {
        //
        FieldRules fieldRules = new FieldRules();
        fieldRules.addPattern("[\\w]+");
        //
        String result = new ObjectMapper().writerFor(FieldRules.class).writeValueAsString(fieldRules);
        //
        JSONAssert.assertEquals("[\"[\\\\w]+\"]", result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeRulesPropertyFromQuestionnaire_stringCase() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        SuggesterType suggesterType = new SuggesterType();
        SuggesterField suggesterField = new SuggesterField();
        suggesterField.setRules(new FieldRules());
        suggesterField.getRules().setRule("soft");
        suggesterType.getFields().add(suggesterField);
        questionnaire.getSuggesters().add(suggesterType);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expected = """
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
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeRulesPropertyFromQuestionnaire_arrayCase() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        SuggesterType suggesterType = new SuggesterType();
        SuggesterField suggesterField = new SuggesterField();
        suggesterField.setRules(new FieldRules());
        suggesterField.getRules().addPattern("[\\w]+");
        suggesterType.getFields().add(suggesterField);
        questionnaire.getSuggesters().add(suggesterType);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "componentType": "Questionnaire",
                  "suggesters": [
                    {
                      "fields": [
                        {
                          "rules": ["[\\\\w]+"]
                        }
                      ]
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

}
