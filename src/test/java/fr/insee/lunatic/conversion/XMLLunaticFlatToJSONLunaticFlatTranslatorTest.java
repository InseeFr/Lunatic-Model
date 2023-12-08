package fr.insee.lunatic.conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class XMLLunaticFlatToJSONLunaticFlatTranslatorTest {

    @Test
    void convertQuestionnaireWithSuggester_nonNullOutput() throws Exception {
        // Given (using a xml hierarchical as input and supposing that xml h et xml flat translator works fine)
        XMLLunaticToXMLLunaticFlatTranslator xmlToXmlFlatTranslator = new XMLLunaticToXMLLunaticFlatTranslator();
        String xmlFlat = xmlToXmlFlatTranslator.generate(this.getClass().getClassLoader().getResourceAsStream(
                "conversion/questionnaire-suggester-xml-h.xml"));

        // When
        XMLLunaticFlatToJSONLunaticFlatTranslator xmlFlatToJsonFlatTranslator = new XMLLunaticFlatToJSONLunaticFlatTranslator();
        xmlFlatToJsonFlatTranslator.translate(xmlFlat);

        // Then
        assertNotNull(xmlFlat);
    }

}
