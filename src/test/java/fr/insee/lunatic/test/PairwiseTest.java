package fr.insee.lunatic.test;

import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;
import org.junit.jupiter.api.Test;

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

}
