package fr.insee.lunatic.test;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToJSONLunaticTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;
import fr.insee.lunatic.utils.Modele;
import fr.insee.lunatic.utils.SchemaValidator;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.diff.Diff;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ValidatorTest {

	private XMLDiff xmlDiff = new XMLDiff();
	
	private static final Logger logger = LoggerFactory.getLogger(ValidatorTest.class);

	
	@Test
	public void testValidateH() {
		logger.debug("Launch test : Validate Hierarchical modele");
		String basePath = "src/test/resources/dummy";
		File in = new File(String.format("%s/form.xml", basePath));
		try {
			SchemaValidator schemaValidator = new SchemaValidator(Modele.HIERARCHICAL);
			Assert.assertTrue(schemaValidator.validateFile(in));

		} catch (Exception e) {
			Assert.fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testValidateF() {
		logger.debug("Launch test : Validate flat modele");
		String basePath = "src/test/resources/dummy";
		File in = new File(String.format("%s/form_flat.xml", basePath));
		try {
			SchemaValidator schemaValidator = new SchemaValidator(Modele.FLAT);
			Assert.assertTrue(schemaValidator.validateFile(in));

		} catch (Exception e) {
			Assert.fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
