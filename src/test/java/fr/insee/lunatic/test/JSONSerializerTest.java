package fr.insee.lunatic.test;

import fr.insee.lunatic.conversion.JSONSerializer;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.test.utils.JsonFormatter;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONSerializerTest {

    @Test
    void serialize_simplestCase() throws JAXBException, UnsupportedEncodingException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("foo-id");
        //
        JSONSerializer serializer = new JSONSerializer();
        String result = serializer.serialize(questionnaire);
        //
        assertEquals("{\"Questionnaire\":{\"id\":\"foo-id\"}}",
                JsonFormatter.compress(result));
    }

    @Test
    void serialize2_simplestCase() throws SerializationException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("foo-id");
        //
        JSONSerializer serializer = new JSONSerializer();
        String result = serializer.serialize2(questionnaire);
        //
        assertEquals("{\"id\":\"foo-id\"}",
                JsonFormatter.compress(result));
    }

}
