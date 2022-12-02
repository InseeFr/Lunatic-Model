package fr.insee.lunatic.conversion;

import javax.json.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class JSONSymLinksCleaner {

    /**
     * Given a Lunatic JSON flat questionnaire, replace the source/target fields by key/values in "symLinks" attribute
     * in "PairwiseLinks" components to be compliant with the Lunatic JS library.
    public String clean(String stringFlatQuestionnaire) throws IOException {

        // Read the json string into a JsonObject
        JsonReader jsonReader = Json.createReader(
                new ByteArrayInputStream(stringFlatQuestionnaire.getBytes(StandardCharsets.UTF_8)));
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

    private static void editQuestionnaire(JsonObject jsonQuestionnaire, JsonObjectBuilder jsonQuestionnaireBuilder) {
        jsonQuestionnaire.forEach((key, jsonValue) -> {
            if (!key.equals("components")) {
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
            if (!jsonComponent.getString("componentType").equals("PairwiseLinks")) {
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
            if (!key2.equals("symLinks")) {
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
                    String sourceKey = String.valueOf(jsonSourceTarget.get("source"));
                    if (jsonSourceTarget.get("target") != null) {
                        String targetKey = String.valueOf(jsonSourceTarget.get("target"));
                        jsonLINKSBuilder.add(sourceKey, targetKey);
                    } else {
                        jsonLINKSBuilder.addNull(sourceKey);
                    }
                });
        jsonSymLinksBuilder.add("LINKS", jsonLINKSBuilder.build());
        jsonPairwiseBuilder.add("symLinks", jsonSymLinksBuilder.build());
    }

}
