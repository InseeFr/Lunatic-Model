package fr.insee.lunatic.conversion.data;

import fr.insee.lunatic.conversion.ConversionException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class XMLLunaticDataToJSONTest {

    private final XMLLunaticDataToJSON converter = new XMLLunaticDataToJSON();

    // Minimal valid XML structure that matches expected Lunatic schema (example)
    private final String validXml = """
            <Data>
              <COLLECTED>
                <PLOP>
                    <COLLECTED type="string">hello</COLLECTED>
                </PLOP>
              </COLLECTED>
              <CALCULATED/>
              <EXTERNAL>
                <FOO_EXTERNAL type="string">some value</FOO_EXTERNAL>
              </EXTERNAL>
            </Data>
            """;
    private final String malformedXml = "<Questionnaire><Id>Q123</Label>";

    private final String jsonExpected = """
            {
              "COLLECTED": {
                "PLOP": {
                  "COLLECTED": "hello"
                }
              },
              "CALCULATED": {},
              "EXTERNAL": {
                "FOO_EXTERNAL": "some value"
              }
            }
            """;

    @Test
    @DisplayName("Should convert a valid XML file to JSON file")
    void testTransformFromFile() throws Exception {
        File tempXmlFile = File.createTempFile("test-valid", ".xml");
        FileUtils.writeStringToFile(tempXmlFile, validXml, StandardCharsets.UTF_8);

        File jsonOutputFile = converter.transform(tempXmlFile);

        assertNotNull(jsonOutputFile);
        assertTrue(jsonOutputFile.exists());
        String jsonResult = FileUtils.readFileToString(jsonOutputFile, StandardCharsets.UTF_8);
        JSONAssert.assertEquals(jsonExpected, jsonResult, JSONCompareMode.STRICT);

        tempXmlFile.delete();
        jsonOutputFile.delete();
    }

    @Test
    @DisplayName("Should convert a valid XML InputStream to JSON OutputStream")
    void testTransformFromInputStream() throws Exception {
        InputStream xmlStream = new ByteArrayInputStream(validXml.getBytes(StandardCharsets.UTF_8));
        OutputStream jsonStream = converter.transform(xmlStream);

        assertNotNull(jsonStream);
        String jsonResult = jsonStream.toString();
        JSONAssert.assertEquals(jsonExpected, jsonResult, JSONCompareMode.STRICT);
    }

    @Test
    @DisplayName("Should throw ConversionException for invalid XML file")
    void testInvalidXmlFileThrowsException() throws IOException {
        File invalidXmlFile = File.createTempFile("test-invalid", ".xml");
        FileUtils.writeStringToFile(invalidXmlFile, malformedXml, StandardCharsets.UTF_8);

        assertThrows(ConversionException.class, () -> converter.transform(invalidXmlFile));

        invalidXmlFile.delete();
    }

    @Test
    @DisplayName("Should throw ConversionException for malformed XML stream")
    void testInvalidXmlInputStreamThrowsException() {
        InputStream malformedStream = new ByteArrayInputStream(malformedXml.getBytes(StandardCharsets.UTF_8));

        assertThrows(ConversionException.class, () -> converter.transform(malformedStream));
    }
}
