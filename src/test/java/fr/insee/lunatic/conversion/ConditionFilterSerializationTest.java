package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.ConditionFilterType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import fr.insee.lunatic.model.flat.VariablesDependencies;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void serializeConditionFilterWithVariablesDependencies() throws JsonProcessingException, JSONException {
        ConditionFilterType conditionFilterType = new ConditionFilterType();
        conditionFilterType.setValue("SUM_AGE + PRENOM + TITI");
        conditionFilterType.setType(LabelTypeEnum.VTL);
        VariablesDependencies dependencies = new VariablesDependencies();
        dependencies.getCollected().add("PRENOM");
        dependencies.getExternal().add("TITI");
        dependencies.getCalculated().add("SUM_AGE");
        conditionFilterType.setVariablesDependencies(dependencies);
        //
        String result = new ObjectMapper().writerFor(ConditionFilterType.class)
                .writeValueAsString(conditionFilterType);
        //
        String expected = """
            {"value": "SUM_AGE + PRENOM + TITI", "type": "VTL",
             "variablesDependencies": {"COLLECTED": ["PRENOM"], "EXTERNAL": ["TITI"], "CALCULATED": ["SUM_AGE"]}}
            """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeEmptyVariablesDependencies() throws JsonProcessingException, JSONException {
        ConditionFilterType conditionFilterType = new ConditionFilterType();
        conditionFilterType.setValue("true");
        conditionFilterType.setType(LabelTypeEnum.VTL);
        conditionFilterType.setVariablesDependencies(new VariablesDependencies());
        //
        String result = new ObjectMapper().writerFor(ConditionFilterType.class)
                .writeValueAsString(conditionFilterType);
        //
        String expected = """
            {"value": "true", "type": "VTL",
             "variablesDependencies": {"COLLECTED": [], "EXTERNAL": [], "CALCULATED": []}}
            """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeVariablesDependenciesWithOnlyOneList() throws JsonProcessingException, JSONException {
        ConditionFilterType conditionFilterType = new ConditionFilterType();
        conditionFilterType.setValue("TITI = 1");
        conditionFilterType.setType(LabelTypeEnum.VTL);
        VariablesDependencies dependencies = new VariablesDependencies();
        dependencies.getExternal().add("TITI");
        conditionFilterType.setVariablesDependencies(dependencies);
        //
        String result = new ObjectMapper().writerFor(ConditionFilterType.class)
                .writeValueAsString(conditionFilterType);
        //
        String expected = """
            {"value": "TITI = 1", "type": "VTL",
             "variablesDependencies": {"COLLECTED": [], "EXTERNAL": ["TITI"], "CALCULATED": []}}
            """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeConditionFilterWithVariablesDependencies() throws JsonProcessingException {
        String jsonInput = """
            {"value": "SUM_AGE + PRENOM + TITI", "type": "VTL",
             "variablesDependencies": {"COLLECTED": ["PRENOM"], "EXTERNAL": ["TITI"], "CALCULATED": ["SUM_AGE"]}}
            """;
        //
        ConditionFilterType conditionFilterType = new ObjectMapper().readValue(jsonInput, ConditionFilterType.class);
        //
        VariablesDependencies dependencies = conditionFilterType.getVariablesDependencies();
        assertEquals(List.of("PRENOM"), dependencies.getCollected());
        assertEquals(List.of("TITI"), dependencies.getExternal());
        assertEquals(List.of("SUM_AGE"), dependencies.getCalculated());
    }

    @Test
    void deserializeConditionFilterWithoutVariablesDependencies() throws JsonProcessingException {
        String jsonInput = """
            {"value": "true", "type": "VTL"}
            """;
        //
        ConditionFilterType conditionFilterType = new ObjectMapper().readValue(jsonInput, ConditionFilterType.class);
        //
        assertNull(conditionFilterType.getVariablesDependencies());
    }
}
