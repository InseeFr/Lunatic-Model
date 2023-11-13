package fr.insee.lunatic.conversion;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class XMLLunaticToJSONLunaticTranslatorTest {

    @Test
    void convertQuestionnaireWithSuggester_nonNullOutput() throws Exception {
        // Given
        URL resource = this.getClass().getClassLoader().getResource("conversion/questionnaire-suggester-xml-h.xml");
        assert resource != null;
        // When
        XMLLunaticToJSONLunaticTranslator xmlToJsonFlatTranslator = new XMLLunaticToJSONLunaticTranslator();
        String result = xmlToJsonFlatTranslator.translate(Files.readString(Path.of(resource.toURI())));
        // Then
        assertNotNull(result);
    }

}
