package fr.insee.lunatic.conversion.data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.insee.lunatic.Constants;
import fr.insee.lunatic.utils.XslTransformation;

/**
 * remove technical attribute as xsi:type
 * 
 * @author xbeltv
 *
 */
public class JSONLunaticDataToXML {

	private static XslTransformation saxonService = new XslTransformation();

	private static final Logger logger = LoggerFactory.getLogger(JSONLunaticDataToXML.class);

	public File transform(File input) throws Exception {

		File outputFile = Files.createTempFile("json2xml", ".xml").toFile();
		

		logger.debug("Output folder : " + outputFile.getAbsolutePath());

		String jsonString = FileUtils.readFileToString(input, StandardCharsets.UTF_8);
		InputStream inputStream = new ByteArrayInputStream(wrapJsonWithXml(jsonString).getBytes(StandardCharsets.UTF_8));
		OutputStream outputStream = FileUtils.openOutputStream(outputFile);

		InputStream XSL = XMLLunaticDataToJSON.class.getClassLoader()
				.getResourceAsStream(Constants.DATA_TRANSFORMATION_JSON_2_XML);
		try {
			saxonService.transformWithSimpleXSLSheet(inputStream,outputStream, XSL);
		}catch(Exception e) {
			String errorMessage = "An error was occured during the json to xml transformation. "+e.getMessage();
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}

		inputStream.close();
		outputStream.close();
		XSL.close();

		return outputFile;
	}

	public String transform(String jsonString) throws Exception {

		File outputFile = Files.createTempFile("json2xml", ".xml").toFile();


		logger.debug("Output folder : " + outputFile.getAbsolutePath());

		InputStream inputStream = new ByteArrayInputStream(wrapJsonWithXml(jsonString).getBytes(StandardCharsets.UTF_8));
		OutputStream outputStream = FileUtils.openOutputStream(outputFile);

		InputStream XSL = XMLLunaticDataToJSON.class.getClassLoader()
				.getResourceAsStream(Constants.DATA_TRANSFORMATION_JSON_2_XML);
		try {
			saxonService.transformWithSimpleXSLSheet(inputStream,outputStream, XSL);
		}catch(Exception e) {
			String errorMessage = "An error was occured during the json to xml transformation. "+e.getMessage();
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}

		inputStream.close();
		outputStream.close();
		XSL.close();

		return FileUtils.readFileToString(outputFile, StandardCharsets.UTF_8);
	}
	
	public String wrapJsonWithXml(String json) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data>"+preProcessJson2XML(json)+"</Data>";
	}
	
	public String preProcessJson2XML(String json) {
		return json.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
