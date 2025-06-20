package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;

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
        Option option1 = new Option();
        option1.setLabel(new LabelType());
        option1.setValue("1");
        option1.getLabel().setValue("\"Option A\"");
        option1.getLabel().setType(LabelTypeEnum.VTL);
        radio.getOptions().add(option1);
        //
        Option option2 = new Option();
        option2.setValue("2");
        option2.setLabel(new LabelType());
        option2.getLabel().setValue("\"Option B\"");
        option2.getLabel().setType(LabelTypeEnum.VTL);
        option2.setConditionFilter(new ConditionFilterType());
        option2.getConditionFilter().setValue("<some filter expression>");
        option2.getConditionFilter().setType(LabelTypeEnum.VTL);
        radio.getOptions().add(option2);
        //
        Option optionOther = new Option();
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
        optionOther.getDetail().setMaxLength(BigInteger.valueOf(5));
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
                          },
                          "conditionFilter": {
                            "value": "<some filter expression>",
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
                            },
                            "maxLength": 5
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
