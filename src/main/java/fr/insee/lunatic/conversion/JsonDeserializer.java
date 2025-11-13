package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Slf4j
public class JsonDeserializer {

	public Questionnaire deserialize(InputStream jsonQuestionnaire) throws SerializationException {
		if (jsonQuestionnaire == null) return null;

		log.debug("Deserializing questionnaire from input stream");

		ObjectMapper mapper = new ObjectMapper();
		Questionnaire questionnaire;
		try {
			questionnaire = mapper.readValue(jsonQuestionnaire, Questionnaire.class);
		} catch (IOException e) {
			throw new SerializationException(e.getMessage(), e);
		}
		log.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return questionnaire;
	}

}
