package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Question component introduced in Lunatic v3.
 * Encapsulates response components such as Input, InputNumber, Table etc.
 */
@Getter
@Setter
public class Question extends ComponentType {

    public Question() {
        this.componentType = ComponentTypeEnum.QUESTION;
        this.components = new ArrayList<>();
    }

    /** Components that are encapsulated in the question component.
     * Preferably use the <code>addComponent</code> and <code>addComponents</code> methods rather than that
     * <code>get().add(...)</code> or <code>get().addAll(...)</code> to have controls on the type of components that
     * are valid for Lunatic. */
    private final List<ComponentType> components;

    /**
     * Adds the given component within the question.
     * A runtime exception is thrown if the component type is invalid for a question.
     * @param component A Lunatic response component (like Input, InputNumber, Table etc.).
     */
    public void addComponent(ComponentType component) {
        if (! isQuestionComponent(component))
            throw new IllegalArgumentException(
                    "Component of type " + component.getComponentType() + " cannot be inserted in a Question component.");
        this.components.add(component);
    }

    /**
     * Adds the given components within the question.
     * A runtime exception is thrown if one of the components' type is invalid for a question.
     * @param components A list of Lunatic response components (like Input, InputNumber, Table etc.).
     */
    public void addComponents(Collection<ComponentType> components) {
        components.forEach(this::addComponent);
    }

    /**
     * Method to test if the type of the given component is valid for a Question.
     * @param component A Lunatic component.
     * @return True if the type of the given component is valid for a Question.
     */
    public static boolean isQuestionComponent(ComponentType component) {
        if (component.getComponentType() == null)
            return false;
        return switch (component.getComponentType()) {
            case CHECKBOX_BOOLEAN, INPUT, TEXTAREA, INPUT_NUMBER, DATEPICKER, DURATION,
                    CHECKBOX_ONE, RADIO, DROPDOWN, SUGGESTER, TEXT,
                    CHECKBOX_GROUP, TABLE, ROSTER_FOR_LOOP, PAIRWISE_LINKS -> true;
            case QUESTIONNAIRE, SEQUENCE, SUBSEQUENCE, QUESTION, ACCORDION, LOOP -> false;
        };
    }

}
