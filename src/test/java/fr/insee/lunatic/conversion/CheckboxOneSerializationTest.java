package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class CheckboxOneSerializationTest {

    @Test
    void serializeCheckboxOne() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        CheckboxOne checkboxOne = new CheckboxOne();
        checkboxOne.setId("foo-id");
        //
        Option option1 = new Option();
        option1.setLabel(new LabelType());
        option1.setValue("1");
        option1.getLabel().setValue("\"Option A\"");
        option1.getLabel().setType(LabelTypeEnum.VTL);
        checkboxOne.getOptions().add(option1);
        //
        Option option2 = new Option();
        option2.setValue("2");
        option2.setLabel(new LabelType());
        option2.getLabel().setValue("\"Option B\"");
        option2.getLabel().setType(LabelTypeEnum.VTL);
        checkboxOne.getOptions().add(option2);
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
        checkboxOne.getOptions().add(optionOther);
        //
        checkboxOne.setResponse(new ResponseType());
        checkboxOne.getResponse().setName("FOO");
        //
        questionnaire.getComponents().add(checkboxOne);

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
                      "componentType": "CheckboxOne",
                      "orientation": "vertical",
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

}
