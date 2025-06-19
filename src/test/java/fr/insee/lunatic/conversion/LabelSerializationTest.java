package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LabelSerializationTest {

    @Test
    void serializeFromQuestionnaire() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        LabelType label = new LabelType();
        label.setValue("Foo label");
        label.setType(LabelTypeEnum.VTL_MD);
        questionnaire.setLabel(label);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "componentType": "Questionnaire",
                  "label": {"value": "Foo label", "type": "VTL|MD"}
                }
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeFromQuestionnaire() throws SerializationException {
        //
        String jsonInput = """
                {
                  "componentType": "Questionnaire",
                  "label": {"value": "Foo label", "type": "VTL|MD"}
                }
                """;
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        LabelType label = questionnaire.getLabel();
        assertEquals(LabelTypeEnum.VTL_MD, label.getType());
    }

    @Test
    void serializeFromQuestionnaire_txtType() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        LabelType label = new LabelType();
        label.setValue("Foo label");
        label.setType(LabelTypeEnum.TXT);
        questionnaire.setLabel(label);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "componentType": "Questionnaire",
                  "label": {"value": "Foo label", "type": "TXT"}
                }
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeFromQuestionnaire_txtType() throws SerializationException {
        //
        String jsonInput = """
                {
                  "componentType": "Questionnaire",
                  "label": {"value": "Foo label", "type": "TXT"}
                }
                """;
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        LabelType label = questionnaire.getLabel();
        assertEquals(LabelTypeEnum.TXT, label.getType());
    }

    @Test
    void deserializeFromQuestionnaire_illegalTypeValue() {
        //
        String jsonInput = """
                {
                  "componentType": "Questionnaire",
                  "label": {"value": "Foo label", "type": "Foo value"}
                }
                """;
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        assertThrows(SerializationException.class, () ->
                jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes())));
    }

    @Test
    void serializeFromQuestionnaireShapeFrom() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        LabelType label = new LabelType();
        label.setValue("Foo label");
        label.setType(LabelTypeEnum.VTL);
        label.setShapeFrom("BAR");
        questionnaire.setLabel(label);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "componentType": "Questionnaire",
                  "label": {"value": "Foo label", "type": "VTL", "shapeFrom": "BAR" }
                }
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeFromQuestionnaireShapeFrom() throws SerializationException {
        //
        String jsonInput = """
                {
                  "componentType": "Questionnaire",
                  "label": {"value": "Foo label", "type": "VTL", "shapeFrom": "BAR"}
                }
                """;
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        LabelType label = questionnaire.getLabel();
        assertEquals(LabelTypeEnum.VTL, label.getType());
        assertEquals("BAR", label.getShapeFrom());
    }

}
