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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DurationSerializationTest {

    @Test
    void serializeDuration_yearsMonthsFormat() throws SerializationException, IOException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        Duration duration = new Duration();
        duration.setId("duration-id");
        duration.setFormat(Duration.YEARS_MONTHS_FORMAT);
        duration.setLabel(new LabelType());
        duration.getLabel().setValue("\"Duration (years/months)\"");
        duration.getLabel().setType(LabelTypeEnum.VTL_MD);
        duration.setDescription(new LabelType());
        duration.getDescription().setValue("\"2 years and 5 months\"");
        duration.getDescription().setType(LabelTypeEnum.VTL_MD);
        duration.setResponse(new ResponseType());
        duration.getResponse().setName("DURATION_VAR");
        //
        questionnaire.getComponents().add(duration);

        //
        String result = new JsonSerializer().serialize(questionnaire);

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
        duration.setFormat(Duration.HOURS_MINUTES_FORMAT);
        duration.setLabel(new LabelType());
        duration.getLabel().setValue("\"Duration (hours/minutes)\"");
        duration.getLabel().setType(LabelTypeEnum.VTL_MD);
        duration.setDescription(new LabelType());
        duration.getDescription().setValue("\"1 hour and 10 minutes\"");
        duration.getDescription().setType(LabelTypeEnum.VTL_MD);
        duration.setResponse(new ResponseType());
        duration.getResponse().setName("DURATION_VAR");
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
        assertEquals("PnYnM", duration.getFormat());
    }

    @Test
    void deserializeDuration_hoursMinutesFormat() throws IOException, SerializationException {
        //
        String jsonInput = TestUtils.readResourceFile("duration-hours-minutes.json");
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        Duration duration = assertInstanceOf(Duration.class, questionnaire.getComponents().getFirst());
        assertEquals("PTnHnM", duration.getFormat());
    }

}
