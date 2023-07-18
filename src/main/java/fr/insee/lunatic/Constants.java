package fr.insee.lunatic;

import java.io.IOException;
import java.util.Properties;

public class Constants {

	private Constants() {

	}

	public static final String LUNATIC_MODEL_VERSION = getVersion();

	public static String getVersion(){
		String version="";
		try {
			Properties properties = new Properties();
			properties.load(Constants.class.getResourceAsStream("/lunatic-model.properties"));
			version = properties.getProperty("version");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return version;
	}
}
