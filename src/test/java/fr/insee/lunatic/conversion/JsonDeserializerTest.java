package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.ComponentTypeName;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonDeserializerTest {

    @Test
    void deserialize_simplestCase() throws SerializationException {
        //
        String jsonInput = "{\"id\":\"foo-id\",\"componentType\":\"Questionnaire\"}";
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        assertNotNull(questionnaire);
        assertEquals("foo-id", questionnaire.getId());
        assertEquals(ComponentTypeName.QUESTIONNAIRE, questionnaire.getComponentTypeName());
    }

}
