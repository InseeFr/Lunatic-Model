package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

public class JsonDeserializer {

	public JsonDeserializer() {	}

	private static final Logger logger = LoggerFactory.getLogger(JsonDeserializer.class);

	public Questionnaire deserialize(String fileName) throws SerializationException {

		if ((fileName == null) || (fileName.length() == 0)) return null;

		logger.info("Deserializing questionnaire from file {}", fileName);

		ObjectMapper mapper = new ObjectMapper();
		Questionnaire questionnaire;
		try {
			questionnaire = mapper.readValue(new StreamSource(fileName).getInputStream(), Questionnaire.class);
		} catch (IOException e) {
			throw new SerializationException(e.getMessage(), e);
		}
		logger.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return questionnaire;
	}

	public Questionnaire deserialize(InputStream jsonQuestionnaire) throws SerializationException {
		if (jsonQuestionnaire == null) return null;

		logger.debug("Deserializing questionnaire from input stream");

		ObjectMapper mapper = new ObjectMapper();
		Questionnaire questionnaire = null;
		try {
			questionnaire = mapper.readValue(jsonQuestionnaire, Questionnaire.class);
		} catch (IOException e) {
			throw new SerializationException(e.getMessage(), e);
		}
		logger.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return questionnaire;
	}
}
