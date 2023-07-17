package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONSerializer {

	public JSONSerializer() { }

	private static final Logger logger = LoggerFactory.getLogger(JSONSerializer.class);

	public String serialize(Questionnaire questionnaire) throws JsonProcessingException {

		if (questionnaire == null) return "";

		logger.info("Serializing questionnaire {}", questionnaire.getId());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper.writeValueAsString(questionnaire);

	}
}
