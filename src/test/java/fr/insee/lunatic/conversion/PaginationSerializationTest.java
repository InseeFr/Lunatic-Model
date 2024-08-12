package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Pagination;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationSerializationTest {

    @Test
    void serializePagination() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setPagination(Pagination.QUESTION);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        String expected = """
                {"componentType": "Questionnaire", "pagination": "question"}""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializePagination() throws SerializationException {
        //
        String jsonInput = """
                {"componentType": "Questionnaire", "pagination": "question"}""";
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        assertEquals(Pagination.QUESTION, questionnaire.getPaginationEnum());
        assertEquals("question", questionnaire.getPagination()); // backward compatibility test
    }

    // backward compatibility test
    @Test
    void serializeStringPagination() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setPagination("question");
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        String expected = """
                {"componentType": "Questionnaire", "pagination": "question"}""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

}
