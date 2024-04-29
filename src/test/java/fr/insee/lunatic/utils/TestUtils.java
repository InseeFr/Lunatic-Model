package fr.insee.lunatic.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestUtils {

    private TestUtils() {}

    /**
     * Reads the text content of the file
     * @param relativePath Relative class loader path (i.e. resource folder)
     * @return String content of the file.
     */
    public static String readResourceFile(String relativePath) throws IOException {
        URL testFileUrl = TestUtils.class.getClassLoader().getResource(relativePath);
        if (testFileUrl == null)
            throw new IOException("No file found at relative path " + relativePath);
        try {
            return Files.readString(Path.of(testFileUrl.toURI()));
        } catch (URISyntaxException e) {
            throw new IOException("Error when converting path " + relativePath + "to URI.");
        }
    }

}
