package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.ConditionFilterType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionFilterSerializationTest {

    @Test
    void serializeConditionFilter() throws JsonProcessingException, JSONException {
        //
        ConditionFilterType conditionFilterType = new ConditionFilterType();
        conditionFilterType.setValue("if FOO then BAR else BAZ");
        conditionFilterType.setType(LabelTypeEnum.VTL);
        conditionFilterType.setBindingDependencies(List.of("FOO", "BAR", "BAZ"));
        conditionFilterType.setShapeFrom("QUX");
        //
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writerFor(ConditionFilterType.class).writeValueAsString(conditionFilterType);
        //
        String expected = """
                {"value": "if FOO then BAR else BAZ", "type": "VTL", "shapeFrom": "QUX" }
                """; // (binding dependencies are ignored)
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeConditionFilter() throws JsonProcessingException {
        //
        String jsonInput = """
                {"value": "if FOO then BAR else BAZ", "type": "VTL", "shapeFrom": "QUX"}
                """;
        //
        ConditionFilterType conditionFilterType = new ObjectMapper().readValue(jsonInput, ConditionFilterType.class);
        //
        assertEquals("if FOO then BAR else BAZ", conditionFilterType.getValue());
        assertEquals(LabelTypeEnum.VTL, conditionFilterType.getType());
        assertEquals("QUX", conditionFilterType.getShapeFrom());
        assertTrue(conditionFilterType.getBindingDependencies().isEmpty());
    }

}
