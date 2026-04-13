package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class TextSerializationTest {

    private final String textComponentJson = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "id": "text-component-id",
                  "componentType": "Text",
                  "label": {
                    "value": "\\"Non-editable text.\\"",
                    "type": "VTL|MD"
                  }
                }
              ]
            }""";

    @Test
    void serializeTextComponent() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        Text text = new Text();
        text.setId("text-component-id");
        text.setLabel(new LabelType());
        text.getLabel().setValue("\"Non-editable text.\"");
        text.getLabel().setType(LabelTypeEnum.VTL_MD);
        questionnaire.getComponents().add(text);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        JSONAssert.assertEquals(textComponentJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeTextComponent() throws SerializationException {
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(
                new ByteArrayInputStream(textComponentJson.getBytes()));
        //
        Text text = assertInstanceOf(Text.class, questionnaire.getComponents().getFirst());
        assertEquals(ComponentTypeEnum.TEXT, text.getComponentType());
        assertEquals("\"Non-editable text.\"", text.getLabel().getValue());
        assertEquals(LabelTypeEnum.VTL_MD, text.getLabel().getType());
    }

}
