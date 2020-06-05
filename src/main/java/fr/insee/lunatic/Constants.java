package fr.insee.lunatic;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	private static final Logger logger = LoggerFactory.getLogger(Constants.class);


	private Constants() {

	}

	public static final String TEMP_FOLDER_PATH = System.getProperty("java.io.tmpdir")+"/"+"lunatic-test";
	public static final Path TEMP_FOLDER= getTempDir(TEMP_FOLDER_PATH);
	public static final String RESOURCES_FOLDER_PATH = "src/test/resources/examples";
	public static final String RESOURCES_FOLDER_DUMMY_PATH = "src/test/resources/dummy";
	public static final String RESOURCES_FOLDER_XMLF_2_JSONF_PATH = RESOURCES_FOLDER_PATH +"/xmlf-2-jsonf";
	public static final String RESOURCES_FOLDER_XMLH_2_XMLF_PATH = RESOURCES_FOLDER_PATH +"/xmlh-2-xmlf";
	public static final String RESOURCES_FOLDER_XMLH_2_JSONF_PATH = RESOURCES_FOLDER_PATH +"/xmlh-2-jsonf";
	public static final String RESOURCES_FOLDER_XMLH_2_JSONH_PATH = RESOURCES_FOLDER_PATH + "/xmlh-2-jsonh";

	public static final String XSD_FILE_PATH = "/src/main/resources/xsd/Questionnaire.xsd";
	public static final String TRANSFORMATION_XML_2_XMLF = "xslt/flattener.xsl";
	public static final String TRANSFORMATION_JSON_2_JSON_CLEANED = "xslt/json-cleaner.xsl";
	public static final String NAMESPACE_URI = "http://xml.insee.fr/schema/applis/lunatic";
	public static final String PREFIX = "Questionnaire";
	public static final String JAXB_SCHEMA_LOCATION = NAMESPACE_URI + " file:"+XSD_FILE_PATH;

	public static final String SCHEMAS_FOLDER_PATH = "/xsd";

	public static final URL MODEL_FLAT_XSD = Constants.class.getResource(SCHEMAS_FOLDER_PATH+"/LunaticModelFlat.xsd");
	public static final URL MODEL_HIERARCHICAL_XSD = Constants.class.getResource(SCHEMAS_FOLDER_PATH+"/LunaticModel.xsd");

	public static Path getTempDir(String pathFolder) {
		Path tempDirPath = null;
		File dir = new File(pathFolder);

		if(dir.exists()) {
			tempDirPath = Paths.get(pathFolder);
		}
		else {
			try {
				tempDirPath = Files.createDirectory(Paths.get(pathFolder));
			} catch(IOException e) {
				logger.debug("Temp directory fail to initialize");
				e.printStackTrace();
			}
		}
		return tempDirPath;
	}
}
