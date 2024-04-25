package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import fr.insee.lunatic.utils.TestUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Class to test if the serialization/deserialization of the question component works.
 * See response component (Input, InputNumber, Table etc.) test classes for specific tests.
 */
class QuestionSerializationTest {

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
        input.setComponentType(ComponentTypeEnum.INPUT);
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
        JsonSerializer jsonSerializer = new JsonSerializer();
        String result = jsonSerializer.serialize(questionnaire);

        //
        String expectedJson = TestUtils.readResourceFile("input-question.json");
        JSONAssert.assertEquals(expectedJson, result, JSONCompareMode.STRICT);
    }

    @Test
    void deserializeQuestion() throws IOException, SerializationException {
        //
        String jsonQuestionnaire = TestUtils.readResourceFile("input-question.json");
        //
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        Questionnaire questionnaire = jsonDeserializer.deserialize(
                new ByteArrayInputStream(jsonQuestionnaire.getBytes()));
        //
        assertNotNull(questionnaire);
    }

}
