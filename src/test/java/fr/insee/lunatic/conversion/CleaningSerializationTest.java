package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.cleaning.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CleaningSerializationTest {

    /** Json content used in following tests. */
    private final String jsonCleaningExample = """
            {
              "componentType": "Questionnaire",
              "cleaning": {
                "Q1": {
                  "Q21": ["(Q1)"],
                  "Q22": ["(Q1)", "(Q2)"]
                },
                "Q2": {
                  "Q22": ["(Q1)", "(Q2)"]
                }
              }
            }""";

    @Test
    void serializeCleaning() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        CleaningType cleaning = new CleaningType();
        CleaningVariableEntry cleaningEntry1 = new CleaningVariableEntry("Q1");
        cleaningEntry1.addCleanedVariable(new CleanedVariableEntry("Q21", List.of("(Q1)")));
        cleaningEntry1.addCleanedVariable(new CleanedVariableEntry("Q22", List.of("(Q1)", "(Q2)")));
        CleaningVariableEntry cleaningEntry2 = new CleaningVariableEntry("Q2");
        cleaningEntry2.addCleanedVariable(new CleanedVariableEntry("Q22", List.of("(Q1)", "(Q2)")));
        cleaning.addCleaningEntry(cleaningEntry1);
        cleaning.addCleaningEntry(cleaningEntry2);
        questionnaire.setCleaning(cleaning);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonCleaningExample, result, JSONCompareMode.STRICT);
    }
    @Test
    void deserializeCleaning2() throws SerializationException {
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire result = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonCleaningExample.getBytes()));

        //
        assertNotNull(result.getCleaning());
        //
        CleaningVariableEntry q1Entry = result.getCleaning().getCleaningEntry("Q1");
        assertNotNull(q1Entry);
        assertNotNull(q1Entry.getCleanedVariable("Q21"));
        assertNotNull(q1Entry.getCleanedVariable("Q22"));
        assertEquals(List.of("(Q1)"), q1Entry.getCleanedVariable("Q21").getFilterExpressions());
        assertEquals(List.of("(Q1)", "(Q2)"), q1Entry.getCleanedVariable("Q22").getFilterExpressions());
        //
        CleaningVariableEntry q2Entry = result.getCleaning().getCleaningEntry("Q2");
        assertNotNull(q2Entry);
        assertNotNull(q2Entry.getCleanedVariable("Q22"));
        assertEquals(List.of("(Q1)", "(Q2)"), q2Entry.getCleanedVariable("Q22").getFilterExpressions());
    }

}
