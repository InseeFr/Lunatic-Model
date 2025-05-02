package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
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

    private final String jsonInputNumberCellWithDecimals = """
            {
              "id": "foo-id",
              "componentType": "InputNumber",
              "min": 0.5,
              "max": 1,
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
              "response": {
                "name": "FOO"
              }
            }""";

    private ObjectMapper objectMapper;

    /** Direct usage of jackson's object mapper since serialization/deserialization classes can only
     * work with a questionnaire object for now. */
    @BeforeEach
    void setupMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

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
        String result = objectMapper.writeValueAsString(bodyCell);
        //
        JSONAssert.assertEquals(jsonSuggesterCell, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeSuggesterCell() throws JsonProcessingException {
        //
        BodyCell bodyCell = objectMapper.readValue(jsonSuggesterCell, BodyCell.class);
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
        bodyCell.setMin(0d);
        bodyCell.setMax(100d);
        bodyCell.setUnit(new LabelType());
        bodyCell.getUnitLabel().setValue("%");
        bodyCell.getUnitLabel().setType(LabelTypeEnum.VTL_MD);
        bodyCell.setResponse(new ResponseType());
        bodyCell.getResponse().setName("FOO");
        //
        String result = objectMapper.writeValueAsString(bodyCell);
        //
        JSONAssert.assertEquals(jsonInputNumberCell, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeInputNumberCell() throws JsonProcessingException {
        //
        BodyCell bodyCell = objectMapper.readValue(jsonInputNumberCell, BodyCell.class);
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
    void serializeInputNumberCell2() throws JsonProcessingException, JSONException {
        //
        BodyCell bodyCell = new BodyCell();
        bodyCell.setId("foo-id");
        bodyCell.setComponentType(ComponentTypeEnum.INPUT_NUMBER);
        bodyCell.setMin(0.5d);
        bodyCell.setMax(1d);
        bodyCell.setResponse(new ResponseType());
        bodyCell.getResponse().setName("FOO");
        //
        String result = objectMapper.writeValueAsString(bodyCell);
        //
        JSONAssert.assertEquals(jsonInputNumberCellWithDecimals, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeInputNumberCell2() throws JsonProcessingException {
        //
        BodyCell bodyCell = objectMapper.readValue(jsonInputNumberCellWithDecimals, BodyCell.class);
        //
        assertEquals("foo-id", bodyCell.getId());
        assertEquals(ComponentTypeEnum.INPUT_NUMBER, bodyCell.getComponentType());
        assertEquals(0.5d, bodyCell.getMin());
        assertEquals(1d, bodyCell.getMax());
        assertEquals("FOO", bodyCell.getResponse().getName());
    }

    @Test
    void serializeDatepickerCell() throws JsonProcessingException, JSONException {
        //
        BodyCell bodyCell = new BodyCell();
        bodyCell.setId("foo-id");
        bodyCell.setComponentType(ComponentTypeEnum.DATEPICKER);
        bodyCell.setMin("24/05/1950");
        bodyCell.setMax("02/04/2030");
        bodyCell.setResponse(new ResponseType());
        bodyCell.getResponse().setName("FOO");
        //
        String result = objectMapper.writeValueAsString(bodyCell);
        //
        JSONAssert.assertEquals(jsonDatepickerCell, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeDatepickerCell() throws JsonProcessingException {
        //
        BodyCell bodyCell = objectMapper.readValue(jsonDatepickerCell, BodyCell.class);
        //
        assertEquals("foo-id", bodyCell.getId());
        assertEquals(ComponentTypeEnum.DATEPICKER, bodyCell.getComponentType());
        String minValue = (String) bodyCell.getMin();
        String maxValue = (String) bodyCell.getMax();
        assertEquals("24/05/1950", minValue);
        assertEquals("02/04/2030", maxValue);
        assertEquals("FOO", bodyCell.getResponse().getName());
    }

}
