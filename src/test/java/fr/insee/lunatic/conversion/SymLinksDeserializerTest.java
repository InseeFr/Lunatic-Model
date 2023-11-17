package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.SymLinksType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SymLinksDeserializerTest {

    @Test
    void deserializeSymLinks() throws JsonProcessingException {
        //
        String jsonInput = "{\"FOO_LINKS\":{\"1\":\"2\",\"2\":\"1\"}}";
        //
        SymLinksType symLinks = new ObjectMapper().readValue(jsonInput, SymLinksType.class);
        //
        assertEquals("FOO_LINKS", symLinks.getName());
        assertEquals(2, symLinks.getLink().size());
        assertEquals("1", symLinks.getLink().get(0).getSource());
        assertEquals("2", symLinks.getLink().get(0).getTarget());
        assertEquals("2", symLinks.getLink().get(1).getSource());
        assertEquals("1", symLinks.getLink().get(1).getTarget());
    }

}
