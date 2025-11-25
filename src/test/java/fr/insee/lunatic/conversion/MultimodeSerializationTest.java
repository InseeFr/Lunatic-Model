package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.multimode.Multimode;
import fr.insee.lunatic.model.flat.multimode.MultimodeLeaf;
import fr.insee.lunatic.model.flat.multimode.MultimodeQuestionnaire;
import fr.insee.lunatic.model.flat.multimode.MultimodeRule;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultimodeSerializationTest {

    @Test
    void serializeMultimode() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        Multimode multimode = new Multimode();

        MultimodeRule isMoved = new MultimodeRule();
        isMoved.setType(LabelTypeEnum.VTL);
        isMoved.setValue("true");

        MultimodeRule isSplit = new MultimodeRule();
        isSplit.setType(LabelTypeEnum.VTL);
        isSplit.setValue("true");

        Map<String, MultimodeRule> rules = new HashMap<>();
        rules.put("IS_MOVED", isMoved);
        rules.put("IS_SPLIT", isSplit);
        MultimodeQuestionnaire multimodeQuestionnaire = new MultimodeQuestionnaire();
        multimodeQuestionnaire.setRules(rules);
        MultimodeLeaf multimodeLeaf = new MultimodeLeaf();
        multimodeLeaf.setSource("id-roundabout");
        multimodeLeaf.setRules(rules);
        multimode.setQuestionnaire(multimodeQuestionnaire);
        multimode.setLeaf(multimodeLeaf);

        questionnaire.setMultimode(multimode);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        String expected = """
{
  "componentType": "Questionnaire",
  "multimode": {
    "questionnaire": {
      "rules": {
        "IS_MOVED": {
          "value": "true",
          "type": "VTL"
        },
        "IS_SPLIT": {
          "value": "true",
          "type": "VTL"
        }
      }
    },
    "leaf": {
      "rules": {
        "IS_MOVED": {
          "value": "true",
          "type": "VTL"
        },
        "IS_SPLIT": {
          "value": "true",
          "type": "VTL"
        }
      },
      "source": "id-roundabout"
    }
  }
}""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeMultimode() throws SerializationException {
        //
        String jsonInput = """
{
  "componentType": "Questionnaire",
  "multimode": {
    "questionnaire": {
      "rules": {
        "IS_MOVED": {
          "value": "true",
          "type": "VTL"
        },
        "IS_SPLIT": {
          "value": "true",
          "type": "VTL"
        }
      }
    },
    "leaf": {
      "rules": {
        "IS_MOVED": {
          "value": "true",
          "type": "VTL"
        },
        "IS_SPLIT": {
          "value": "true",
          "type": "VTL"
        }
      },
      "source": "id-roundabout"
    }
  }
}""";
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        assertEquals(2, questionnaire.getMultimode().getLeaf().getRules().size());
        assertEquals("id-roundabout", questionnaire.getMultimode().getLeaf().getSource());

        assertEquals(2, questionnaire.getMultimode().getQuestionnaire().getRules().size());
        assertEquals("true", questionnaire.getMultimode().getQuestionnaire().getRules().get("IS_MOVED").getValue());
        assertEquals(LabelTypeEnum.VTL, questionnaire.getMultimode().getQuestionnaire().getRules().get("IS_MOVED").getType());
    }

}
