package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResizingSerializationTest {

    private final String jsonResizingIteration = """
            {
              "componentType": "Questionnaire",
              "resizing": {
                "LOOP_SIZE": {
                  "size": "max(1, LOOP_SIZE)",
                  "variables": ["LOOP_VAR1", "LOOP_VAR2"]
                }
              }
            }""";

    private final String jsonResizingPairwise = """
            {
              "componentType": "Questionnaire",
              "resizing": {
                "FIRST_NAME": {
                  "sizeForLinksVariables": [
                    "count(FIRST_NAME)",
                    "count(FIRST_NAME)"
                  ],
                  "linksVariables": ["LINKS"]
                }
              }
            }""";

    private final String jsonResizingIterationAndPairwise = """
            {
              "componentType": "Questionnaire",
              "resizing": {
                "FIRST_NAME": {
                  "size": "count(FIRST_NAME)",
                  "variables": ["LOOP_VAR1", "LOOP_VAR2"],
                  "sizeForLinksVariables": [
                    "count(FIRST_NAME)",
                    "count(FIRST_NAME)"
                  ],
                  "linksVariables": ["LINKS"]
                }
              }
            }""";

    private final JsonSerializer jsonSerializer = new JsonSerializer();
    private final JsonDeserializer jsonDeserializer = new JsonDeserializer();

    @Test
    void serializeResizing_iterationCase() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        ResizingType resizingType = new ResizingType();
        ResizingIterationEntry resizingEntry = new ResizingIterationEntry();
        resizingEntry.setSize("max(1, LOOP_SIZE)");
        resizingEntry.getVariables().addAll(List.of("LOOP_VAR1", "LOOP_VAR2"));
        resizingType.putResizingEntry("LOOP_SIZE", resizingEntry);
        questionnaire.setResizing(resizingType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonResizingIteration, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeResizing_pairwiseCase() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        ResizingType resizingType = new ResizingType();
        ResizingPairwiseEntry resizingPairwiseEntry = new ResizingPairwiseEntry();
        resizingPairwiseEntry.getSizeForLinksVariables().add("count(FIRST_NAME)");
        resizingPairwiseEntry.getSizeForLinksVariables().add("count(FIRST_NAME)");
        resizingPairwiseEntry.getLinksVariables().add("LINKS");
        resizingType.putResizingEntry("FIRST_NAME", resizingPairwiseEntry);
        questionnaire.setResizing(resizingType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonResizingPairwise, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeResizing_iterationAndPairwiseCase() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        ResizingType resizingType = new ResizingType();
        ResizingPairwiseEntry resizingPairwiseEntry = new ResizingPairwiseEntry();
        resizingPairwiseEntry.setSize("count(FIRST_NAME)");
        resizingPairwiseEntry.getVariables().addAll(List.of("LOOP_VAR1", "LOOP_VAR2"));
        resizingPairwiseEntry.getSizeForLinksVariables().add("count(FIRST_NAME)");
        resizingPairwiseEntry.getSizeForLinksVariables().add("count(FIRST_NAME)");
        resizingPairwiseEntry.getLinksVariables().add("LINKS");
        resizingType.putResizingEntry("FIRST_NAME", resizingPairwiseEntry);
        questionnaire.setResizing(resizingType);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonResizingIterationAndPairwise, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeResizing_iterationCase() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(
                jsonResizingIteration.getBytes()));
        //
        assertNotNull(questionnaire.getResizing());
        assertEquals(1, questionnaire.getResizing().countResizingEntries());;
        ResizingIterationEntry resizingEntry = assertInstanceOf(ResizingIterationEntry.class,
                questionnaire.getResizing().getResizingEntry("LOOP_SIZE"));
        assertEquals("max(1, LOOP_SIZE)", resizingEntry.getSize());
        assertEquals(List.of("LOOP_VAR1", "LOOP_VAR2"), resizingEntry.getVariables());
    }

    @Test
    void deserializeResizing_pairwiseCase() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(
                jsonResizingPairwise.getBytes()));
        //
        assertNotNull(questionnaire.getResizing());
        assertEquals(1, questionnaire.getResizing().countResizingEntries());;
        ResizingPairwiseEntry resizingPairwiseEntry = assertInstanceOf(ResizingPairwiseEntry.class,
                questionnaire.getResizing().getResizingEntry("FIRST_NAME"));
        assertEquals(List.of("count(FIRST_NAME)", "count(FIRST_NAME)"), resizingPairwiseEntry.getSizeForLinksVariables());
        assertEquals(List.of("LINKS"), resizingPairwiseEntry.getLinksVariables());
    }

    @Test
    void deserializeResizing_iterationAndPairwiseCase() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(
                jsonResizingIterationAndPairwise.getBytes()));
        //
        assertNotNull(questionnaire.getResizing());
        assertEquals(1, questionnaire.getResizing().countResizingEntries());;
        ResizingPairwiseEntry resizingPairwiseEntry = assertInstanceOf(ResizingPairwiseEntry.class,
                questionnaire.getResizing().getResizingEntry("FIRST_NAME"));
        assertEquals("count(FIRST_NAME)", resizingPairwiseEntry.getSize());
        assertEquals(List.of("LOOP_VAR1", "LOOP_VAR2"), resizingPairwiseEntry.getVariables());
        assertEquals(List.of("count(FIRST_NAME)", "count(FIRST_NAME)"), resizingPairwiseEntry.getSizeForLinksVariables());
        assertEquals(List.of("LINKS"), resizingPairwiseEntry.getLinksVariables());
    }

}
