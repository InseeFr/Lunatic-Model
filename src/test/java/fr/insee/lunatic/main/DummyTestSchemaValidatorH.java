package fr.insee.lunatic.main;

import fr.insee.lunatic.utils.Modele;
import fr.insee.lunatic.utils.SchemaValidator;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;


public class DummyTestSchemaValidatorH {
	
	private static final Logger logger = LoggerFactory.getLogger(DummyTestSchemaValidatorH.class);

	public static void main(String[] args) {

		String basePath = "src/test/resources/dummy";
		File in = new File(String.format("%s/form.xml", basePath));
		try {
			SchemaValidator schemaValidator = new SchemaValidator(Modele.HIERARCHICAL);
			logger.info("Valid : "+schemaValidator.validateFile(in));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
