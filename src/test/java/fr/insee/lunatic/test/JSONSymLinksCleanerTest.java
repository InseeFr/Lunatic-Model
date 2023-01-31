package fr.insee.lunatic.test;

import fr.insee.lunatic.conversion.JSONSymLinksCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONSymLinksCleanerTest {

    JSONSymLinksCleaner symLinksCleaner;

    @BeforeEach
    public void initCleaner() {
        symLinksCleaner = new JSONSymLinksCleaner();
    }

    @Test
    void nullString() throws IOException {
        assertNull(symLinksCleaner.clean(null));
    }

    @Test
    void sandbox_testJsonObjectGetString() {
        JsonReader reader = Json.createReader(new ByteArrayInputStream("{\"foo\":1}".getBytes()));
        JsonObject jsonObject = reader.readObject();
        assertThrows(ClassCastException.class, () -> jsonObject.getString("foo"));
        assertEquals("1", String.valueOf(jsonObject.get("foo")));
    }

}
