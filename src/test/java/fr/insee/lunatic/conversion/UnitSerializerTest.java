package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.InputNumber;
import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class UnitSerializerTest {

    @Test
    void serializeStringUnit() throws JsonProcessingException, JSONException {
        //
        InputNumber.Unit unit = new InputNumber.Unit();
        unit.setValue("kg");
        //
        String result = new ObjectMapper().writeValueAsString(unit);
        //
        JSONAssert.assertEquals("\"kg\"", result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeLabelUnit() throws JsonProcessingException, JSONException {
        //
        InputNumber.Unit unit = new InputNumber.Unit();
        unit.setLabel(new LabelType());
        unit.getLabel().setValue("\"kg\"");
        unit.getLabel().setType(LabelTypeEnum.VTL_MD);
        //
        String result = new ObjectMapper().writeValueAsString(unit);
        //
        String expected = """
                {"value": "\\"kg\\"", "type": "VTL|MD"}""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

}
