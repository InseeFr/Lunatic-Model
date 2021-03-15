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
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.diff.Diff;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.JSONCleaner;
import fr.insee.lunatic.conversion.XMLLunaticFlatToJSONLunaticFlatTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToJSONLunaticTranslator;
import fr.insee.lunatic.conversion.XMLLunaticToXMLLunaticFlatTranslator;

public class TranslatorsTest {

	private XMLDiff xmlDiff = new XMLDiff();
	
	private static final Logger logger = LoggerFactory.getLogger(TranslatorsTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	
	@Test
	public void testQuestionnaireXMLFToJSONF() {
		logger.debug("Launch test : XMLLunaticFlat -> JSONLunaticFlat");
		try {
			String basePath = Constants.RESOURCES_FOLDER_XMLF_2_JSONF_PATH;
			
			Path outPath = Paths.get(Constants.TEMP_FOLDER_PATH + "/xmlf-2-jsonf-out.json");
			Files.deleteIfExists(outPath);
			Path outputFile = Files.createFile(outPath);
			
			File in = new File(String.format("%s/form_flat.xml", Constants.RESOURCES_FOLDER_DUMMY_PATH));
			
			XMLLunaticFlatToJSONLunaticFlatTranslator translator = new XMLLunaticFlatToJSONLunaticFlatTranslator();
			JSONCleaner jsonCleaner = new JSONCleaner();
			
			String jsonQuestionnaire = jsonCleaner.clean(translator.translate(in));
			JSONObject jsonOut = new JSONObject(jsonQuestionnaire);
			
			Files.write(outputFile, jsonQuestionnaire.getBytes("UTF-8"));
			logger.debug("File generated at : "+outputFile.toString());

			Path expectedFile = Paths.get(String.format("%s/out.json", basePath));
			String jsonExpectedString = new String(Files.readAllBytes(expectedFile),StandardCharsets.UTF_8);

			JSONAssert.assertEquals(jsonExpectedString, String.valueOf(jsonOut),
					new CustomComparator(JSONCompareMode.STRICT,
							new Customization("lunaticModelVersion", (o1, o2) -> true),
							new Customization("enoCoreVersion",(o1, o2) -> true)));

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
			
			Path outPath = Paths.get(Constants.TEMP_FOLDER_PATH + "/xmlh-2-xmlf-out.xml");
			Files.deleteIfExists(outPath);
			Path outputFile = Files.createFile(outPath);
			
			File in = new File(String.format("%s/form.xml",  Constants.RESOURCES_FOLDER_DUMMY_PATH));
			
			XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();

			String xmlQuestionnaire = translator.generate(in);
			Files.write(outputFile, xmlQuestionnaire.getBytes("UTF-8"));
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
			
			Path outPath = Paths.get(Constants.TEMP_FOLDER_PATH + "/xmlh-2-jsonf-out.json");
			Files.deleteIfExists(outPath);
			Path outputFile = Files.createFile(outPath);

			File in = new File(String.format("%s/form.xml",  Constants.RESOURCES_FOLDER_DUMMY_PATH));
			
			XMLLunaticToXMLLunaticFlatTranslator translator = new XMLLunaticToXMLLunaticFlatTranslator();
			XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
			JSONCleaner jsonCleaner = new JSONCleaner();			
			
			String jsonQuestionnaire = jsonCleaner.clean(translator2.translate(translator.generate(in)));
			JSONObject jsonOut = new JSONObject(jsonQuestionnaire);

			Files.write(outputFile, jsonQuestionnaire.getBytes("UTF-8"));
			
			long elapsedTime = System.currentTimeMillis() - startTime;
			
			logger.debug("File generated at : "+outputFile.toString());
			logger.debug("Transformation time for eno-XML to json lunatic fo JS: " + elapsedTime +" ms");

			Path expectedFile = Paths.get(String.format("%s/out.json", basePath));
			String jsonExpectedString = new String(Files.readAllBytes(expectedFile),StandardCharsets.UTF_8);
			JSONAssert.assertEquals(jsonExpectedString, String.valueOf(jsonOut),
					new CustomComparator(JSONCompareMode.STRICT,
							new Customization("lunaticModelVersion", (o1, o2) -> true),
							new Customization("enoCoreVersion",(o1, o2) -> true)));
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
	public void testQuestionnaireXMLHToJSONH() {
		logger.debug("Launch test : XMLLunatic -> JSONLunaticH");
		try {
			String basePath = Constants.RESOURCES_FOLDER_XMLH_2_JSONH_PATH;
			
			Path outPath = Paths.get(Constants.TEMP_FOLDER_PATH + "/xmlh-2-jsonh-out.json");
			Files.deleteIfExists(outPath);
			Path outputFile = Files.createFile(outPath);
			
			File in = new File(String.format("%s/form.xml",  Constants.RESOURCES_FOLDER_DUMMY_PATH));

			XMLLunaticToJSONLunaticTranslator translator = new XMLLunaticToJSONLunaticTranslator();
			JSONCleaner jsonCleaner = new JSONCleaner();
			
			String jsonQuestionnaire = jsonCleaner.clean(translator.translate(in));
			JSONObject jsonOut = new JSONObject(jsonQuestionnaire);
			Files.write(outputFile, jsonQuestionnaire.getBytes("UTF-8"));
			logger.debug("File generated at : "+outputFile.toString());

			Path expectedFile = Paths.get(String.format("%s/out.json", basePath));
			String jsonExpectedString = new String(Files.readAllBytes(expectedFile),StandardCharsets.UTF_8);

			JSONAssert.assertEquals(jsonExpectedString, String.valueOf(jsonOut),
					new CustomComparator(JSONCompareMode.STRICT,
							new Customization("lunaticModelVersion", (o1, o2) -> true),
							new Customization("enoCoreVersion",(o1, o2) -> true)));

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
