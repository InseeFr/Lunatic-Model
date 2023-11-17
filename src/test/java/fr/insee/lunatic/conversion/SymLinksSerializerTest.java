package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.SymLinksType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SymLinksSerializerTest {

    @Test
    void serialize_simplestCase() throws JsonProcessingException {
        //
        SymLinksType symLinksType = new SymLinksType();
        symLinksType.setName("FOO_LINKS");
        symLinksType.getLink().add(new SymLinksType.LINK("1", "2"));
        symLinksType.getLink().add(new SymLinksType.LINK("2", "1"));
        //
        String result = new ObjectMapper().writerFor(SymLinksType.class).writeValueAsString(symLinksType);
        //
        assertEquals("{\"FOO_LINKS\":{\"1\":\"2\",\"2\":\"1\"}}", result);
    }

}
