package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class CheckboxGroupSerializationTest {

    @Test
    void serializeCheckboxGroup() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        checkboxGroup.setId("foo-id");
        checkboxGroup.setComponentType(ComponentTypeEnum.CHECKBOX_GROUP);
        //
        ResponsesCheckboxGroup response1 = new ResponsesCheckboxGroup();
        response1.setId("response1-id");
        response1.setLabel(new LabelType());
        response1.getLabel().setValue("\"Option A\"");
        response1.getLabel().setType(LabelTypeEnum.VTL);
        response1.setResponse(new ResponseType());
        response1.getResponse().setName("FOO_A");
        checkboxGroup.getResponses().add(response1);
        //
        ResponsesCheckboxGroup response2 = new ResponsesCheckboxGroup();
        response2.setId("response2-id");
        response2.setLabel(new LabelType());
        response2.getLabel().setValue("\"Option B\"");
        response2.getLabel().setType(LabelTypeEnum.VTL);
        response2.setResponse(new ResponseType());
        response2.getResponse().setName("FOO_B");
        checkboxGroup.getResponses().add(response2);
        //
        ResponsesCheckboxGroup responseOther = new ResponsesCheckboxGroup();
        responseOther.setId("response-other1-id");
        responseOther.setLabel(new LabelType());
        responseOther.getLabel().setValue("\"Other\"");
        responseOther.getLabel().setType(LabelTypeEnum.VTL);
        responseOther.setResponse(new ResponseType());
        responseOther.getResponse().setName("FOO_C");
        responseOther.setDetail(new DetailResponse());
        responseOther.getDetail().setLabel(new LabelType());
        responseOther.getDetail().getLabel().setValue("\"Please specify:\"");
        responseOther.getDetail().getLabel().setType(LabelTypeEnum.VTL);
        responseOther.getDetail().setResponse(new ResponseType());
        responseOther.getDetail().getResponse().setName("FOO_C_DETAIL");
        checkboxGroup.getResponses().add(responseOther);
        //
        ResponsesCheckboxGroup responseOther2 = new ResponsesCheckboxGroup();
        responseOther2.setId("response-other2-id");
        responseOther2.setLabel(new LabelType());
        responseOther2.getLabel().setValue("\"Another\"");
        responseOther2.getLabel().setType(LabelTypeEnum.VTL);
        responseOther.setResponse(new ResponseType());
        responseOther.getResponse().setName("FOO_D");
        responseOther2.setDetail(new DetailResponse());
        responseOther2.getDetail().setLabel(new LabelType());
        responseOther2.getDetail().getLabel().setValue("\"Please specify:\"");
        responseOther2.getDetail().getLabel().setType(LabelTypeEnum.VTL);
        responseOther2.getDetail().setResponse(new ResponseType());
        responseOther2.getDetail().getResponse().setName("FOO_D_DETAIL");
        checkboxGroup.getResponses().add(responseOther2);
        //
        questionnaire.getComponents().add(checkboxGroup);

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
                      "componentType": "CheckboxGroup",
                      "responses": [
                        {
                          "id": "response1-id",
                          "label": {
                            "value": "\\"Option A\\"",
                            "type": "VTL"
                          },
                          "response": {
                            "name": "FOO_A"
                          }
                        },
                        {
                          "id": "response2-id",
                          "label": {
                            "value": "\\"Option B\\"",
                            "type": "VTL"
                          },
                          "response": {
                            "name": "FOO_B"
                          }
                        },
                        {
                          "id": "response-other1-id",
                          "label": {
                            "value": "\\"Other\\"",
                            "type": "VTL"
                          },
                          "response": {
                            "name": "FOO_D"
                          },
                          "detail": {
                            "label": {
                              "value": "\\"Please specify:\\"",
                              "type": "VTL"
                            },
                            "response": {
                              "name": "FOO_C_DETAIL"
                            }
                          }
                        },
                        {
                          "id": "response-other2-id",
                          "label": {
                            "value": "\\"Another\\"",
                            "type": "VTL"
                          },
                          "detail": {
                            "label": {
                              "value": "\\"Please specify:\\"",
                              "type": "VTL"
                            },
                            "response": {
                              "name": "FOO_D_DETAIL"
                            }
                          }
                        }
                      ]
                    }
                  ]
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }
}
