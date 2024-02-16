package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.CleaningEntry;
import fr.insee.lunatic.model.flat.CleaningType;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class CleaningSerializationTest {

    @Test
    void serializeCleaning() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        CleaningType cleaning = new CleaningType();
        cleaning.insertCleaningEntry("Q1", new CleaningEntry("Q21", "(Q1)"));
        cleaning.insertCleaningEntry("Q1", new CleaningEntry("Q22", "(Q1) and (Q21)"));
        cleaning.insertCleaningEntry("Q21", new CleaningEntry("Q22", "(Q1) and (Q21)"));
        questionnaire.setCleaning(cleaning);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expectedJson = """
                {
                  "cleaning": {
                    "Q1": {
                      "Q21": "(Q1)",
                      "Q22": "(Q1) and (Q21)"
                    },
                    "Q21": {
                      "Q22": "(Q1) and (Q21)"
                    }
                  }
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
