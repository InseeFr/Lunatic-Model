package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.PairwiseLinks;
import fr.insee.lunatic.model.flat.Questionnaire;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class PairwiseLinksSerializationTest {

    @Test
    void serializePairwiseLinks() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        PairwiseLinks pairwiseLinks = new PairwiseLinks();
        pairwiseLinks.setId("foo-id");
        pairwiseLinks.setSourceVariables(new PairwiseLinks.SourceVariables("FIRST_NAME_VAR", "GENDER_VAR"));
        questionnaire.getComponents().add(pairwiseLinks);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        String expected = """
{
  "componentType": "Questionnaire",
  "components": [
    {
      "id": "foo-id",
      "componentType": "PairwiseLinks",
      "sourceVariables": {
        "name": "FIRST_NAME_VAR",
        "gender": "GENDER_VAR"
      },
      "components": []
    }
  ]
}
""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

}
