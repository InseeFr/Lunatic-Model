package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Note: the ComponentType class has way too many properties
// Should probably be refactored with an intermediate level of subclasses,
// or (surely better): implement proper interfaces.

@JsonPropertyOrder({
        "id",
        "componentType",
        "modele",
        "enoCoreVersion",
        "lunaticModelVersion",
        "generatingDate",
        "missing",
        "pagination",
        "maxPage",
        "mandatory",
        "page",
        "goToPage",
        "label",
        "orientation",
        "maxLength",
        "min",
        "max",
        "decimals",
        "dateFormat",
        "format",
        "unit",
        "declarations",
        "conditionFilter",
        "controls",
        "hierarchy",
        "missingResponse",
        "storeName",
        "bindingDependencies",
        "lines",
        "iterations",
        "locked",
        "progressVariable",
        "header",
        "body",
        "options",
        "item",
        "items",
        "components",
        "response",
        "responses",
        "optionResponses",
        "suggesters",
        "variables",
        "cleaning",
        "missingBlock",
        "resizing"
})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "componentType",
        visible = true)
@JsonSubTypes({ // Annotation required for deserialization of components
        @JsonSubTypes.Type(value = Questionnaire.class, name = "Questionnaire"),
        @JsonSubTypes.Type(value = Sequence.class, name = "Sequence"),
        @JsonSubTypes.Type(value = Subsequence.class, name = "Subsequence"),
        @JsonSubTypes.Type(value = Question.class, name = "Question"),
        @JsonSubTypes.Type(value = RosterForLoop.class, name = "RosterForLoop"),
        @JsonSubTypes.Type(value = Loop.class, name = "Loop"),
        @JsonSubTypes.Type(value = Roundabout.class, name = "Roundabout"),
        @JsonSubTypes.Type(value = Table.class, name = "Table"),
        @JsonSubTypes.Type(value = Input.class, name = "Input"),
        @JsonSubTypes.Type(value = InputNumber.class, name = "InputNumber"),
        @JsonSubTypes.Type(value = PairwiseLinks.class, name = "PairwiseLinks"),
        @JsonSubTypes.Type(value = Datepicker.class, name = "Datepicker"),
        @JsonSubTypes.Type(value = Duration.class, name = "Duration"),
        @JsonSubTypes.Type(value = CheckboxGroup.class, name = "CheckboxGroup"),
        @JsonSubTypes.Type(value = CheckboxOne.class, name = "CheckboxOne"),
        @JsonSubTypes.Type(value = CheckboxBoolean.class, name = "CheckboxBoolean"),
        @JsonSubTypes.Type(value = Dropdown.class, name = "Dropdown"),
        @JsonSubTypes.Type(value = Radio.class, name = "Radio"),
        @JsonSubTypes.Type(value = Textarea.class, name = "Textarea"),
        @JsonSubTypes.Type(value = Suggester.class, name = "Suggester"),
        @JsonSubTypes.Type(value = Text.class, name = "Text"),
        @JsonSubTypes.Type(value = FilterDescription.class, name = "FilterDescription"),
        @JsonSubTypes.Type(value = Accordion.class, name = "Accordion"),
})
@Getter
@Setter
public abstract class ComponentType {

    /** Component identifier. */
    @JsonProperty(required = true)
    protected String id;

    /** Property that defines the type of component in the serialized object. */
    protected ComponentTypeEnum componentType;

    /**
     * @deprecated The "component type" is set in constructor.
     */
    @Deprecated(since = "5.10.1")
    public void setComponentType(ComponentTypeEnum componentTypeEnum) {
        this.componentType = componentTypeEnum;
    }

    /** Displayed label of the component. */
    protected LabelType label;

    /** Component description (concept introduced in Lunatic v3). */
    private LabelType description;

    /** Declarations to display with the component. */
    @JsonInclude(Include.NON_EMPTY)
    protected List<DeclarationType> declarations;

    /** Filter applied on the component. */
    protected ConditionFilterType conditionFilter;

    /** Controls applied on the component. */
    @JsonInclude(Include.NON_EMPTY)
    protected List<ControlType> controls;

    /** Object that indicates in which sequence/subsequence belongs the component.
     * @deprecated Unused by Lunatic anymore, to be removed. */
    @Deprecated(since = "5.1.0", forRemoval = true)
    protected Hierarchy hierarchy;

    protected ResponseType missingResponse;

    /** Collected or external variable names required to evaluate expressions used in the component properties.
     * @deprecated Binding dependencies at component level are not used anymore in Lunatic. */
    @JsonInclude(Include.NON_EMPTY)
    @Deprecated(since = "3.4.0", forRemoval = true)
    protected List<String> bindingDependencies = new ArrayList<>();

    /** Page number of the component: "1", "2", "3", etc.
     * For components that are in a loop, the page number becomes: "n.1", "n.2", "n.3", etc.
     * (where 'n' is the loop page number).
     * Components that share the same page number are displayed on the same page. */
    protected String page;

    /** {@link ComponentPosition} */
    protected ComponentPosition position;

    /** This property should be moved in the Suggester component.
     * Yet having this property defined here makes Eno suggester specific treatment easier.
     * To be moved in the Suggester class when the suggester specific treatment is removed in Eno. */
    protected String storeName;

    protected ComponentType(ComponentTypeEnum componentType) {
        this.componentType = componentType;
        controls = new ArrayList<>();
        declarations = new ArrayList<>();
    }
}
