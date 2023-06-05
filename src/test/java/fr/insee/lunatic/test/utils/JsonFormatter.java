package fr.insee.lunatic.test.utils;

/**
 * Utils class to facilitate json comparison in tests.
 */
public class JsonFormatter {

    /**
     * Compress the json string by removing line breaks and spaces.
     * Warning: this method also removes spaces inside string content.
     * @param jsonString Json string.
     * @return Compressed json string.
     */
    public static String compress(String jsonString) {
        return jsonString.replaceAll("\\s+","").replaceAll("\\n+","");
    }

}
