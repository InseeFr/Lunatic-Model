package fr.insee.lunatic.conversion.data;

import fr.insee.lunatic.conversion.ConversionException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.xmlunit.assertj3.XmlAssert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

class JSONLunaticDataToXMLTest {

    private final JSONLunaticDataToXML converter = new JSONLunaticDataToXML();
    private final String validJson = """
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

    private final String xmlExpected = """
            <?xml version="1.0" encoding="UTF-8"?>
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

    private final String malformedJson = "{ invalid_json }";

    @Test
    @DisplayName("Should convert a valid JSON File to XML File")
    void testTransformFromFile() throws Exception {
        File tempJsonFile = File.createTempFile("test", ".json");
        FileUtils.writeStringToFile(tempJsonFile, validJson, StandardCharsets.UTF_8);

        File resultXmlFile = converter.transform(tempJsonFile);

        assertNotNull(resultXmlFile);
        assertTrue(resultXmlFile.exists());
        String xmlResult = FileUtils.readFileToString(resultXmlFile, StandardCharsets.UTF_8);
        XmlAssert.assertThat(xmlExpected).and(xmlResult).ignoreWhitespace().areIdentical();
        tempJsonFile.delete();
        resultXmlFile.delete();
    }

    @Test
    @DisplayName("Should convert a valid JSON InputStream to XML OutputStream")
    void testTransformFromInputStream() throws Exception {
        InputStream jsonStream = new ByteArrayInputStream(validJson.getBytes(StandardCharsets.UTF_8));
        OutputStream resultStream = converter.transform(jsonStream);

        assertNotNull(resultStream);
        String xmlResult = resultStream.toString();
        XmlAssert.assertThat(xmlExpected).and(xmlResult).ignoreWhitespace().areIdentical();

    }

    @Test
    @DisplayName("Should convert a valid JSON String to XML String")
    void testTransformFromString() throws Exception {
        String xmlResult = converter.transform(validJson);
        assertNotNull(xmlResult);
        XmlAssert.assertThat(xmlExpected).and(xmlResult).ignoreWhitespace().areIdentical();
    }

    @Test
    @DisplayName("Should throw ConversionException for invalid JSON in string")
    void testTransformInvalidInputString() {
        assertThrows(ConversionException.class, () -> converter.transform(malformedJson));
    }

    @Test
    @DisplayName("Should throw ConversionException for invalid JSON in InputStream")
    void testTransformInvalidInputStream() {
        InputStream badJsonStream = new ByteArrayInputStream(malformedJson.getBytes(StandardCharsets.UTF_8));
        assertThrows(ConversionException.class, () -> converter.transform(badJsonStream));
    }

    @Test
    @DisplayName("Should throw ConversionException for invalid JSON File")
    void testTransformInvalidFile() throws IOException {
        File tempBadJson = File.createTempFile("invalid", ".json");
        FileUtils.writeStringToFile(tempBadJson, malformedJson, StandardCharsets.UTF_8);
        assertThrows(ConversionException.class, () -> converter.transform(tempBadJson));
        tempBadJson.delete();
    }

    /**
     * Lunatic bug: in some cases pairwise data can be inconsistent.
     * Yet the data conversion shouldn't fail so as not to break the data extraction of client collection tools
     * that uses the json to xml conversion.
     */
    @Test
    void convertInconsistentPairwiseData() throws Exception {
        // Given
        String jsonInconsistentData = """
                {
                  "COLLECTED": {
                    "LIENS_MISSING": {
                      "COLLECTED": [
                        null,
                        [null, null]
                      ]
                    }
                  },
                  "CALCULATED": {},
                  "EXTERNAL": {
                    "FOO_EXTERNAL": "some value"
                  }
                }""";

        // When
        String result = converter.transform(jsonInconsistentData);

        // Then
        String expected = """
                <?xml version="1.0" encoding="UTF-8"?>
                <Data>
                  <COLLECTED>
                    <LIENS_MISSING>
                      <COLLECTED>
                        <COLLECTED type="null"/>
                        <COLLECTED>
                          <COLLECTED type="null"/>
                          <COLLECTED type="null"/>
                        </COLLECTED>
                      </COLLECTED>
                    </LIENS_MISSING>
                  </COLLECTED>
                  <CALCULATED/>
                  <EXTERNAL>
                    <FOO_EXTERNAL type="string">some value</FOO_EXTERNAL>
                  </EXTERNAL>
                </Data>
                """;
        XmlAssert.assertThat(expected).and(result).ignoreWhitespace().areIdentical();
    }

}
