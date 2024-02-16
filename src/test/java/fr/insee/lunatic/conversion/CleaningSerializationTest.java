package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.CleanedVariableEntry;
import fr.insee.lunatic.model.flat.CleaningType;
import fr.insee.lunatic.model.flat.CleaningVariableEntry;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class CleaningSerializationTest {

    private Questionnaire questionnaire;

    @BeforeEach
    void cleaningObject() {
        questionnaire = new Questionnaire();
        CleaningType cleaning = new CleaningType();
        CleaningVariableEntry cleaningEntry1 = new CleaningVariableEntry("Q1");
        cleaningEntry1.addCleanedVariable(new CleanedVariableEntry("Q21", "(Q1)"));
        cleaningEntry1.addCleanedVariable(new CleanedVariableEntry("Q22", "(Q1) and (Q2)"));
        CleaningVariableEntry cleaningEntry2 = new CleaningVariableEntry("Q2");
        cleaningEntry2.addCleanedVariable(new CleanedVariableEntry("Q22", "(Q1) and (Q2)"));
        cleaning.addCleaningEntry(cleaningEntry1);
        cleaning.addCleaningEntry(cleaningEntry2);
        questionnaire.setCleaning(cleaning);
    }

    @Test
    void serializeCleaning() throws SerializationException, JSONException {
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expectedJson = """
                {
                  "cleaning": {
                    "Q1": {
                      "Q21": "(Q1)",
                      "Q22": "(Q1) and (Q2)"
                    },
                    "Q2": {
                      "Q22": "(Q1) and (Q2)"
                    }
                  }
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
