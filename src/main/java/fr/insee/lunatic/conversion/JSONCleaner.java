package fr.insee.lunatic.conversion;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.utils.XslTransformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * remove technical attribute as xsi:type
 * 
 * @author xbeltv
 *
 */
public class JSONCleaner {

	private static XslTransformation saxonService = new XslTransformation();

	private static final Logger logger = LoggerFactory.getLogger(JSONCleaner.class);

	
	public String clean(String jsonString) throws Exception {

		// New step: apply (Java) symLinks cleaning
		jsonString = symLinksCleaning(jsonString);

		if ((jsonString == null) || (jsonString.length() == 0))
			return null;
		InputStream json = new ByteArrayInputStream(wrapJsonWithXml(jsonString).getBytes("UTF-8"));

		return this.generate(json);
	}
	
	public String generate(InputStream isFinalInput) throws Exception {
		// Unchanged step: use XSLT "cleaning" sheet
		OutputStream osOutputFile = generateOS(isFinalInput);
		String res = osOutputFile.toString();
		osOutputFile.close();

		return res;
	}

	public OutputStream generateOS(InputStream isFinalInput) throws Exception {
		logger.info("Clean json output : START");

		InputStream xslSheet = JSONCleaner.class.getClassLoader()
				.getResourceAsStream(Constants.TRANSFORMATION_JSON_2_JSON_CLEANED);
		
		OutputStream osOutputFile = new ByteArrayOutputStream();
		saxonService.transformJSONLunaticToJSONLunaticClean(isFinalInput, osOutputFile, xslSheet);

		xslSheet.close();

		return osOutputFile;
	}
	
	public String wrapJsonWithXml(String json) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Questionnaire>"+preProcessJson2XML(json)+"</Questionnaire>";
	}
	
	public String preProcessJson2XML(String json) {
		return json.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	/**
	 * Given a Lunatic JSON flat questionnaire, replace the source/target fields by key/values in "symLinks" attribute
	 * in "PairwiseLinks" components to be compliant with the Lunatic JS library. */
	public String symLinksCleaning(String stringQuestionnaire) throws IOException {

		// Read the json string into a JsonObject
		JsonReader jsonReader = Json.createReader(
				new ByteArrayInputStream(stringQuestionnaire.getBytes(StandardCharsets.UTF_8)));
		JsonObject jsonQuestionnaire = jsonReader.readObject();

		// // We will copy the entire input json object, except the "symLinks" attribute in PairwiseLinks components
		JsonObjectBuilder jsonQuestionnaireBuilder = Json.createObjectBuilder();

		jsonQuestionnaire.forEach((key, jsonValue) -> {
			if (!key.equals("components")) {
				jsonQuestionnaireBuilder.add(key, jsonValue);
			} else {
				JsonArray jsonComponents = (JsonArray) jsonValue;
				JsonArrayBuilder jsonComponentsBuilder = Json.createArrayBuilder();
				for (JsonValue jsonValue1 : jsonComponents) {
					JsonObject jsonComponent = (JsonObject) jsonValue1;
					if (!jsonComponent.getString("componentType").equals("PairwiseLinks")) {
						jsonComponentsBuilder.add(jsonValue1);
					} else {
						JsonObjectBuilder jsonPairwiseBuilder = Json.createObjectBuilder();
						((JsonObject) jsonValue1).forEach((key2, jsonValue2) -> {
							if (!key2.equals("symLinks")) {
								jsonPairwiseBuilder.add(key2, jsonValue2);
							} else {
								JsonArray jsonSymLinks = (JsonArray) jsonValue2;
								JsonObjectBuilder jsonSymLinksBuilder = Json.createObjectBuilder();
								JsonObjectBuilder jsonLINKSBuilder = Json.createObjectBuilder();
								((JsonObject) jsonSymLinks.get(0)).getJsonObject("LINKS")
										.getJsonArray("LINK").forEach(jsonValue3 -> {
											JsonObject jsonSourceTarget = (JsonObject) jsonValue3;
											String sourceKey = String.valueOf(jsonSourceTarget.get("source"));
											if (jsonSourceTarget.get("target") != null) {
												String targetKey = String.valueOf(jsonSourceTarget.get("target"));
												jsonLINKSBuilder.add(sourceKey, targetKey);
											} else {
												jsonLINKSBuilder.addNull(sourceKey);
											}
										});
								jsonSymLinksBuilder.add("LINKS", jsonLINKSBuilder.build());
								jsonPairwiseBuilder.add("symLinks", jsonSymLinksBuilder.build());
							}
						});
						jsonComponentsBuilder.add(jsonPairwiseBuilder.build());
					}
				}
				jsonQuestionnaireBuilder.add("components", jsonComponentsBuilder.build());
			}
		});

		OutputStream outputStream = new ByteArrayOutputStream();

		JsonWriter jsonWriter = Json.createWriter(outputStream);
		jsonWriter.writeObject(jsonQuestionnaireBuilder.build());

		String result = outputStream.toString();
		outputStream.close();

		return result;
	}

}
