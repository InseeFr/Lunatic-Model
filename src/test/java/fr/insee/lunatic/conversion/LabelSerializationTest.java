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
                {"label": {"value": "Foo label", "type": "VTL_MD"}}
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeFromQuestionnaire_usingStringType() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        LabelType label = new LabelType();
        label.setValue("Foo label");
        label.setType("VTL_MD");
        questionnaire.setLabel(label);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        String expected = """
                {"label": {"value": "Foo label", "type": "VTL_MD"}}
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeFromQuestionnaire() throws SerializationException {
        //
        String jsonInput = """
                {"label": {"value": "Foo label", "type": "VTL_MD"}}
                """;
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        LabelType label = questionnaire.getLabel();
        assertEquals(LabelTypeEnum.VTL_MD, label.getTypeEnum());
        assertEquals("VTL_MD", label.getType());
    }

}
