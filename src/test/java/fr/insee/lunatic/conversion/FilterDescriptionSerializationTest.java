package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.FilterDescription;
import fr.insee.lunatic.model.flat.LabelType;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class FilterDescriptionSerializationTest {

    private final JsonSerializer serializer = new JsonSerializer();
    private final JsonDeserializer deserializer = new JsonDeserializer();

    private final String filterDescriptionJson = """
            {
              "id": "questionnaire-id",
              "componentType": "Questionnaire",
              "components": [
                {
                  "id": "filter-description-id",
                  "page": "1",
                  "componentType": "FilterDescription",
                  "label": {
                    "value": "Filter on some component.",
                    "type": "TXT"
                  }
                }
              ]
            }""";

    @Test
    @DisplayName("Serialize single filter description component.")
    void serializeFilterDescription_test1() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        FilterDescription filterDescription = new FilterDescription();
        filterDescription.setId("filter-description-id");
        filterDescription.setPage("1");
        filterDescription.setLabel(new LabelType());
        filterDescription.getLabel().setValue("Filter on some component.");
        filterDescription.getLabel().setType(LabelTypeEnum.TXT);
        questionnaire.getComponents().add(filterDescription);
        //
        String result = serializer.serialize(questionnaire);
        //
        JSONAssert.assertEquals(result, filterDescriptionJson, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeFilterDescription_test1() throws SerializationException {
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(
                filterDescriptionJson.getBytes()));
        //
        FilterDescription filterDescription = assertInstanceOf(FilterDescription.class,
                questionnaire.getComponents().getFirst());
        assertEquals("filter-description-id", filterDescription.getId());
        assertEquals("1", filterDescription.getPage());
        assertEquals("Filter on some component.", filterDescription.getLabel().getValue());
        assertEquals(LabelTypeEnum.TXT, filterDescription.getLabel().getType());
    }

}
