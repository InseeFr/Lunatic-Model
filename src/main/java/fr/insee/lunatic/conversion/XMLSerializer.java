package fr.insee.lunatic.conversion;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.SequenceType;

public class XMLSerializer {

	public XMLSerializer() { }

	private static final Logger logger = LogManager.getLogger(XMLSerializer.class);

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

	public String serialize(SequenceType sequence) throws JAXBException, UnsupportedEncodingException {

		if (sequence == null) return "";

		logger.debug("Serializing sequence " + sequence.getId());

		JAXBContext context = JAXBContext.newInstance(SequenceType.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshaller.marshal(sequence, baos);

		return baos.toString("UTF-8");
	}



}
