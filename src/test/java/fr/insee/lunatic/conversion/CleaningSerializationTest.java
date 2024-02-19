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

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void deserializeCleaning() throws SerializationException {
        //
        String jsonInput = """
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

        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire result = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));

        //
        assertNotNull(result.getCleaning());
        //
        CleaningVariableEntry q1Entry = result.getCleaning().getCleaningEntry("Q1");
        assertNotNull(q1Entry);
        assertNotNull(q1Entry.getCleanedVariable("Q21"));
        assertNotNull(q1Entry.getCleanedVariable("Q22"));
        assertEquals("(Q1)", q1Entry.getCleanedVariable("Q21").filterExpression());
        assertEquals("(Q1) and (Q2)", q1Entry.getCleanedVariable("Q22").filterExpression());
        //
        CleaningVariableEntry q2Entry = result.getCleaning().getCleaningEntry("Q2");
        assertNotNull(q2Entry);
        assertNotNull(q2Entry.getCleanedVariable("Q22"));
        assertEquals("(Q1) and (Q2)", q2Entry.getCleanedVariable("Q22").filterExpression());
    }

}
