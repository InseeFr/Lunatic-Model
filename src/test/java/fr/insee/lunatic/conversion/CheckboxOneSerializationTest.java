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
        checkboxOne.setComponentType(ComponentTypeEnum.CHECKBOX_ONE);
        //
        Options option1 = new Options();
        option1.setLabel(new LabelType());
        option1.getLabel().setValue("\"Option A\"");
        option1.getLabel().setType(LabelTypeEnum.VTL);
        checkboxOne.getOptions().add(option1);
        Options option2 = new Options();
        option2.setLabel(new LabelType());
        option2.getLabel().setValue("\"Option B\"");
        option2.getLabel().setType(LabelTypeEnum.VTL);
        checkboxOne.getOptions().add(option2);
        Options optionOther = new Options();
        optionOther.setLabel(new LabelType());
        optionOther.getLabel().setValue("\"Other\"");
        optionOther.getLabel().setType(LabelTypeEnum.VTL);
        checkboxOne.getOptions().add(optionOther);
        //
        checkboxOne.setResponse(new ResponseType());
        checkboxOne.getResponse().setName("FOO");
        //
        checkboxOne.setDetail(new DetailResponse());
        checkboxOne.getDetail().setLabel(new LabelType());
        checkboxOne.getDetail().getLabel().setValue("\"Please specify:\"");
        checkboxOne.getDetail().setResponse(new ResponseType());
        checkboxOne.getDetail().getResponse().setName("FOO_DETAIL");
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
