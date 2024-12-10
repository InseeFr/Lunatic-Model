package fr.insee.lunatic.conversion.data;

import org.junit.jupiter.api.Test;
import org.xmlunit.assertj3.XmlAssert;

class JsonToXmlDataTest {

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
        JSONLunaticDataToXML converter = new JSONLunaticDataToXML();
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
