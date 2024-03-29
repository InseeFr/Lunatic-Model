package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonSerializer {

	private static final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

	public String serialize(Questionnaire questionnaire) throws SerializationException {
		if (questionnaire == null) {
			logger.warn("null questionnaire given, empty string will be returned.");
			return "";
		}

		logger.info("Serializing questionnaire {}", questionnaire.getId());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		String jsonQuestionnaire;

		try {
			jsonQuestionnaire = objectMapper.writeValueAsString(questionnaire);
		} catch (JsonProcessingException e) {
			throw new SerializationException(e.getMessage(), e);
		}

		logger.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return jsonQuestionnaire;
	}

}
