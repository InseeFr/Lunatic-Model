package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONSerializerTest {

    @Test
    void serialize_simplestCase() throws JsonProcessingException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("foo-id");
        //
        JSONSerializer serializer = new JSONSerializer();
        String result = serializer.serialize(questionnaire);
        //
        assertEquals("{\"id\":\"foo-id\"}",
                result);
    }

}
