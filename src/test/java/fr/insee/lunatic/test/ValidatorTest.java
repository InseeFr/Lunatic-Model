package fr.insee.lunatic.test;

import fr.insee.lunatic.utils.Modele;
import fr.insee.lunatic.utils.SchemaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
			Assertions.assertTrue(schemaValidator.validateFile(in));

		} catch (Exception e) {
			Assertions.fail();
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
			Assertions.assertTrue(schemaValidator.validateFile(in));

		} catch (Exception e) {
			Assertions.fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
