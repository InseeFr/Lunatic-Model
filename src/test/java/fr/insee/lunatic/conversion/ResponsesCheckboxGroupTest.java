package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class ResponsesCheckboxGroupTest {

    @Test
    void serializeResponseWithDetail() throws JsonProcessingException, JSONException {
        //
        ResponsesCheckboxGroup responsesCheckboxGroup = new ResponsesCheckboxGroup();
        responsesCheckboxGroup.setId("kmort6x9-QOP-kmorue9d");
        responsesCheckboxGroup.setLabel(new LabelType());
        responsesCheckboxGroup.getLabel().setValue("\"Autre\"");
        responsesCheckboxGroup.getLabel().setType(LabelTypeEnum.VTL);
        responsesCheckboxGroup.setResponse(new ResponseType());
        responsesCheckboxGroup.getResponse().setName("NATIO1N5");
        responsesCheckboxGroup.setDetail(new DetailResponse());
        responsesCheckboxGroup.getDetail().setLabel(new LabelType());
        responsesCheckboxGroup.getDetail().getLabel().setValue("\"Préciser : \"");
        responsesCheckboxGroup.getDetail().getLabel().setType(LabelTypeEnum.VTL);
        responsesCheckboxGroup.getDetail().setResponse(new ResponseType());
        responsesCheckboxGroup.getDetail().getResponse().setName("NATIO1N5DETAIL");
        //
        String result = new ObjectMapper().writeValueAsString(responsesCheckboxGroup);
        //
        String expectedJson = """
                {
                    "id": "kmort6x9-QOP-kmorue9d",
                    "label": {
                        "value": "\\"Autre\\"",
                        "type": "VTL"
                    },
                    "response": { "name": "NATIO1N5" },
                    "detail": {
                        "label": {
                            "value": "\\"Préciser : \\"",
                            "type": "VTL"
                        },
                        "response": {
                            "name": "NATIO1N5DETAIL"
                        }
                    }
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
