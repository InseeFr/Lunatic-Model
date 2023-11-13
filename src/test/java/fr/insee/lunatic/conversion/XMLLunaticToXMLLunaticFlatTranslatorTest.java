package fr.insee.lunatic.conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class XMLLunaticToXMLLunaticFlatTranslatorTest {

    @Test
    void convertQuestionnaireWithSuggester_nonNullOutput() throws Exception {
        // Given + When
        XMLLunaticToXMLLunaticFlatTranslator xmlToXmlFlatTranslator = new XMLLunaticToXMLLunaticFlatTranslator();
        String result = xmlToXmlFlatTranslator.generate(this.getClass().getClassLoader().getResourceAsStream(
                "conversion/questionnaire-suggester-xml-h.xml"));
        // Then
        assertNotNull(result);
    }

}
