package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.LabelTypeEnum;
import fr.insee.lunatic.model.flat.Questionnaire;
import fr.insee.lunatic.model.flat.articulation.Articulation;
import fr.insee.lunatic.model.flat.articulation.ArticulationItem;
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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArticulationSerializationTest {

    @Test
    void serializeArticulation() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        Articulation articulation = new Articulation();
        articulation.setSource("id-roundabout");
        ArticulationItem prenomItem = new ArticulationItem();
        prenomItem.setLabel("Prénom");
        prenomItem.setType(LabelTypeEnum.VTL);
        prenomItem.setValue("PRENOMS");
        ArticulationItem sexeItem = new ArticulationItem();
        sexeItem.setLabel("Sexe");
        sexeItem.setType(LabelTypeEnum.VTL);
        sexeItem.setValue("if SEXE = \"H\" then \"Homme\" else \"Femme\"");
        articulation.getItems().addAll(List.of(prenomItem, sexeItem));
        questionnaire.setArticulation(articulation);
        //
        String result = new JsonSerializer().serialize(questionnaire);
        //
        String expected = """
                {
                   "componentType": "Questionnaire",
                   "articulation": {
                     "source": "id-roundabout",
                     "items": [
                       {
                         "value": "PRENOMS",
                         "type": "VTL",
                         "label": "Prénom"
                       },
                       {
                         "value": "if SEXE = \\"H\\" then \\"Homme\\" else \\"Femme\\"",
                         "type": "VTL",
                         "label": "Sexe"
                       }
                     ]
                   }
                 }""";
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeArticulation() throws SerializationException {
        //
        String jsonInput = """
                {
                   "componentType": "Questionnaire",
                   "articulation": {
                     "source": "id-roundabout",
                     "items": [
                       {
                         "value": "PRENOMS",
                         "type": "VTL",
                         "label": "Prénom"
                       },
                       {
                         "value": "if SEXE = \\"H\\" then \\"Homme\\" else \\"Femme\\"",
                         "type": "VTL",
                         "label": "Sexe"
                       }
                     ]
                   }
                 }""";
        //
        Questionnaire questionnaire = new JsonDeserializer().deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        assertEquals(2, questionnaire.getArticulation().getItems().size());
        assertEquals("id-roundabout", questionnaire.getArticulation().getSource());

        assertEquals("Sexe", questionnaire.getArticulation().getItems().getLast().getLabel());
        assertEquals("if SEXE = \"H\" then \"Homme\" else \"Femme\"", questionnaire.getArticulation().getItems().getLast().getValue());
        assertEquals(LabelTypeEnum.VTL, questionnaire.getArticulation().getItems().getLast().getType());
    }

}
