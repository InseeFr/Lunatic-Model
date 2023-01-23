package fr.insee.lunatic.conversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class JSONSymLinksCleaner {

    private static final Logger logger = LoggerFactory.getLogger(JSONSymLinksCleaner.class);

    /**
     * Given a Lunatic JSON flat questionnaire, replace the source/target fields by key/values in "symLinks" attribute
     * in "PairwiseLinks" components to be compliant with the Lunatic JS library.
     * Warning: no validation is done on the string input. */
    public String clean(String stringFlatQuestionnaire) throws IOException {

        if (stringFlatQuestionnaire == null) {
            logger.warn("null string given in JSON SymLinks cleaner.");
            return null;
        }

        // Read the json string into a JsonObject
        try (JsonReader jsonReader = Json.createReader(
                new ByteArrayInputStream(stringFlatQuestionnaire.getBytes(StandardCharsets.UTF_8)))) {
            JsonObject jsonQuestionnaire = jsonReader.readObject();

            // We will copy the entire input json object, except the "symLinks" attribute in PairwiseLinks components
            JsonObjectBuilder jsonQuestionnaireBuilder = Json.createObjectBuilder();
            editQuestionnaire(jsonQuestionnaire, jsonQuestionnaireBuilder);

            OutputStream outputStream = new ByteArrayOutputStream();

            JsonWriter jsonWriter = Json.createWriter(outputStream);
            jsonWriter.writeObject(jsonQuestionnaireBuilder.build());

            String result = outputStream.toString();
            outputStream.close();

            return result;
        }

    }

    private static void editQuestionnaire(JsonObject jsonQuestionnaire, JsonObjectBuilder jsonQuestionnaireBuilder) {
        jsonQuestionnaire.forEach((key, jsonValue) -> {
            if (! "components".equals(key)) {
                jsonQuestionnaireBuilder.add(key, jsonValue);
            } else {
                editComponents(jsonQuestionnaireBuilder, (JsonArray) jsonValue);
            }
        });
    }

    private static void editComponents(JsonObjectBuilder jsonQuestionnaireBuilder, JsonArray jsonComponents) {
        JsonArrayBuilder jsonComponentsBuilder = Json.createArrayBuilder();
        for (JsonValue jsonValue1 : jsonComponents) {
            JsonObject jsonComponent = (JsonObject) jsonValue1;
            if (! "PairwiseLinks".equals(jsonComponent.getString("componentType"))) {
                jsonComponentsBuilder.add(jsonValue1);
            } else {
                editPairwiseLinks(jsonComponentsBuilder, (JsonObject) jsonValue1);
            }
        }
        jsonQuestionnaireBuilder.add("components", jsonComponentsBuilder.build());
    }

    private static void editPairwiseLinks(JsonArrayBuilder jsonComponentsBuilder, JsonObject jsonPairwiseLinks) {
        JsonObjectBuilder jsonPairwiseBuilder = Json.createObjectBuilder();
        jsonPairwiseLinks.forEach((key2, jsonValue2) -> {
            if (! "symLinks".equals(key2)) {
                jsonPairwiseBuilder.add(key2, jsonValue2);
            } else {
                editSymLinks(jsonPairwiseBuilder, (JsonObject) jsonValue2);
            }
        });
        jsonComponentsBuilder.add(jsonPairwiseBuilder.build());
    }

    private static void editSymLinks(JsonObjectBuilder jsonPairwiseBuilder, JsonObject jsonLINKS) {
        JsonObjectBuilder jsonSymLinksBuilder = Json.createObjectBuilder();
        JsonObjectBuilder jsonLINKSBuilder = Json.createObjectBuilder();
        jsonLINKS.getJsonObject("LINKS")
                .getJsonArray("LINK").forEach(jsonValue3 -> {
                    JsonObject jsonSourceTarget = (JsonObject) jsonValue3;
                    JsonString sourceKey = (JsonString) jsonSourceTarget.get("source");
                    JsonString targetKey = (JsonString) jsonSourceTarget.get("target");
                    // target field is not mandatory and can be null
                    // json converted from xml can contain "null" string values
                    if (targetKey != null && !"null".equals(targetKey.getString())) {
                        jsonLINKSBuilder.add(sourceKey.getString(), targetKey);
                    } else {
                        jsonLINKSBuilder.addNull(sourceKey.getString());
                    }
                });
        jsonSymLinksBuilder.add("LINKS", jsonLINKSBuilder.build());
        jsonPairwiseBuilder.add("symLinks", jsonSymLinksBuilder.build());
    }

}
