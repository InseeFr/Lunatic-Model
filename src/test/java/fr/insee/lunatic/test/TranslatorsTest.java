package fr.insee.lunatic.test;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.conversion.*;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.ConditionFilterType;
import fr.insee.lunatic.model.flat.Input;
import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.test.utils.XMLDiff;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.diff.Diff;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TranslatorsTest {

	private XMLDiff xmlDiff = new XMLDiff();

	private static final Logger logger = LoggerFactory.getLogger(TranslatorsTest.class);

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	@Disabled("Translators will be removed with Eno v3")
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
							new Customization("generatingDate", (o1, o2) -> true),
							new Customization("lunaticModelVersion", (o1, o2) -> true),
							new Customization("enoCoreVersion",(o1, o2) -> true)));

		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assertions.fail();
		}
	}

	@Test
	@Disabled("Translators will be removed with Eno v3")
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
			Assertions.assertFalse(diff.hasDifferences(), getDiffMessage(diff, basePath));

		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assertions.fail();
		}
	}

	@Test
	@Disabled("Translators will be removed with Eno v3")
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
							new Customization("generatingDate", (o1, o2) -> true),
							new Customization("lunaticModelVersion", (o1, o2) -> true),
							new Customization("enoCoreVersion",(o1, o2) -> true)));
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assertions.fail();
		}
	}

	@Test
	@Disabled("Translators will be removed with Eno v3")
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
							new Customization("generatingDate", (o1, o2) -> true),
							new Customization("lunaticModelVersion", (o1, o2) -> true),
							new Customization("enoCoreVersion",(o1, o2) -> true)));

		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (NullPointerException e) {
			e.printStackTrace();
			Assertions.fail();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Assertions.fail();
		}
	}
	
	private String getDiffMessage(Diff diff, String path) {
		return String.format("Transformed output for %s should match expected XML document:\n %s", path,
				diff.toString());
	}

	@Test
	void xmlHierarchicalToJsonFlat_resizingIssue() throws Exception {
		//
		XMLLunaticToXMLLunaticFlatTranslator translator1 = new XMLLunaticToXMLLunaticFlatTranslator();
		XMLLunaticFlatToJSONLunaticFlatTranslator translator2 = new XMLLunaticFlatToJSONLunaticFlatTranslator();
		JSONCleaner cleaner = new JSONCleaner();
		//
		String xmlFlat = translator1.generate(this.getClass().getClassLoader().getResourceAsStream(
				"pairwise/resizing-issue/lb3ei722-lunatic-hierarchical.xml"));
		String jsonFlat = translator2.translate(xmlFlat);
		String result = cleaner.clean(jsonFlat);
		//
		assertNotNull(result);
		try (JsonReader jsonReader = Json.createReader(
				new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)))) {
			JsonObject resultJson = jsonReader.readObject();
			JsonArray resizingVariables = resultJson.getJsonObject("resizing").getJsonObject("NB")
					.getJsonArray("variables");
			assertEquals(2, resizingVariables.size());
			String variableName1 = resizingVariables.getString(0);
			String variableName2 = resizingVariables.getString(1);
			assertEquals(Set.of("PRENOM", "AGE"), Set.of(variableName1, variableName2));
		}
	}

	@Test
	void serializeQuestionnaire_withConditionFilter() throws SerializationException {
		// Given
		String stringValue = "some vtl expression";
		String stringType = "type should be an enum later on";
		//
		Questionnaire questionnaire = new Questionnaire();
		Input component = new Input();
		ConditionFilterType conditionFilterType = new ConditionFilterType();
		LabelType expression = new LabelType();
		expression.setValue(stringValue);
		expression.setType(stringType);
		conditionFilterType.setExpression(expression);
		component.setConditionFilter(conditionFilterType);
		questionnaire.getComponents().add(component);
		// When
		JSONSerializer serializer = new JSONSerializer();
		String result = serializer.serialize2(questionnaire);
		// Then
		assertNotNull(result);
		try (JsonReader jsonReader = Json.createReader(
				new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)))) {
			JsonObject resultJson = jsonReader.readObject();
			JsonObject expressionJson = resultJson.getJsonArray("components").getJsonObject(0)
					.getJsonObject("conditionFilter")
					.getJsonObject("expression");
			assertEquals(stringValue, expressionJson.getString("value"));
			assertEquals(stringType, expressionJson.getString("type"));
		}
	}

}
