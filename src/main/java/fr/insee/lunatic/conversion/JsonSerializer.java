package fr.insee.lunatic.conversion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.Questionnaire;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonSerializer {

	@Getter
	private final ObjectMapper mapper;

	public JsonSerializer() {
		mapper = new ObjectMapper();
		configureMapper(mapper);
	}

	private static void configureMapper(ObjectMapper objectMapper) {
		objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public String serialize(Questionnaire questionnaire) throws SerializationException {
		if (questionnaire == null) {
			log.warn("null questionnaire given, empty string will be returned.");
			return "";
		}

		log.info("Serializing questionnaire {}", questionnaire.getId());

		String jsonQuestionnaire;

		try {
			jsonQuestionnaire = mapper.writeValueAsString(questionnaire);
		} catch (JsonProcessingException e) {
			throw new SerializationException(e.getMessage(), e);
		}

		log.info("Questionnaire {} successfully deserialized", questionnaire.getId());
		return jsonQuestionnaire;
	}

}
