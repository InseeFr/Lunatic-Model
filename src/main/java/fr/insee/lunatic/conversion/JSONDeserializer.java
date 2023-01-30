package fr.insee.lunatic.conversion;

import fr.insee.lunatic.model.flat.Questionnaire;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

public class JSONDeserializer {

	public JSONDeserializer() {	}

	private static final Logger logger = LoggerFactory.getLogger(JSONDeserializer.class);

	public Questionnaire deserialize(String fileName) throws JAXBException {

		if ((fileName == null) || (fileName.length() == 0)) return null;

		logger.debug("Deserializing questionnaire from file " + fileName);

		JAXBContext context = JAXBContext.newInstance(Questionnaire.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);

		StreamSource json = new StreamSource(fileName);
		Questionnaire questionnaire = unmarshaller.unmarshal(json, Questionnaire.class).getValue();

		logger.debug("Questionnaire " + questionnaire.getId() + " successfully deserialized");

		return questionnaire;
	}

	public Questionnaire deserialize(InputStream jsonQuestionnaire) throws JAXBException {
		if (jsonQuestionnaire == null) return null;

		logger.debug("Deserializing questionnaire from input stream");

		JAXBContext context = JAXBContext.newInstance(Questionnaire.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);

		StreamSource json = new StreamSource(jsonQuestionnaire);
		Questionnaire questionnaire = unmarshaller.unmarshal(json, Questionnaire.class).getValue();

		logger.debug("Questionnaire " + questionnaire.getId() + " successfully deserialized");

		return questionnaire;
	}


}
