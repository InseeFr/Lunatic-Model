package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSONSerializer {

	public JSONSerializer() { }

	private static final Logger logger = LoggerFactory.getLogger(JSONSerializer.class);

	public String serialize(Questionnaire questionnaire) throws JAXBException, UnsupportedEncodingException {

		if (questionnaire == null) return "";

		logger.debug("Serializing questionnaire " + questionnaire.getId());

		JAXBContext context = JAXBContext.newInstance(Questionnaire.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

		// Set it to true if you need to include the JSON root element in the JSON output
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
		// Set it to true if you need the JSON output to formatted
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshal the questionnaire object to JSON and put the output in a string

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(questionnaire, baos);

		return baos.toString("UTF-8");

	}

	/** <code>serialize</code> method returns a json string beginning with {"Questionnaire": {...}}.
	 * The need is to actually have questionnaire content directly at the root of the json tree.
	 * This method returns the json content of the given questionnaire without the "Questionnaire" level.
	 * @param questionnaire Lunatic questionnaire object (flat model).
	 * @return The questionnaire as a json string.
	 */
	public String serialize2(Questionnaire questionnaire) throws SerializationException {
		//
		String questionnaireString;
		try {
			questionnaireString = serialize(questionnaire);
		} catch (JAXBException | UnsupportedEncodingException e) {
			throw new SerializationException("Error when calling first serialize method.", e);
		}
		//
		try (JsonReader jsonReader = Json.createReader(
				new ByteArrayInputStream(questionnaireString.getBytes(StandardCharsets.UTF_8)));
			 OutputStream outputStream = new ByteArrayOutputStream();
			 JsonWriter jsonWriter = Json.createWriter(outputStream)) {
			JsonObject questionnaireJson = jsonReader.readObject();
			JsonObject contentJson = questionnaireJson.getJsonObject("Questionnaire");
			jsonWriter.writeObject(contentJson);
			return outputStream.toString();
		} catch (IOException e) {
			throw new SerializationException("Error when removing \"Questionnaire\" level of given questionnaire.", e);
		}
	}
}
