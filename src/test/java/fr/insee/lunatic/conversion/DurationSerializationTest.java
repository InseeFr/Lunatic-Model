package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import fr.insee.lunatic.utils.TestUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DurationSerializationTest {

    @Test
    void serializeDuration_yearsMonthsFormat() throws SerializationException, IOException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        Duration duration = new Duration();
        duration.setId("duration-id");
        duration.setMandatory(true);
        duration.setFormat(DurationFormat.YEARS_MONTHS);
        duration.setLabel(new LabelType());
        duration.getLabel().setValue("\"Duration (years/months)\"");
        duration.getLabel().setType(LabelTypeEnum.VTL_MD);
        duration.setDescription(new LabelType());
        duration.getDescription().setValue("\"2 years and 5 months\"");
        duration.getDescription().setType(LabelTypeEnum.VTL_MD);
        duration.setResponse(new ResponseType());
        duration.getResponse().setName("DURATION_VAR");
        duration.setMin("P1Y6M");
        duration.setMax("P3Y0M");

        //
        questionnaire.getComponents().add(duration);

        //
        String result = new JsonSerializer().serialize(questionnaire);

        //
        String expectedJson = TestUtils.readResourceFile("duration-years-months.json");
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeDuration_HoursMinutesFormat() throws SerializationException, IOException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        Duration duration = new Duration();
        duration.setId("duration-id");
        duration.setMandatory(false);
        duration.setFormat(DurationFormat.HOURS_MINUTES);
        duration.setLabel(new LabelType());
        duration.getLabel().setValue("\"Duration (hours/minutes)\"");
        duration.getLabel().setType(LabelTypeEnum.VTL_MD);
        duration.setDescription(new LabelType());
        duration.getDescription().setValue("\"1 hour and 10 minutes\"");
        duration.getDescription().setType(LabelTypeEnum.VTL_MD);
        duration.setResponse(new ResponseType());
        duration.getResponse().setName("DURATION_VAR");
        duration.setMin("PT1H30M");
        duration.setMax("PT5H0M");
        //
        questionnaire.getComponents().add(duration);

        //
        String result = new JsonSerializer().serialize(questionnaire);

        String expectedJson = TestUtils.readResourceFile("duration-hours-minutes.json");
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeDuration_yearsMonthsFormat() throws IOException, SerializationException {
        //
        String jsonInput = TestUtils.readResourceFile("duration-years-months.json");
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        Duration duration = assertInstanceOf(Duration.class, questionnaire.getComponents().getFirst());
        assertEquals("PnYnM", duration.getFormat().value());
        assertTrue(duration.getMandatory());
    }

    @Test
    void deserializeDuration_hoursMinutesFormat() throws IOException, SerializationException {
        //
        String jsonInput = TestUtils.readResourceFile("duration-hours-minutes.json");
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        Duration duration = assertInstanceOf(Duration.class, questionnaire.getComponents().getFirst());
        assertFalse(duration.getMandatory());
        assertEquals("PTnHnM", duration.getFormat().value());
    }

    @Test
    void deserializeDuration_unsupportedFormat_shouldThrow() {
        // Given a json with invalid format
        // (and a valid one to control that it's actually the format that generates an exception)
        String validDurationJson = jsonDurationSample("PnYnM");
        String invalidDurationJson = jsonDurationSample("PnYnMnDTnHnMnS");
        //
        assertDoesNotThrow(() ->
                new JsonDeserializer().deserialize(new ByteArrayInputStream(validDurationJson.getBytes())));
        assertThrows(SerializationException.class, () ->
                new JsonDeserializer().deserialize(new ByteArrayInputStream(invalidDurationJson.getBytes())));
    }

    /** Utility method used in this test to generate a Json-Lunatic questionnaire with a duration component
     * with the given format. */
    private static String jsonDurationSample(String stringFormat) {
        return String.format("""
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "duration-id",
                      "componentType": "Duration",
                      "format": "%s",
                      "response": {
                        "name": "DURATION_VAR"
                      }
                    }
                  ]
                }""", stringFormat);
    }

}
