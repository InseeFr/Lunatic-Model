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

import static fr.insee.lunatic.utils.TestUtils.createLabel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class RoundaboutSerializationTest {

    @Test
    void serializeRoundabout() throws SerializationException, IOException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        Roundabout roundabout = new Roundabout();
        roundabout.setId("roundabout-id");
        roundabout.setPage("1");
        roundabout.setConditionFilter(new ConditionFilterType());
        roundabout.getConditionFilter().setValue("true");
        roundabout.getConditionFilter().setType(LabelTypeEnum.VTL);
        roundabout.setLabel(createLabel("\"Roundabout label.\"", LabelTypeEnum.VTL));
        roundabout.setIterations(createLabel("NUMBER_VAR", LabelTypeEnum.VTL));
        roundabout.setLocked(true);
        roundabout.setProgressVariable("PROGRESS");
        Roundabout.Item roundaboutItem = new Roundabout.Item();
        roundaboutItem.setLabel(createLabel("FIRST_NAME", LabelTypeEnum.VTL));
        roundaboutItem.setDescription(createLabel(
                "if AGE > 18 then \"Questions for \" || FIRST_NAME else FIRST_NAME || \" is not concerned\"",
                LabelTypeEnum.VTL));
        roundaboutItem.setDisabled(createLabel("AGE < 18", LabelTypeEnum.VTL));
        roundabout.setItem(roundaboutItem);
        Input input = new Input();
        input.setId("input-id");
        roundabout.getComponents().add(input);
        //
        questionnaire.getComponents().add(roundabout);

        //
        String result = new JsonSerializer().serialize(questionnaire);

        //
        String expectedJson = TestUtils.readResourceFile("roundabout.json");
        JSONAssert.assertEquals(result, expectedJson, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeRoundabout() throws IOException, SerializationException {
        //
        String jsonInput = TestUtils.readResourceFile("roundabout.json");

        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(
                new ByteArrayInputStream(jsonInput.getBytes()));

        //
        Roundabout roundabout = assertInstanceOf(Roundabout.class, questionnaire.getComponents().getFirst());
        assertEquals(1, roundabout.getComponents().size());
        assertEquals(ComponentTypeEnum.INPUT, roundabout.getComponents().getFirst().getComponentType());
    }

}
