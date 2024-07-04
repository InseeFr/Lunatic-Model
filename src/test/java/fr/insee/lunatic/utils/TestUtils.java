package fr.insee.lunatic.utils;

import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;

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

    /**
     * Utility method to create a label object in one line in tests.
     * (Might be moved in main code if it is found useful to have it there.)
     */
    public static LabelType createLabel(String value, LabelTypeEnum type) {
        LabelType label = new LabelType();
        label.setValue(value);
        label.setType(type);
        return label;
    }

}
