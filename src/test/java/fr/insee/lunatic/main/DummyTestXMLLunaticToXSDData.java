package fr.insee.lunatic.main;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;
import fr.insee.lunatic.conversion.data.XMLLunaticToXSDData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DummyTestXMLLunaticToXSDData {
	
	private static final Logger logger = LoggerFactory.getLogger(DummyTestXMLLunaticToXSDData.class);

	public static void main(String[] args) {

		String basePath = "src/test/resources/dummy";
		File in = new File(String.format("%s/form.xml", basePath));

		try {
			XMLLunaticToXSDData translator = new XMLLunaticToXSDData();

			
			File xsdData = translator.transform(in);
			logger.info("File generated at : "+xsdData.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
