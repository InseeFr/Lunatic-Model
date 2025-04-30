package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.*;
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

    private final String jsonInputNumberCell = """
            {
              "id": "foo-id",
              "componentType": "InputNumber",
              "min": 0,
              "max": 100,
              "unit": {
                "value": "%",
                "type": "VTL|MD"
              },
              "response": {
                "name": "FOO"
              }
            }""";

    private final String jsonDatepickerCell = """
            {
              "id": "foo-id",
              "componentType": "Datepicker",
              "min": "24/05/1950",
              "max": "02/04/2030",
              "unit": {
                "value": "%",
                "type": "VTL|MD"
              },
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

    @Test
    void serializeInputNumberCell() throws JsonProcessingException, JSONException {
        //
        BodyCell bodyCell = new BodyCell();
        bodyCell.setId("foo-id");
        bodyCell.setComponentType(ComponentTypeEnum.INPUT_NUMBER);
        NumberBoundaries boundaries = new NumberBoundaries();
        boundaries.setMin(0d);
        boundaries.setMax(100d);
        bodyCell.setBoundaries(boundaries);
        bodyCell.setUnit(new LabelType());
        bodyCell.getUnitLabel().setValue("%");
        bodyCell.getUnitLabel().setType(LabelTypeEnum.VTL_MD);
        bodyCell.setResponse(new ResponseType());
        bodyCell.getResponse().setName("FOO");
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String result = objectMapper.writeValueAsString(bodyCell);
        //
        JSONAssert.assertEquals(jsonInputNumberCell, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeInputNumberCell() throws JsonProcessingException {
        //
        BodyCell bodyCell = new ObjectMapper().readValue(jsonInputNumberCell, BodyCell.class);
        //
        assertEquals("foo-id", bodyCell.getId());
        assertEquals(ComponentTypeEnum.INPUT_NUMBER, bodyCell.getComponentType());
        assertEquals(0d, bodyCell.getMin());
        assertEquals(100d, bodyCell.getMax());
        assertEquals("%", bodyCell.getUnitLabel().getValue());
        assertEquals(LabelTypeEnum.VTL_MD, bodyCell.getUnitLabel().getType());
        assertEquals("FOO", bodyCell.getResponse().getName());
    }

    @Test
    void serializeDatepickerCell() throws JsonProcessingException, JSONException {
        //
        BodyCell bodyCell = new BodyCell();
        bodyCell.setId("foo-id");
        bodyCell.setComponentType(ComponentTypeEnum.DATEPICKER);
        DateBoundaries boundaries = new DateBoundaries();
        boundaries.setMin("24/05/1950");
        boundaries.setMax("02/04/2030");
        bodyCell.setBoundaries(boundaries);
        bodyCell.setUnit(new LabelType());
        bodyCell.getUnitLabel().setValue("%");
        bodyCell.getUnitLabel().setType(LabelTypeEnum.VTL_MD);
        bodyCell.setResponse(new ResponseType());
        bodyCell.getResponse().setName("FOO");
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String result = objectMapper.writeValueAsString(bodyCell);
        //
        JSONAssert.assertEquals(jsonDatepickerCell, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeDatepickerCell() throws JsonProcessingException {
        //
        BodyCell bodyCell = new ObjectMapper().readValue(jsonDatepickerCell, BodyCell.class);
        //
        assertEquals("foo-id", bodyCell.getId());
        assertEquals(ComponentTypeEnum.DATEPICKER, bodyCell.getComponentType());
        assertEquals("24/05/1950", bodyCell.getMin());
        assertEquals("02/04/2030", bodyCell.getMax());
        assertEquals("%", bodyCell.getUnitLabel().getValue());
        assertEquals(LabelTypeEnum.VTL_MD, bodyCell.getUnitLabel().getType());
        assertEquals("FOO", bodyCell.getResponse().getName());
    }

}
