package fr.insee.lunatic.conversion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.model.flat.Questionnaire;

public class JSONLunaticFlatToXMLLunaticFlatTranslator {

	private boolean monitored;

	public JSONLunaticFlatToXMLLunaticFlatTranslator() {
		this(false);
	}

	public JSONLunaticFlatToXMLLunaticFlatTranslator(boolean monitored) {
		this.monitored = monitored;
	}

	private static final Logger logger = LoggerFactory.getLogger(JSONLunaticFlatToXMLLunaticFlatTranslator.class);

	public String translate(File jsonFile) throws JAXBException, UnsupportedEncodingException {

		if (jsonFile == null)
			return null;
		StreamSource json = new StreamSource(jsonFile);

		return this.translate(json);
	}

	public String translate(String jsonString) throws JAXBException, UnsupportedEncodingException {

		if ((jsonString == null) || (jsonString.length() == 0))
			return null;
		StreamSource json = new StreamSource(new StringReader(jsonString));

		return this.translate(json);
	}

	public String translate(StreamSource jsonStream) throws JAXBException, UnsupportedEncodingException {

		if (jsonStream == null)
			return null;

		logger.debug("Preparing to translate from JSON to XML");

		JAXBContext context = JAXBContext.newInstance(Questionnaire.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		if (monitored)
			unmarshaller.setListener(new UnmarshallLogger());

		Questionnaire questionnaireNoRoot = unmarshaller.unmarshal(jsonStream, Questionnaire.class).getValue();
		logger.debug("Questionnaire unmarshalled from JSON source, questionnaire id:" + questionnaireNoRoot.getId());

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, Constants.JAXB_SCHEMA_LOCATION);

		// The JSON document does not specify the root element, so we have to
		// add it explicitly
		QName qName = new QName(Constants.NAMESPACE_URI, Constants.PREFIX);
		JAXBElement<Questionnaire> questionnaire = new JAXBElement<Questionnaire>(qName, Questionnaire.class,
				questionnaireNoRoot);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(questionnaire, baos);

		logger.debug("Translation complete");

		return baos.toString("UTF-8");
	}

	private class UnmarshallLogger extends Unmarshaller.Listener {

		@Override
		public void beforeUnmarshal(Object target, Object parent) {
			logger.debug("Before unmarshalling object " + target);
		}

		@Override
		public void afterUnmarshal(Object target, Object parent) {
			logger.debug("After unmarshalling object " + target);
		}
	}
}