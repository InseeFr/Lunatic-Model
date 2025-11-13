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

	private static final Logger logger = LoggerFactory.getLogger(JsonDeserializer.class);

	/**
	 * Deserializes the file at given relative path to a Lunatic questionnaire object.
	 * @param fileName Relative file path.
	 * @return Deserialized Lunatic questionnaire object. null if the input given is null or empty.
	 * @throws SerializationException if file cannot be found or deserialization fails.
	 * @deprecated The string file path argument is misleading, this method should be removed.
	 */
	@Deprecated(since = "3.16.0", forRemoval = true)
	public Questionnaire deserialize(String fileName) throws SerializationException {

		if ((fileName == null) || (fileName.isEmpty())) return null;

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
		Questionnaire questionnaire;
		try {
			questionnaire = mapper.readValue(jsonQuestionnaire, Questionnaire.class);
		} catch (IOException e) {
			throw new SerializationException(e.getMessage(), e);
		}
		logger.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return questionnaire;
	}
}
