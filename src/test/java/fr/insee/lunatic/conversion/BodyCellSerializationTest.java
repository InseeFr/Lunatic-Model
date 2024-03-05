package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.BodyCell;
import fr.insee.lunatic.model.flat.ComponentTypeEnum;
import fr.insee.lunatic.model.flat.ResponseType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BodyCellSerializationTest {

    private final String jsonSuggesterCell = """
            {
              "id": "foo-id",
              "componentType": "Suggester",
              "storeName": "FOO_CODE_LIST",
              "response": {
                "name": "FOO"
              }
            }""";

    @Test
    void serializeSuggesterCell() throws JsonProcessingException, JSONException {
        //
        BodyCell bodyCell = new BodyCell();
        bodyCell.setId("foo-id");
        bodyCell.setComponentType(ComponentTypeEnum.SUGGESTER);
        bodyCell.setStoreName("FOO_CODE_LIST");
        bodyCell.setResponse(new ResponseType());
        bodyCell.getResponse().setName("FOO");
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String result = objectMapper.writeValueAsString(bodyCell);
        //
        JSONAssert.assertEquals(jsonSuggesterCell, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeSuggesterCell() throws JsonProcessingException {
        //
        BodyCell bodyCell = new ObjectMapper().readValue(jsonSuggesterCell, BodyCell.class);
        //
        assertEquals("foo-id", bodyCell.getId());
        assertEquals(ComponentTypeEnum.SUGGESTER, bodyCell.getComponentType());
        assertEquals("FOO_CODE_LIST", bodyCell.getStoreName());
        assertEquals("FOO", bodyCell.getResponse().getName());
    }

}
