package fr.insee.lunatic.conversion;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.ComponentType;

public class XMLSerializer {

	public XMLSerializer() { }

	private static final Logger logger = LoggerFactory.getLogger(XMLSerializer.class);

	public String serialize(Questionnaire questionnaire) throws JAXBException, UnsupportedEncodingException {

		if (questionnaire == null) return "";

		logger.debug("Serializing questionnaire " + questionnaire.getId());

		JAXBContext context = JAXBContext.newInstance(Questionnaire.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(questionnaire, baos);
	
		return baos.toString("UTF-8");

	}

	public String serialize(ComponentType component) throws JAXBException, UnsupportedEncodingException {

		if (component == null) return "";

		logger.debug("Serializing component " + component.getId());

		JAXBContext context = JAXBContext.newInstance(ComponentType.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(component, baos);

		return baos.toString("UTF-8");
	}



}
