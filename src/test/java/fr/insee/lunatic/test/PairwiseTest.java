package fr.insee.lunatic.test;

import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/** Test class to see if PairwiseLinks object doesn't break translators */
public class PairwiseTest {

    @Test
    public void transformAndClean_questionnaireWithPairwise_doesNotThrow() {
        XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
        XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
        JSONCleaner jsonCleaner = new JSONCleaner();
        assertDoesNotThrow(() -> {
            jsonCleaner.clean(
                    translator2.translate(
                            translator.generate(
                                    this.getClass().getClassLoader().getResourceAsStream("pairwise/pairwise-test.xml")
                            )));
        });
    }

    @Order(1)
    @Test
    public void xmlHToXmlFlat() {
        assertDoesNotThrow(() -> {
            XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
            String result = translator.generate(this.getClass().getClassLoader().getResourceAsStream("pairwise/pairwise-test.xml"));
            Files.writeString(Path.of("src/test/resources/pairwise/out/pairwise-flat.xml"), result);
        });
    }

    @Order(2)
    @Test
    public void xmlFlatToJsonFlat() {
        assertDoesNotThrow(() -> {
            XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
            String result2 = translator2.translate(new File(this.getClass().getClassLoader().getResource("pairwise/out/pairwise-flat.xml").toURI()));
            Files.writeString(Path.of("src/test/resources/pairwise/out/pairwise-flat.json"), result2);
        });

    }

    @Order(3)
    @Test
    public void jsonFlatCleaning() {
        assertDoesNotThrow(() -> {
            JSONCleaner jsonCleaner = new JSONCleaner();
            String result3 = jsonCleaner.clean(Files.readString(Path.of("src/test/resources/pairwise/out/pairwise-flat.json")));
            Files.writeString(Path.of("src/test/resources/pairwise/out/pairwise-flat-cleaned.json"), result3);
        });
    }

}
