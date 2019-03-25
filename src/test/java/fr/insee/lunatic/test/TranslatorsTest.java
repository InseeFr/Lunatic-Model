package fr.insee.lunatic.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.diff.Diff;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.JSONLunaticFlatToXMLLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;

public class TranslatorsTest {

	private XMLDiff xmlDiff = new XMLDiff();
	
	private static final Logger logger = LoggerFactory.getLogger(TranslatorsTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testQuestionnaireJSONFToXMLF() {
		logger.debug("Launch test : JSONLunaticFlat -> XMLLunaticFlat");
				
		try {
			String basePath = Constants.RESOURCES_FOLDER_JSONF_2_XMLF_PATH;
			
			Path outputFile = Files.createTempFile(Constants.TEMP_FOLDER, "jsonf-2-xmlf-out", ".xml");
			
			JSONLunaticFlatToXMLLunaticFlatTranslator translator = new JSONLunaticFlatToXMLLunaticFlatTranslator(true);
			File in = new File(String.format("%s/in.json", basePath));
			String xmlQuestionnaire = translator.translate(in);
			Files.write(outputFile, xmlQuestionnaire.getBytes());
			logger.debug("File generated at : "+outputFile.toString());
			
			File expectedFile = new File(String.format("%s/out.xml", basePath));
			Diff diff = xmlDiff.getDiff(outputFile.toFile(),expectedFile);
			Assert.assertFalse(getDiffMessage(diff, basePath), diff.hasDifferences());
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}

	@Test
	public void testQuestionnaireXMLFToJSONF() {
		logger.debug("Launch test : XMLLunaticFlat -> JSONLunaticFlat");
		try {
			String basePath = Constants.RESOURCES_FOLDER_XMLF_2_JSONF_PATH;
			Path outputFile = Files.createTempFile(Constants.TEMP_FOLDER, "xmlf-2-jsonf-out", ".json");

			XMLLunaticFlatToJSONLunaticFlatTranslator translator = new XMLLunaticFlatToJSONLunaticFlatTranslator();
			File in = new File(String.format("%s/in.xml", basePath));
			String jsonQuestionnaire = translator.translate(in);
			JSONObject jsonOut = new JSONObject(jsonQuestionnaire);
			Files.write(outputFile, jsonQuestionnaire.getBytes());
			logger.debug("File generated at : "+outputFile.toString());

			Path expectedFile = Paths.get(String.format("%s/out.json", basePath));
			String jsonExpectedString = new String(Files.readAllBytes(expectedFile),StandardCharsets.UTF_8);

			JSONAssert.assertEquals(jsonExpectedString, jsonOut, false);

		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	public void testQuestionnaireXMLHToXMLF() {
		logger.debug("Launch test : XMLLunatic -> XMLLunaticFlat");

		try {
			String basePath = Constants.RESOURCES_FOLDER_XMLH_2_XMLF_PATH;
			
			Path outputFile = Files.createTempFile(Constants.TEMP_FOLDER, "xmlh-2-xmlf-out", ".xml");
			
			XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
			File in = new File(String.format("%s/in.xml", basePath));
			String xmlQuestionnaire = translator.generate(in);
			Files.write(outputFile, xmlQuestionnaire.getBytes());
			logger.debug("File generated at : "+outputFile.toString());
			
			File expectedFile = new File(String.format("%s/out.xml", basePath));
			Diff diff = xmlDiff.getDiff(outputFile.toFile(),expectedFile);
			Assert.assertFalse(getDiffMessage(diff, basePath), diff.hasDifferences());
			
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	public void testQuestionnaireXMLHToJSONF() {
		logger.debug("Launch test : XMLLunatic -> JSONLunaticFlat");
		try {
			
			long startTime = System.currentTimeMillis();
			
			String basePath = Constants.RESOURCES_FOLDER_XMLH_2_JSONF_PATH;
			Path outputFile = Files.createTempFile(Constants.TEMP_FOLDER, "xmlh-2-jsonf-out", ".json");

			XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
			XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
			
			File in = new File(String.format("%s/in.xml", basePath));
			String jsonQuestionnaire = translator2.translate(translator.generate(in));
			JSONObject jsonOut = new JSONObject(jsonQuestionnaire);

			Files.write(outputFile, jsonQuestionnaire.getBytes());
			
			long elapsedTime = System.currentTimeMillis() - startTime;
			
			logger.debug("File generated at : "+outputFile.toString());
			logger.debug("Transformation time for eno-XML to json lunatic fo JS: " + elapsedTime +" ms");

			Path expectedFile = Paths.get(String.format("%s/out.json", basePath));
			String jsonExpectedString = new String(Files.readAllBytes(expectedFile),StandardCharsets.UTF_8);

			JSONAssert.assertEquals(jsonExpectedString, jsonOut, false);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
	
	private String getDiffMessage(Diff diff, String path) {
		return String.format("Transformed output for %s should match expected XML document:\n %s", path,
				diff.toString());
	}
	
	
}
