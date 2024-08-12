package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.InputNumber.Unit;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitDeserializerTest {

    @Test
    void deserializeStringUnit() throws JsonProcessingException {
        //
        Unit unit = new ObjectMapper().readValue("\"kg\"", Unit.class);
        //
        assertEquals("kg", unit.getValue());
    }

    @Test
    void deserializeLabelUnit() throws JsonProcessingException {
        //
        String jsonInput = """
                {"value": "\\"kg\\"", "type": "VTL|MD"}""";
        //
        Unit unit = new ObjectMapper().readValue(jsonInput, Unit.class);
        //
        assertEquals("\"kg\"", unit.getLabel().getValue());
        assertEquals(LabelTypeEnum.VTL_MD, unit.getLabel().getType());
    }

}
