package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;

public class JSONDeserializer {

	public JSONDeserializer() {	}

	private static final Logger logger = LoggerFactory.getLogger(JSONDeserializer.class);

	public Questionnaire deserialize(String fileName) throws IOException {

		if ((fileName == null) || (fileName.length() == 0)) return null;

		logger.info("Deserializing questionnaire from file {}", fileName);

		ObjectMapper mapper = new ObjectMapper();
		Questionnaire questionnaire = mapper.readValue(new StreamSource(fileName).getInputStream(), Questionnaire.class);
		logger.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return questionnaire;
	}

	public Questionnaire deserialize(InputStream jsonQuestionnaire) throws IOException {
		if (jsonQuestionnaire == null) return null;

		logger.debug("Deserializing questionnaire from input stream");

		ObjectMapper mapper = new ObjectMapper();
		Questionnaire questionnaire = mapper.readValue(jsonQuestionnaire, Questionnaire.class);
		logger.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return questionnaire;
	}
}
