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
        PairwiseLinks.SourceVariables sourceVariables = new PairwiseLinks.SourceVariables();
        sourceVariables.setName("FIRST_NAME_VAR");
        sourceVariables.setGender("GENDER_VAR");
        pairwiseLinks.setSourceVariables(sourceVariables);
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
