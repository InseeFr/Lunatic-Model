package fr.insee.lunatic.conversion.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import fr.insee.lunatic.conversion.ConversionException;
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
	private static final XslTransformation saxonService = new XslTransformation();
	private static final Logger logger = LoggerFactory.getLogger(JSONLunaticDataToXML.class);

	/**
	 *
	 * @param jsonInputFile json file in lunatic format
	 * @return xml file in lunatic format
	 * @throws ConversionException exception occurring during conversion
	 */
	public File transform(File jsonInputFile) throws Exception {
		File outputFile = Files.createTempFile("json2xml", ".xml").toFile();
		logger.debug("Output folder : {}", outputFile.getAbsolutePath());
		String jsonString = FileUtils.readFileToString(jsonInputFile, StandardCharsets.UTF_8);
		try(
				InputStream inputStream = new ByteArrayInputStream(wrapJsonWithXml(jsonString).getBytes(StandardCharsets.UTF_8));
				OutputStream outputStream = FileUtils.openOutputStream(outputFile);
				InputStream xslStream = XMLLunaticDataToJSON.class.getClassLoader()
						.getResourceAsStream(Constants.DATA_TRANSFORMATION_JSON_2_XML)
		) {
			saxonService.transformWithSimpleXSLSheet(inputStream,outputStream, xslStream);
		}catch(Exception e) {
			throw new ConversionException("Error when converting json to xml", e);
		}
		return outputFile;
	}

	/**
	 * Transformation of a lunatic json data to a lunatic xml data
	 *
	 * @param jsonInputStream data in a json format input stream
	 * @return data in a xml format
	 * @throws Exception when exceptions occurred
	 */
	public OutputStream transform(InputStream jsonInputStream) throws Exception {
		try (InputStream xslStream = XMLLunaticDataToJSON.class.getClassLoader()
				.getResourceAsStream(Constants.DATA_TRANSFORMATION_JSON_2_XML);
			 InputStream wrappedInput = wrapJsonStreamWithXml(jsonInputStream)) {

			ByteArrayOutputStream xmlOutputStream = new ByteArrayOutputStream();
			saxonService.transformWithSimpleXSLSheet(wrappedInput, xmlOutputStream, xslStream);
			return xmlOutputStream;
		} catch (Exception e) {
			throw new ConversionException("Error when converting json to xml", e);
		}
	}

	public String transform(String jsonString) throws Exception {

		File outputFile = Files.createTempFile("json2xml", ".xml").toFile();

		logger.debug("Output folder : {}", outputFile.getAbsolutePath());

		try(
				InputStream inputStream = new ByteArrayInputStream(wrapJsonWithXml(jsonString)
						.getBytes(StandardCharsets.UTF_8));
				OutputStream outputStream = FileUtils.openOutputStream(outputFile);
				InputStream xslStream = XMLLunaticDataToJSON.class.getClassLoader()
						.getResourceAsStream(Constants.DATA_TRANSFORMATION_JSON_2_XML)
		) {
			saxonService.transformWithSimpleXSLSheet(inputStream,outputStream, xslStream);
		}catch(Exception e) {
			throw new ConversionException("Error when converting json to xml", e);
		}
		return FileUtils.readFileToString(outputFile, StandardCharsets.UTF_8);
	}
	
	private String wrapJsonWithXml(String json) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Data>"+preProcessJson2XML(json)+"</Data>";
	}

	/**
	 * Wraps the content of a JSON InputStream into a valid XML structure.
	 *
	 * @param jsonInputStream the original JSON stream
	 * @return a new InputStream containing the wrapped XML
	 * @throws IOException if reading the stream fails
	 */
	private InputStream wrapJsonStreamWithXml(InputStream jsonInputStream) throws IOException {
		String json = new String(jsonInputStream.readAllBytes(), StandardCharsets.UTF_8);
		String xml = wrapJsonWithXml(json);
		return new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
	}
	
	private String preProcessJson2XML(String json) {
		return json.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
