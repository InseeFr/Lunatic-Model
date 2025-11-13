package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class SuggesterSerializationTest {

    private final String jsonSuggesterWithOptions = """
            {
              "id": "questionnaire-id",
              "componentType": "Questionnaire",
              "components": [
                {
                  "id": "foo-id",
                  "componentType": "Suggester",
                  "storeName": "FOO_CODE_LIST",
                  "response": {
                    "name": "FOO"
                  },
                  "optionResponses": [
                    {
                      "name": "FOO_LABEL",
                      "attribute": "label"
                    },
                    {
                      "name": "FOO_PRICE",
                      "attribute": "price"
                    }
                  ]
                }
              ]
            }""";

    @Test
    void serializeSuggesterComponent() throws SerializationException, JSONException {
        //
        Suggester suggester = new Suggester();
        suggester.setId("foo-id");
        suggester.setStoreName("FOO_CODE_LIST");
        suggester.setResponse(new ResponseType());
        suggester.getResponse().setName("FOO");
        suggester.getOptionResponses().add(new Suggester.OptionResponse("FOO_LABEL", "label"));
        suggester.getOptionResponses().add(new Suggester.OptionResponse("FOO_PRICE", "price"));
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        questionnaire.getComponents().add(suggester);

        //
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);

        //
        JSONAssert.assertEquals(jsonSuggesterWithOptions, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeSuggesterComponent() throws SerializationException {
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(new ByteArrayInputStream(jsonSuggesterWithOptions.getBytes()));
        //
        Suggester suggester = assertInstanceOf(Suggester.class, questionnaire.getComponents().getFirst());
        assertEquals("foo-id", suggester.getId());
        assertEquals(ComponentTypeEnum.SUGGESTER, suggester.getComponentType());
        assertEquals("FOO_CODE_LIST", suggester.getStoreName());
        assertEquals("FOO", suggester.getResponse().getName());
        assertEquals(2, suggester.getOptionResponses().size());
        assertEquals("FOO_LABEL", suggester.getOptionResponses().get(0).name());
        assertEquals("FOO_PRICE", suggester.getOptionResponses().get(1).name());
        assertEquals("label", suggester.getOptionResponses().get(0).attribute());
        assertEquals("price", suggester.getOptionResponses().get(1).attribute());
    }

    private final String jsonSuggesterWithArbitrary = """
            {
              "componentType": "Questionnaire",
              "components": [
                {
                  "id": "suggester-id",
                  "componentType": "Suggester",
                  "storeName": "products",
                  "maxLength": 249,
                  "response": {
                    "name": "PRODUCT"
                  },
                  "arbitrary": {
                    "response": {
                      "name": "PRODUCT_OTHER"
                    },
                    "label": {
                      "value": "\\"My product is not in the list\\"",
                      "type": "VTL"
                    },
                    "inputLabel": {
                      "value": "\\"Please type your product name\\"",
                      "type": "VTL"
                    }
                  }
                }
              ]
            }
            """;

    @Test
    void serializeSuggesterWithArbitrary() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        Suggester suggester = new Suggester();
        suggester.setId("suggester-id");
        suggester.setStoreName("products");
        suggester.setMaxLength(BigInteger.valueOf(249));
        suggester.setResponse(new ResponseType());
        suggester.getResponse().setName("PRODUCT");
        ArbitraryType arbitrary = new ArbitraryType();
        arbitrary.setResponse(new ResponseType());
        arbitrary.getResponse().setName("PRODUCT_OTHER");
        arbitrary.setLabel(new LabelType());
        arbitrary.getLabel().setValue("\"My product is not in the list\"");
        arbitrary.getLabel().setType(LabelTypeEnum.VTL);
        arbitrary.setInputLabel(new LabelType());
        arbitrary.getInputLabel().setValue("\"Please type your product name\"");
        arbitrary.getInputLabel().setType(LabelTypeEnum.VTL);
        suggester.setArbitrary(arbitrary);
        questionnaire.getComponents().add(suggester);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        JSONAssert.assertEquals(jsonSuggesterWithArbitrary, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeSuggesterWithArbitrary() throws SerializationException {
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(
                new ByteArrayInputStream(jsonSuggesterWithArbitrary.getBytes()));
        //
        Suggester suggester = assertInstanceOf(Suggester.class, questionnaire.getComponents().getFirst());
        assertEquals("suggester-id", suggester.getId());
        assertEquals("products", suggester.getStoreName());
        assertEquals(BigInteger.valueOf(249), suggester.getMaxLength());
        assertEquals("PRODUCT", suggester.getResponse().getName());
        ArbitraryType arbitrary = suggester.getArbitrary();
        assertEquals("PRODUCT_OTHER", arbitrary.getResponse().getName());
        assertEquals("\"My product is not in the list\"", arbitrary.getLabel().getValue());
        assertEquals("\"Please type your product name\"", arbitrary.getInputLabel().getValue());
        assertEquals(LabelTypeEnum.VTL, arbitrary.getLabel().getType());
        assertEquals(LabelTypeEnum.VTL, arbitrary.getInputLabel().getType());
    }

}
