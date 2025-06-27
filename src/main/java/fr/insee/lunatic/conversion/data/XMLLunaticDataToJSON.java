package fr.insee.lunatic.conversion.data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.utils.XslTransformation;


public class XMLLunaticDataToJSON {

	private static XslTransformation saxonService = new XslTransformation();

	private static final Logger logger = LoggerFactory.getLogger(XMLLunaticDataToJSON.class);

	
	public File transform(File input) throws Exception {

		File outputFile = Files.createTempFile("xml2json", ".json").toFile();

		logger.debug("Output folder : " + outputFile.getAbsolutePath());

		InputStream inputStream = FileUtils.openInputStream(input);
		OutputStream outputStream = FileUtils.openOutputStream(outputFile);

		InputStream XSL = XMLLunaticDataToJSON.class.getClassLoader()
				.getResourceAsStream(Constants.DATA_TRANSFORMATION_XML_2_JSON);
		try {
			saxonService.transformXMLLunaticDataToJSON(inputStream,outputStream, XSL);
		}catch(Exception e) {
			String errorMessage = "An error was occured during the xml to json transformation. "+e.getMessage();
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}

		inputStream.close();
		outputStream.close();
		XSL.close();

		return outputFile;
	}

	/**
	 * Transform a xml lunatic data (InputStream) to JSON lunatic data (OutputStream) with xslt
	 *
	 * @param xmlInputStream   xml lunatic data
	 * @throws Exception when exception occurs
	 *
	 * @return the outputstream data in json format
	 */
	public OutputStream transform(InputStream xmlInputStream) throws Exception {
		try (InputStream xslStream = getClass().getClassLoader().getResourceAsStream(Constants.DATA_TRANSFORMATION_XML_2_JSON)) {
			ByteArrayOutputStream jsonOutputStream = new ByteArrayOutputStream();
			saxonService.transformXMLLunaticDataToJSON(xmlInputStream, jsonOutputStream, xslStream);
			return jsonOutputStream;
		} catch (Exception e) {
			String errorMessage = "An error occurred during the XML to JSON transformation: " + e.getMessage();
			logger.error(errorMessage, e);
			throw new Exception(errorMessage, e);
		}
	}
}
