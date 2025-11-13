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

class TableSerializationTest {

    private final String tableWithText = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "componentType": "Table",
                  "body": [
                    [
                      {
                        "componentType": "Text",
                        "id": "text-cell-id",
                        "label": {
                          "value": "\\"Label value.\\"",
                          "type": "VTL|MD"
                        }
                      }
                    ]
                  ]
                }
              ]
            }""";

    @Test
    void serializeTableWithTextCells() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        Table table = new Table();
        table.getBodyLines().add(new BodyLine());
        BodyCell textCell = new BodyCell();
        textCell.setId("text-cell-id");
        textCell.setComponentType(ComponentTypeName.TEXT);
        textCell.setLabel(new LabelType());
        textCell.getLabel().setType(LabelTypeEnum.VTL_MD);
        textCell.getLabel().setValue("\"Label value.\"");
        table.getBodyLines().getFirst().getBodyCells().add(textCell);
        questionnaire.getComponents().add(table);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        JSONAssert.assertEquals(tableWithText, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeTableWithTextCells() throws SerializationException {
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(
                new ByteArrayInputStream(tableWithText.getBytes()));
        //
        Table table = assertInstanceOf(Table.class, questionnaire.getComponents().getFirst());
        assertEquals(1, table.getBodyLines().size());
        assertEquals(1, table.getBodyLines().getFirst().getBodyCells().size());
        BodyCell textCell = table.getBodyLines().getFirst().getBodyCells().getFirst();
        assertEquals(ComponentTypeName.TEXT, textCell.getComponentType());
        assertEquals("\"Label value.\"", textCell.getLabel().getValue());
        assertEquals(LabelTypeEnum.VTL_MD, textCell.getLabel().getType());
    }

}
