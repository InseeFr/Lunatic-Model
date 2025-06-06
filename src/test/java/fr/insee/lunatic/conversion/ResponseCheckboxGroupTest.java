package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.math.BigInteger;

class ResponseCheckboxGroupTest {

    @Test
    void serializeResponseWithDetail() throws JsonProcessingException, JSONException {
        //
        ResponseCheckboxGroup responseCheckboxGroup = new ResponseCheckboxGroup();
        responseCheckboxGroup.setId("kmort6x9-QOP-kmorue9d");
        responseCheckboxGroup.setLabel(new LabelType());
        responseCheckboxGroup.getLabel().setValue("\"Autre\"");
        responseCheckboxGroup.getLabel().setType(LabelTypeEnum.VTL);
        responseCheckboxGroup.setResponse(new ResponseType());
        responseCheckboxGroup.getResponse().setName("NATIO1N5");
        responseCheckboxGroup.setDetail(new DetailResponse());
        responseCheckboxGroup.getDetail().setLabel(new LabelType());
        responseCheckboxGroup.getDetail().getLabel().setValue("\"Préciser : \"");
        responseCheckboxGroup.getDetail().getLabel().setType(LabelTypeEnum.VTL);
        responseCheckboxGroup.getDetail().setResponse(new ResponseType());
        responseCheckboxGroup.getDetail().getResponse().setName("NATIO1N5DETAIL");
        responseCheckboxGroup.getDetail().setMaxLength(BigInteger.valueOf(15));
        //
        String result = new ObjectMapper().writeValueAsString(responseCheckboxGroup);
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
                        },
                        "maxLength": 15
                    }
                }""";
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

}
