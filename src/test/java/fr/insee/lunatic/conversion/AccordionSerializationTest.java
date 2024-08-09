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

class AccordionSerializationTest {

    private final String accordionJson = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "componentType": "Accordion",
                  "position": "bottom",
                  "items": [
                    {
                      "label": {
                        "value": "\\"Why this question?\\"",
                        "type": "VTL|MD"
                      },
                      "body": {
                        "value": "\\"To find out more about you 👁️\\"",
                        "type": "VTL|MD"
                      }
                    },
                    {
                      "label": {
                        "value": "\\"Another item\\"",
                        "type": "TXT"
                      },
                      "body": {
                        "value": "\\"This item is just to show you that you can add more than one.\\"",
                        "type": "TXT"
                      }
                    }
                  ]
                }
              ]
            }""";

    @Test
    void serializeAccordion() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        Accordion accordion = new Accordion();
        accordion.setPosition(ComponentPosition.BOTTOM);
        Accordion.Item item1 = new Accordion.Item();
        item1.setLabel(new LabelType());
        item1.getLabel().setValue("\"Why this question?\"");
        item1.getLabel().setType(LabelTypeEnum.VTL_MD);
        item1.setBody(new LabelType());
        item1.getBody().setValue("\"To find out more about you \uD83D\uDC41️\"");
        item1.getBody().setType(LabelTypeEnum.VTL_MD);
        accordion.getItems().add(item1);
        Accordion.Item item2 = new Accordion.Item();
        item2.setLabel(new LabelType());
        item2.getLabel().setValue("\"Another item\"");
        item2.getLabel().setType(LabelTypeEnum.TXT);
        item2.setBody(new LabelType());
        item2.getBody().setValue("\"This item is just to show you that you can add more than one.\"");
        item2.getBody().setType(LabelTypeEnum.TXT);
        accordion.getItems().add(item2);
        questionnaire.getComponents().add(accordion);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        JSONAssert.assertEquals(accordionJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeAccordion() throws SerializationException {
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(
                new ByteArrayInputStream(accordionJson.getBytes()));
        //
        Accordion accordion = assertInstanceOf(Accordion.class, questionnaire.getComponents().getFirst());
        assertEquals(ComponentTypeEnum.ACCORDION, accordion.getComponentType());
        assertEquals(ComponentPosition.BOTTOM, accordion.getPosition());
        //...
    }

}
