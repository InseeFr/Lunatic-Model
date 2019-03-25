package fr.insee.lunatic.conversion;

import java.io.InputStream;


public class UtilFile {

	public static InputStream getInputStreamFromPath(String path) {
		try {
			return UtilFile.class.getResourceAsStream(path);
		} catch (Exception e) {
			return null;
		}
	}
}
