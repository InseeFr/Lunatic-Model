package fr.insee.lunatic.conversion;

import fr.insee.lunatic.exception.SerializationException;
import fr.insee.lunatic.model.flat.*;
import org.json.JSONException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ComponentMandatoryTest {

    private final JsonSerializer serializer = new JsonSerializer();
    private final JsonDeserializer deserializer = new JsonDeserializer();

    /** Method designed to reduce redundant test code here. Wraps the component in a questionnaire with minimal
     * properties. */
    private static Questionnaire wrapInQuestionnaire(ComponentType component) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId("questionnaire-id");
        component.setId("component-id");
        questionnaire.getComponents().add(component);
        return questionnaire;
    }

    /** Arguments for serialization tests. */
    private static Stream<Arguments> questionnairesWithMandatoryComponent() {
        return Stream.of(
                Arguments.of(wrapInQuestionnaire(new Input()), "Input"),
                Arguments.of(wrapInQuestionnaire(new Textarea()), "Textarea"),
                Arguments.of(wrapInQuestionnaire(new InputNumber()), "InputNumber"),
                Arguments.of(wrapInQuestionnaire(new CheckboxBoolean()), "CheckboxBoolean"),
                Arguments.of(wrapInQuestionnaire(new Radio()), "Radio"),
                Arguments.of(wrapInQuestionnaire(new Dropdown()), "Dropdown"),
                Arguments.of(wrapInQuestionnaire(new CheckboxOne()), "CheckboxOne"),
                Arguments.of(wrapInQuestionnaire(new CheckboxGroup()), "CheckboxGroup")
        );
    }

    /** Arguments for deserialization tests. */
    private static Stream<Arguments> mandatoryComponentNames() {
        // re-use same component type names as above arguments.
        return questionnairesWithMandatoryComponent()
                .map(arguments -> Arguments.of(arguments.get()[1]));
    }

    @ParameterizedTest
    @MethodSource("questionnairesWithMandatoryComponent")
    void serializeWithoutMandatory(Questionnaire questionnaire, String componentTypeName)
            throws SerializationException, JSONException {
        //
        String result = serializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "component-id",
                      "componentType": "%s"
                    }
                  ]
                }""".formatted(componentTypeName);
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT_ORDER);
    }

    @ParameterizedTest
    @MethodSource("questionnairesWithMandatoryComponent")
    void serializeWithMandatoryTrue(Questionnaire questionnaire, String componentTypeName)
            throws SerializationException, JSONException {
        //
        ComponentMandatory component = assertInstanceOf(ComponentMandatory.class,
                questionnaire.getComponents().getFirst());
        component.setMandatory(true);
        //
        String result = serializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "component-id",
                      "componentType": "%s",
                      "isMandatory": true
                    }
                  ]
                }""".formatted(componentTypeName);
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT_ORDER);
    }

    @ParameterizedTest
    @MethodSource("questionnairesWithMandatoryComponent")
    void serializeWithMandatoryFalse(Questionnaire questionnaire, String componentTypeName)
            throws SerializationException, JSONException {
        //
        ComponentMandatory component = assertInstanceOf(ComponentMandatory.class,
                questionnaire.getComponents().getFirst());
        component.setMandatory(false);
        //
        String result = serializer.serialize(questionnaire);
        //
        String expected = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "component-id",
                      "componentType": "%s",
                      "isMandatory": false
                    }
                  ]
                }""".formatted(componentTypeName);
        JSONAssert.assertEquals(expected, result, JSONCompareMode.STRICT_ORDER);
    }

    @ParameterizedTest
    @MethodSource("mandatoryComponentNames")
    void deserializeWithoutMandatory(String componentTypeName)
            throws SerializationException {
        //
        String jsonInput = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "component-id",
                      "componentType": "%s"
                    }
                  ]
                }""".formatted(componentTypeName);
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        ComponentMandatory component = assertInstanceOf(ComponentMandatory.class, questionnaire.getComponents().getFirst());
        assertNull(component.getMandatory());
    }

    @ParameterizedTest
    @MethodSource("mandatoryComponentNames")
    void deserializeWithMandatoryTrue(String componentTypeName)
            throws SerializationException {
        //
        String jsonInput = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "component-id",
                      "componentType": "%s",
                      "isMandatory": true
                    }
                  ]
                }""".formatted(componentTypeName);
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        ComponentMandatory component = assertInstanceOf(ComponentMandatory.class, questionnaire.getComponents().getFirst());
        assertTrue(component.getMandatory());
    }

    @ParameterizedTest
    @MethodSource("mandatoryComponentNames")
    void deserializeWithMandatoryFalse(String componentTypeName)
            throws SerializationException {
        //
        String jsonInput = """
                {
                  "id": "questionnaire-id",
                  "componentType": "Questionnaire",
                  "components": [
                    {
                      "id": "component-id",
                      "componentType": "%s",
                      "isMandatory": false
                    }
                  ]
                }""".formatted(componentTypeName);
        //
        Questionnaire questionnaire = deserializer.deserialize(new ByteArrayInputStream(jsonInput.getBytes()));
        //
        ComponentMandatory component = assertInstanceOf(ComponentMandatory.class, questionnaire.getComponents().getFirst());
        assertFalse(component.getMandatory());
    }

}
