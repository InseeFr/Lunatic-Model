package fr.insee.lunatic.test;

import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.JSONDeserializer;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;
import fr.insee.lunatic.model.flat.PairwiseLinks;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Test class to see if PairwiseLinks object doesn't break translators */
class PairwiseTest {

    @Test
    void deserializeQuestionnaireContainingPairwise_doesContainPairwise() throws JAXBException {
        //
        JSONDeserializer jsonDeserializer = new JSONDeserializer();
        //
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                this.getClass().getClassLoader().getResourceAsStream("pairwise/pairwise-flat.json"));
        //
        assertNotNull(questionnaire);
        assertTrue(questionnaire.getComponents().stream()
                .anyMatch(componentType -> componentType instanceof PairwiseLinks));
    }

    @Test
    void transformAndClean_questionnaireWithPairwise_doesNotThrow() throws Exception {
        //
        XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
        XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
        JSONCleaner jsonCleaner = new JSONCleaner();
        //
        String result = jsonCleaner.clean(
                translator2.translate(
                        translator.generate(
                                this.getClass().getClassLoader().getResourceAsStream("pairwise/pairwise-hierarchical-test.xml")
                        )));
        //
        assertNotNull(result);
    }

    @Test
    void transformAndClean_questionnaireWithPairwise_writeOutput() throws Exception {
        //
        XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
        XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
        JSONCleaner jsonCleaner = new JSONCleaner();
        //
        URL pairwiseFileUrl = this.getClass().getClassLoader().getResource("pairwise/pairwise-hierarchical-test.xml");
        assert pairwiseFileUrl != null;

        //
        String xmlFlat = translator.generate(pairwiseFileUrl.openStream());
        String jsonFlat = translator2.translate(xmlFlat);
        String result = jsonCleaner.clean(jsonFlat);

        //
        assertNotNull(result);
        //
        Path pairwisePath = Path.of(pairwiseFileUrl.toURI()).getParent();
        Path outPath = Files.createTempDirectory(pairwisePath, "out");
        System.out.printf("Writing test pairwise outputs in %s", outPath);
        Files.writeString(
                outPath.resolve("pairwise-flat.xml"),
                xmlFlat);
        Files.writeString(
                outPath.resolve("pairwise-flat.json"),
                jsonFlat);
        Files.writeString(
                outPath.resolve("pairwise-cleaned.json"),
                result);
    }

}
