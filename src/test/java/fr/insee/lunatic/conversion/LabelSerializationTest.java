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
    void serializeFromQuestionnaire_usingEnumType() throws SerializationException, JSONException {
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
                {"label": {"value": "Foo label", "type": "VTL|MD"}}
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeFromQuestionnaire_usingStringType() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        LabelType label = new LabelType();
        label.setValue("Foo label");
        label.setType("VTL|MD");
        questionnaire.setLabel(label);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expected = """
                {"label": {"value": "Foo label", "type": "VTL|MD"}}
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void labelObject_usingStringType_illegalValue() {
        LabelType label = new LabelType();
        assertThrows(IllegalArgumentException.class, () -> label.setType("Foo type"));
    }

    @Test
    void deserializeFromQuestionnaire() throws SerializationException {
        //
        String jsonInput = """
                {"label": {"value": "Foo label", "type": "VTL|MD"}}
                """;
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        LabelType label = questionnaire.getLabel();
        assertEquals(LabelTypeEnum.VTL_MD, label.getTypeEnum());
        assertEquals("VTL|MD", label.getType());
    }

    @Test
    void deserializeFromQuestionnaire_illegalTypeValue() {
        //
        String jsonInput = """
                {"label": {"value": "Foo label", "type": "Foo value"}}
                """;
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        assertThrows(SerializationException.class, () ->
                jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes())));
    }

}
