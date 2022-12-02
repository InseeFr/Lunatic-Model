package fr.insee.lunatic.test;

import fr.insee.lunatic.conversion.JSONSymLinksCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;

public class JSONSymLinksCleanerTest {

    JSONSymLinksCleaner symLinksCleaner;

    @BeforeEach
    public void initCleaner() {
        symLinksCleaner = new JSONSymLinksCleaner();
    }

    @Test
    public void nullString() throws IOException {
        assertNull(symLinksCleaner.clean(null));
    }
    
}
