package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.InputNumber;
import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputNumberSerializationTest {

    private final JsonSerializer jsonSerializer = new JsonSerializer();
    private final JsonDeserializer jsonDeserializer = new JsonDeserializer();

    private final String jsonNoUnit = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "componentType": "InputNumber",
                  "min": 0,
                  "max": 100
                }
              ]
            }""";

    private final String jsonStringUnit = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "componentType": "InputNumber",
                  "min": 0,
                  "max": 100,
                  "unit": "kg"
                }
              ]
            }""";

    private final String jsonLabelUnit = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "componentType": "InputNumber",
                  "min": 0,
                  "max": 100,
                  "unit": {
                    "value": "\\"kg\\"",
                    "type": "VTL|MD"
                  }
                }
              ]
            }""";

    @Test
    void serializeInputNumber_noUnit() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        InputNumber inputNumber = new InputNumber();
        inputNumber.setMin(0d);
        inputNumber.setMax(100d);
        questionnaire.getComponents().add(inputNumber);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonNoUnit, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeInputNumber_stringUnit() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        InputNumber inputNumber = new InputNumber();
        inputNumber.setMin(0d);
        inputNumber.setMax(100d);
        inputNumber.setUnit("kg");
        questionnaire.getComponents().add(inputNumber);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonStringUnit, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeInputNumber_labelUnit() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        InputNumber inputNumber = new InputNumber();
        inputNumber.setMin(0d);
        inputNumber.setMax(100d);
        inputNumber.setUnit(new LabelType());
        inputNumber.getUnitLabel().setValue("\"kg\"");
        inputNumber.getUnitLabel().setType(LabelTypeEnum.VTL_MD);
        questionnaire.getComponents().add(inputNumber);
        //
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonLabelUnit, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeInputNumber_noUnit() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonNoUnit.getBytes()));
        //
        InputNumber inputNumber = assertInstanceOf(InputNumber.class, questionnaire.getComponents().getFirst());
        assertNull(inputNumber.getUnit());
        assertNull(inputNumber.getUnitLabel());
    }

    @Test
    void deserializeInputNumber_stringUnit() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonStringUnit.getBytes()));
        //
        InputNumber inputNumber = assertInstanceOf(InputNumber.class, questionnaire.getComponents().getFirst());
        assertEquals("kg", inputNumber.getUnit());
    }

    @Test
    void deserializeInputNumber_labelUnit() throws SerializationException {
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonLabelUnit.getBytes()));
        //
        InputNumber inputNumber = assertInstanceOf(InputNumber.class, questionnaire.getComponents().getFirst());
        assertEquals("\"kg\"", inputNumber.getUnitLabel().getValue());
        assertEquals(LabelTypeEnum.VTL_MD, inputNumber.getUnitLabel().getType());
    }

}
