package fr.insee.lunatic.main;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;
import fr.insee.lunatic.utils.Modele;
import fr.insee.lunatic.utils.SchemaValidator;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DummyTestSchemaValidatorF {
	
	private static final Logger logger = LoggerFactory.getLogger(DummyTestSchemaValidatorF.class);

	public static void main(String[] args) {

		String basePath = "src/test/resources/dummy";
		File in = new File(String.format("%s/form_flat.xml", basePath));
		try {
			SchemaValidator schemaValidator = new SchemaValidator(Modele.FLAT);
			logger.info("Valid : "+schemaValidator.validateFile(in));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
