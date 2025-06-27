package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Loop;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class LoopSerializationTest {

    private final JsonSerializer jsonSerializer = new JsonSerializer();
    private final JsonDeserializer jsonDeserializer = new JsonDeserializer();

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void serializeIterationPaginationProperty(Boolean value) throws SerializationException, JSONException {
        // Given
        Questionnaire questionnaire = new Questionnaire();
        Loop loop = new Loop();
        loop.setIsPaginatedByIterations(value);
        questionnaire.getComponents().add(loop);
        // When
        String result = jsonSerializer.serialize(questionnaire);
        // Then
        JSONAssert.assertEquals("""
                {
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "componentType": "Loop",
                      "isPaginatedByIterations": %s,
                      "components": []
                    }
                  ]
                }""".formatted(value), result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeIterationPaginationProperty_null() throws SerializationException, JSONException {
        // Given
        Questionnaire questionnaire = new Questionnaire();
        Loop loop = new Loop();
        questionnaire.getComponents().add(loop);
        // When
        String result = jsonSerializer.serialize(questionnaire);
        // Then
        JSONAssert.assertEquals("""
                {
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "componentType": "Loop",
                      "components": []
                    }
                  ]
                }""", result, JSONCompareMode.STRICT);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void deserializeIterationPaginationProperty(Boolean value) throws SerializationException {
        // Given
        String jsonQuestionnaire = """
                {
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "componentType": "Loop",
                      "isPaginatedByIterations": %s
                    }
                  ]
                }""".formatted(value);
        // When
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonQuestionnaire.getBytes()));
        // Then
        Loop loop = assertInstanceOf(Loop.class, questionnaire.getComponents().getFirst());
        assertEquals(value, loop.getIsPaginatedByIterations());
    }

    @Test
    void deserializeIterationPaginationProperty_null() throws SerializationException {
        // Given
        String jsonQuestionnaire = """
                {
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "componentType": "Loop"
                    }
                  ]
                }""";
        // When
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonQuestionnaire.getBytes()));
        // Then
        Loop loop = assertInstanceOf(Loop.class, questionnaire.getComponents().getFirst());
        assertNull(loop.getIsPaginatedByIterations());
    }
}
