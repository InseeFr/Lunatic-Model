package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class RosterForLoopSerializationTest {

    private final String rosterForLoopWithConditionFilter = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "componentType": "RosterForLoop",
                  "header": [],
                  "components": [
                      {
                        "componentType": "InputNumber",
                        "id": "input-number-cell-id",
                        "conditionFilter": {
                            "type": "VTL",
                            "value": "AGE >= 18"
                        },
                        "conditionReadOnly": {
                            "type": "VTL",
                            "value": "AGE > 50"
                        }
                      }
                  ]
                }
              ]
            }""";

    @Test
    void serializeRosterForLoopWithConditionFilterInCell() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        RosterForLoop rosterForLoop = new RosterForLoop();
        BodyCell inputNumberCell = new BodyCell();
        inputNumberCell.setComponentType(ComponentTypeName.INPUT_NUMBER);
        inputNumberCell.setId("input-number-cell-id");
        ConditionFilterType conditionFilter = new ConditionFilterType();
        conditionFilter.setType(LabelTypeEnum.VTL);
        conditionFilter.setValue("AGE >= 18");
        ConditionFilterType conditionReadOnly = new ConditionFilterType();
        conditionReadOnly.setType(LabelTypeEnum.VTL);
        conditionReadOnly.setValue("AGE > 50");
        inputNumberCell.setConditionFilter(conditionFilter);
        inputNumberCell.setConditionReadOnly(conditionReadOnly);
        rosterForLoop.getComponents().add(inputNumberCell);
        questionnaire.getComponents().add(rosterForLoop);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        JSONAssert.assertEquals(rosterForLoopWithConditionFilter, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeRosterForLoopWithConditionFilterInCell() throws SerializationException {
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(
                new ByteArrayInputStream(rosterForLoopWithConditionFilter.getBytes()));
        //
        RosterForLoop rosterForLoop = assertInstanceOf(RosterForLoop.class, questionnaire.getComponents().getFirst());
        assertEquals(1, rosterForLoop.getComponents().size());
        BodyCell inputNumberCell = rosterForLoop.getComponents().getFirst();
        assertEquals(ComponentTypeName.INPUT_NUMBER, inputNumberCell.getComponentType());
        assertEquals("AGE >= 18", inputNumberCell.getConditionFilter().getValue());
        assertEquals(LabelTypeEnum.VTL, inputNumberCell.getConditionFilter().getType());
        assertEquals("AGE > 50", inputNumberCell.getConditionReadOnly().getValue());
        assertEquals(LabelTypeEnum.VTL, inputNumberCell.getConditionReadOnly().getType());
    }

}
