package fr.insee.lunatic.conversion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import fr.insee.lunatic.model.hierarchical.Questionnaire;

import static fr.insee.lunatic.Constants.LUNATIC_MODEL_VERSION;

public class XMLLunaticToJSONLunaticTranslator {

	private boolean monitored;

	public XMLLunaticToJSONLunaticTranslator() {
		this(false);
	}

	public XMLLunaticToJSONLunaticTranslator(boolean monitored) {
		this.monitored = monitored;
	}

	private static final Logger logger = LoggerFactory.getLogger(XMLLunaticToJSONLunaticTranslator.class);

	public String translate(File xmlFile) throws JAXBException, UnsupportedEncodingException {

		if (xmlFile == null)
			return null;
		StreamSource xml = new StreamSource(xmlFile);

		return this.translate(xml);
	}

	public String translate(String xmlString) throws JAXBException, UnsupportedEncodingException {

		if ((xmlString == null) || (xmlString.length() == 0))
			return null;
		StreamSource xml = new StreamSource(new StringReader(xmlString));

		return this.translate(xml);
	}

	public String translate(StreamSource xmlStream) throws JAXBException, UnsupportedEncodingException {

		if (xmlStream == null)
			return null;

		logger.debug("Preparing to translate from XML to JSONH");

		JAXBContext context = JAXBContext.newInstance(Questionnaire.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		if (monitored)
			unmarshaller.setListener(new UnmarshallLogger());

		Questionnaire questionnaire = (Questionnaire) unmarshaller.unmarshal(xmlStream);
		// Set lunatic-model-version to json questionnaire
		questionnaire.setLunaticModelVersion(LUNATIC_MODEL_VERSION);

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

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
