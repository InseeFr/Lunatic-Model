package fr.insee.lunatic.conversion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.utils.XslTransformation;

public class XMLLunaticToXMLLunaticFlatTranslator {

	private static XslTransformation saxonService = new XslTransformation();

	private static final Logger logger = LoggerFactory.getLogger(XMLLunaticToXMLLunaticFlatTranslator.class);

	
	public String generate(String xmlString) throws Exception {

		if ((xmlString == null) || (xmlString.length() == 0))
			return null;
		InputStream xml = new ByteArrayInputStream(xmlString.getBytes());;

		return this.generate(xml);
	}
	
	public String generate(File finalInput) throws Exception {

		InputStream isFinalInput = FileUtils.openInputStream(finalInput);
		String result = this.generate(isFinalInput);
		isFinalInput.close();
		return result;

	}

	public String generate(InputStream isFinalInput) throws Exception {
		OutputStream osOutputFile = generateOS(isFinalInput);
		String res = osOutputFile.toString();
		osOutputFile.close();
		return res;
	}

	public OutputStream generateOS(InputStream isFinalInput) throws Exception {
		logger.info("XMLH2XMLF Target : START");

		InputStream xslSheet = XMLLunaticToXMLLunaticFlatTranslator.class.getClassLoader()
				.getResourceAsStream(Constants.TRANSFORMATION_XML_2_XMLF);
		
		OutputStream osOutputFile = new ByteArrayOutputStream();
		saxonService.transformXMLLunaticToXMLLunaticFlat(isFinalInput, osOutputFile, xslSheet);

		xslSheet.close();

		return osOutputFile;
	}
}
