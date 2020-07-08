package fr.insee.lunatic.conversion.data;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
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
public class XMLLunaticToXMLEmptyData {

	private static XslTransformation saxonService = new XslTransformation();

	private static final Logger logger = LoggerFactory.getLogger(XMLLunaticToXMLEmptyData.class);


	public File transform(File input) throws Exception {

		File outputFile = Files.createTempFile("empty-data", ".xml").toFile();

		logger.debug("Output folder : " + outputFile.getAbsolutePath());

		InputStream inputStream = FileUtils.openInputStream(input);
		OutputStream outputStream = FileUtils.openOutputStream(outputFile);

		InputStream XSL = XMLLunaticToXMLEmptyData.class.getClassLoader()
				.getResourceAsStream(Constants.EMPTY_DATA_GENERATION);
		try {
			saxonService.transformXMLLunaticDataToJSON(inputStream,outputStream, XSL);
		}catch(Exception e) {
			String errorMessage = "An error was occured during the empty data transformation. "+e.getMessage();
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}

		inputStream.close();
		outputStream.close();
		XSL.close();

		return outputFile;
	}
}
