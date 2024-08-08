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

class RadioSerializationTest {

    @Test
    void serializeRadio() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        Radio radio = new Radio();
        radio.setId("foo-id");
        radio.setOrientation(Orientation.HORIZONTAL);
        //
        Options option1 = new Options();
        option1.setLabel(new LabelType());
        option1.setValue("1");
        option1.getLabel().setValue("\"Option A\"");
        option1.getLabel().setType(LabelTypeEnum.VTL);
        radio.getOptions().add(option1);
        //
        Options option2 = new Options();
        option2.setValue("2");
        option2.setLabel(new LabelType());
        option2.getLabel().setValue("\"Option B\"");
        option2.getLabel().setType(LabelTypeEnum.VTL);
        radio.getOptions().add(option2);
        //
        Options optionOther = new Options();
        optionOther.setValue("3");
        optionOther.setLabel(new LabelType());
        optionOther.getLabel().setValue("\"Other\"");
        optionOther.getLabel().setType(LabelTypeEnum.VTL);
        optionOther.setDetail(new DetailResponse());
        optionOther.getDetail().setLabel(new LabelType());
        optionOther.getDetail().getLabel().setValue("\"Please specify:\"");
        optionOther.getDetail().getLabel().setType(LabelTypeEnum.VTL);
        optionOther.getDetail().setResponse(new ResponseType());
        optionOther.getDetail().getResponse().setName("FOO_DETAIL");
        radio.getOptions().add(optionOther);
        //
        radio.setResponse(new ResponseType());
        radio.getResponse().setName("FOO");
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
                      "orientation": "horizontal",
                      "options": [
                        {
                          "value": "1",
                          "label": {
                            "value": "\\"Option A\\"",
                            "type": "VTL"
                          }
                        },
                        {
                          "value": "2",
                          "label": {
                            "value": "\\"Option B\\"",
                            "type": "VTL"
                          }
                        },
                        {
                          "value": "3",
                          "label": {
                            "value": "\\"Other\\"",
                            "type": "VTL"
                          },
                          "detail": {
                            "label": {
                              "value": "\\"Please specify:\\"",
                              "type": "VTL"
                            },
                            "response": {
                              "name": "FOO_DETAIL"
                            }
                          }
                        }
                      ],
                      "response": {
                        "name": "FOO"
                      }
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeRadio_defaultOrientation() throws SerializationException {
        //
        String jsonInput = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "foo-id",
                      "componentType": "Radio"
                    }
                  ]
                }""";
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        Radio radio = assertInstanceOf(Radio.class, questionnaire.getComponents().getFirst());
        assertEquals(Orientation.VERTICAL, radio.getOrientation());
    }

    @Test
    void deserializeRadio_defaultOrientation() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        Radio radio = new Radio();
        radio.setId("radio-id");
        questionnaire.getComponents().add(radio);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        String expectedJson = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "radio-id",
                      "componentType": "Radio",
                      "orientation": "vertical",
                      "options": []
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
