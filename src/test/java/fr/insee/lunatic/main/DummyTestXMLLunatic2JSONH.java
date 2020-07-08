package fr.insee.lunatic.main;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.XMLLunaticToJSONLunaticTranslator;

public class DummyTestXMLLunatic2JSONH {
	
	private static final Logger logger = LoggerFactory.getLogger(DummyTestXMLLunatic2JSONH.class);

	public static void main(String[] args) {

		String basePath = "src/test/resources/dummy";
		File in = new File(String.format("%s/form.xml", basePath));

		try {
			Path outPath = Paths.get(Constants.TEMP_FOLDER_PATH + "/out-H.json");
			Path outPath_temp = Paths.get(Constants.TEMP_FOLDER_PATH + "/out-H-temp.json");
			Files.deleteIfExists(outPath);
			Files.deleteIfExists(outPath_temp);
			Path outputFile = Files.createFile(outPath);
			Path outputFile_temp = Files.createFile(outPath_temp);

			XMLLunaticToJSONLunaticTranslator translator = new XMLLunaticToJSONLunaticTranslator();
			JSONCleaner jsonCleaner = new JSONCleaner();
			String jsonQuestionnaire_temp = translator.translate(in);
			
			Files.write(outputFile_temp, jsonQuestionnaire_temp.getBytes("UTF-8"));
			logger.info("File generated at : "+outputFile_temp);
			String jsonQuestionnaire = jsonCleaner.clean(jsonQuestionnaire_temp);
			Files.write(outputFile, jsonQuestionnaire.getBytes("UTF-8"));
			logger.info("File generated at : "+outPath);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
	
	
	XMLLunaticToJSONLunaticTranslator translator = new XMLLunaticToJSONLunaticTranslator();

}
