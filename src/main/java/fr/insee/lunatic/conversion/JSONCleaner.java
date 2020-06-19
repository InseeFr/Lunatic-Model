package fr.insee.lunatic.conversion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
public class JSONCleaner {

	private static XslTransformation saxonService = new XslTransformation();

	private static final Logger logger = LoggerFactory.getLogger(JSONCleaner.class);

	
	public String clean(String jsonString) throws Exception {

		if ((jsonString == null) || (jsonString.length() == 0))
			return null;
		InputStream json = new ByteArrayInputStream(wrapJsonWithXml(jsonString).getBytes("UTF-8"));

		return this.generate(json);
	}
	
	public String generate(InputStream isFinalInput) throws Exception {
		OutputStream osOutputFile = generateOS(isFinalInput);
		String res = osOutputFile.toString();
		osOutputFile.close();
		return res;
	}

	public OutputStream generateOS(InputStream isFinalInput) throws Exception {
		logger.info("Clean json output : START");

		InputStream xslSheet = JSONCleaner.class.getClassLoader()
				.getResourceAsStream(Constants.TRANSFORMATION_JSON_2_JSON_CLEANED);
		
		OutputStream osOutputFile = new ByteArrayOutputStream();
		saxonService.transformJSONLunaticToJSONLunaticClean(isFinalInput, osOutputFile, xslSheet);

		xslSheet.close();

		return osOutputFile;
	}
	
	public String wrapJsonWithXml(String json) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Questionnaire>"+preProcessJson2XML(json)+"</Questionnaire>";
	}
	
	public String preProcessJson2XML(String json) {
		return json.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
