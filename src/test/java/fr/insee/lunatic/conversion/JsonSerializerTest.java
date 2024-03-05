package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonSerializerTest {

    @Test
    void serialize_simplestCase() throws SerializationException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("foo-id");
        //
        JsonSerializer serializer = new JsonSerializer();
        String result = serializer.serialize(questionnaire);
        //
        assertEquals("{\"id\":\"foo-id\",\"componentType\":\"Questionnaire\"}",
                result);
    }

}
