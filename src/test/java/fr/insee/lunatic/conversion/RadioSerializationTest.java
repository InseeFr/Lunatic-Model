package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class RadioSerializationTest {

    @Test
    void serializeRadio() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        Radio radio = new Radio();
        radio.setId("foo-id");
        radio.setComponentType(ComponentTypeEnum.RADIO);
        //
        Options option1 = new Options();
        option1.setLabel(new LabelType());
        option1.getLabel().setValue("\"Option A\"");
        option1.getLabel().setType(LabelTypeEnum.VTL);
        radio.getOptions().add(option1);
        Options option2 = new Options();
        option2.setLabel(new LabelType());
        option2.getLabel().setValue("\"Option B\"");
        option2.getLabel().setType(LabelTypeEnum.VTL);
        radio.getOptions().add(option2);
        Options optionOther = new Options();
        optionOther.setLabel(new LabelType());
        optionOther.getLabel().setValue("\"Other\"");
        optionOther.getLabel().setType(LabelTypeEnum.VTL);
        radio.getOptions().add(optionOther);
        //
        radio.setResponse(new ResponseType());
        radio.getResponse().setName("FOO");
        //
        radio.setDetail(new DetailResponse());
        radio.getDetail().setLabel(new LabelType());
        radio.getDetail().getLabel().setValue("\"Please specify:\"");
        radio.getDetail().setResponse(new ResponseType());
        radio.getDetail().getResponse().setName("FOO_DETAIL");
        //
        questionnaire.getComponents().add(radio);

        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);

        //
        String expectedJson = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "foo-id",
                      "componentType": "Radio",
                      "options": [
                        {
                          "label": {
                            "value": "\\"Option A\\"",
                            "type": "VTL"
                          }
                        },
                        {
                          "label": {
                            "value": "\\"Option B\\"",
                            "type": "VTL"
                          }
                        },
                        {
                          "label": {
                            "value": "\\"Other\\"",
                            "type": "VTL"
                          }
                        }
                      ],
                      "response": {
                        "name": "FOO"
                      },
                      "detail": {
                        "label": {
                          "value": "\\"Please specify:\\""
                        },
                        "response": {
                          "name": "FOO_DETAIL"
                        }
                      }
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
