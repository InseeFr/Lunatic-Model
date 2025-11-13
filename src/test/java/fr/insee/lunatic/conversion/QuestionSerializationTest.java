package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import fr.insee.lunatic.utils.TestUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test if the serialization/deserialization of the question component works.
 * See response component (Input, InputNumber, Table etc.) test classes for specific tests.
 */
class QuestionSerializationTest {

    private final JsonSerializer serializer = new JsonSerializer();
    private final JsonDeserializer deserializer = new JsonDeserializer();

    @Test
    void serializeQuestion() throws SerializationException, IOException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        //
        Question question = new Question();
        question.setId("question-id");
        question.setPage("1");
        //
        question.setLabel(new LabelType());
        question.getLabel().setValue("\"Question label\"");
        question.getLabel().setType(LabelTypeEnum.VTL_MD);
        //
        question.setDescription(new LabelType());
        question.getDescription().setValue("\"Question description.\"");
        question.getDescription().setType(LabelTypeEnum.VTL_MD);
        //
        DeclarationType beforeQuestionDeclaration = new DeclarationType();
        beforeQuestionDeclaration.setId("declaration-1-id");
        beforeQuestionDeclaration.setDeclarationType(DeclarationTypeEnum.HELP);
        beforeQuestionDeclaration.setPosition(DeclarationPositionEnum.BEFORE_QUESTION_TEXT);
        beforeQuestionDeclaration.setLabel(new LabelType());
        beforeQuestionDeclaration.getLabel().setValue("\"Before question declaration.\"");
        beforeQuestionDeclaration.getLabel().setType(LabelTypeEnum.VTL_MD);
        question.getDeclarations().add(beforeQuestionDeclaration);
        //
        DeclarationType afterQuestionDeclaration = new DeclarationType();
        afterQuestionDeclaration.setId("declaration-2-id");
        afterQuestionDeclaration.setDeclarationType(DeclarationTypeEnum.HELP);
        afterQuestionDeclaration.setPosition(DeclarationPositionEnum.AFTER_QUESTION_TEXT);
        afterQuestionDeclaration.setLabel(new LabelType());
        afterQuestionDeclaration.getLabel().setValue("\"After question declaration.\"");
        afterQuestionDeclaration.getLabel().setType(LabelTypeEnum.VTL_MD);
        question.getDeclarations().add(afterQuestionDeclaration);
        //
        Input input = new Input();
        input.setId("input-id");
        input.setPage("1");
        input.setLabel(new LabelType());
        input.getLabel().setValue("\"Input component label\"");
        input.getLabel().setType(LabelTypeEnum.VTL_MD);
        input.setDescription(new LabelType());
        input.getDescription().setValue("\"Input component description.\"");
        input.getDescription().setType(LabelTypeEnum.VTL_MD);
        input.setMaxLength(BigInteger.valueOf(249));
        input.setResponse(new ResponseType());
        input.getResponse().setName("FOO");
        question.addComponent(input);
        //
        questionnaire.getComponents().add(question);

        //
        String result = serializer.serialize(questionnaire);

        //
        String expectedJson = TestUtils.readResourceFile("input-question.json");
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeQuestion() throws IOException, SerializationException {
        //
        String jsonQuestionnaire = TestUtils.readResourceFile("input-question.json");
        //
        Questionnaire questionnaire = deserializer.deserialize(
                new ByteArrayInputStream(jsonQuestionnaire.getBytes()));
        //
        assertNotNull(questionnaire);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void serializeQuestion_mandatory(Boolean mandatory) throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        Question question = new Question();
        question.setId("question-id");
        question.setMandatory(mandatory);
        questionnaire.getComponents().add(question);
        //
        String result = serializer.serialize(questionnaire);
        //
        String expected = String.format("""
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "question-id",
                      "componentType": "Question",
                      "isMandatory": %s,
                      "components": []
                    }
                  ]
                }
                """, mandatory);
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @Test
    void serializeQuestion_withoutMandatory() throws SerializationException, JSONException {
        //
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        Question question = new Question();
        question.setId("question-id");
        questionnaire.getComponents().add(question);
        //
        String result = serializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "question-id",
                      "componentType": "Question",
                      "components": []
                    }
                  ]
                }
                """;
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void deserializeQuestion_mandatory(Boolean mandatory) throws SerializationException {
        //
        String json = String.format("""
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "question-id",
                      "componentType": "Question",
                      "isMandatory": %s,
                      "components": []
                    }
                  ]
                }
                """, mandatory);
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(json.getBytes()));
        //
        Question question = assertInstanceOf(Question.class, questionnaire.getComponents().getFirst());
        assertEquals(mandatory, question.getMandatory());
    }

    @Test
    void deserializeQuestion_withoutMandatory() throws SerializationException {
        //
        String json = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "question-id",
                      "componentType": "Question",
                      "components": []
                    }
                  ]
                }
                """;
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(json.getBytes()));
        //
        Question question = assertInstanceOf(Question.class, questionnaire.getComponents().getFirst());
        assertNull(question.getMandatory());
    }

}
