package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.MissingEntry;
import fr.insee.lunatic.model.flat.MissingType;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MissingSerializationTest {

    String jsonMissingBlockExample = """
            {
              "componentType": "Questionnaire",
              "missingBlock": {
                "SCALAR_MISSING": ["SCALAR"],
                "SCALAR": ["SCALAR_MISSING"],
                "TABLE_MISSING": ["TABLE_VAR1", "TABLE_VAR2"],
                "TABLE_VAR1": ["TABLE_MISSING"],
                "TABLE_VAR2": ["TABLE_MISSING"]
              }
            }""";

    @Test
    void serializeMissingBlock() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        MissingType missingType = new MissingType();
        MissingEntry missingEntry = new MissingEntry("SCALAR_MISSING");
        missingEntry.getCorrespondingVariables().add("SCALAR");
        MissingEntry reversedMissingEntry = new MissingEntry("SCALAR");
        reversedMissingEntry.getCorrespondingVariables().add("SCALAR_MISSING");
        MissingEntry tableMissingEntry = new MissingEntry("TABLE_MISSING");
        tableMissingEntry.getCorrespondingVariables().addAll(List.of("TABLE_VAR1", "TABLE_VAR2"));
        MissingEntry reversedTableEntry1 = new MissingEntry("TABLE_VAR1");
        reversedTableEntry1.getCorrespondingVariables().add("TABLE_MISSING");
        MissingEntry reversedTableEntry2 = new MissingEntry("TABLE_VAR2");
        reversedTableEntry2.getCorrespondingVariables().add("TABLE_MISSING");
        missingType.addMissingEntry(missingEntry);
        missingType.addMissingEntry(reversedMissingEntry);
        missingType.addMissingEntry(tableMissingEntry);
        missingType.addMissingEntry(reversedTableEntry1);
        missingType.addMissingEntry(reversedTableEntry2);
        questionnaire.setMissingBlock(missingType);
        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonMissingBlockExample, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeMissingBlock() throws SerializationException {
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                new ByteArrayInputStream(jsonMissingBlockExample.getBytes()));
        //
        MissingType missingBlock = questionnaire.getMissingBlock();
        assertNotNull(missingBlock);
        assertEquals(List.of("SCALAR"),
                missingBlock.getMissingEntry("SCALAR_MISSING").getCorrespondingVariables());
        assertEquals(List.of("SCALAR_MISSING"),
                missingBlock.getMissingEntry("SCALAR").getCorrespondingVariables());
        assertEquals(List.of("TABLE_VAR1", "TABLE_VAR2"),
                missingBlock.getMissingEntry("TABLE_MISSING").getCorrespondingVariables());
        assertEquals(List.of("TABLE_MISSING"),
                missingBlock.getMissingEntry("TABLE_VAR1").getCorrespondingVariables());
        assertEquals(List.of("TABLE_MISSING"),
                missingBlock.getMissingEntry("TABLE_VAR2").getCorrespondingVariables());
    }

}
