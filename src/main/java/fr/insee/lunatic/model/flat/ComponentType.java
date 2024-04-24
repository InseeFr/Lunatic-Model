package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
        "maxLength",
        "positioning",
        "min",
        "max",
        "decimals",
        "label",
        "declarations",
        "conditionFilter",
        "controls",
        "hierarchy",
        "missingResponse",
        "storeName",
        "bindingDependencies",
        "components",
        "lines",
        "header",
        "body",
        "options",
        "dateFormat",
        "format",
        "unit",
        "response",
        "responses",
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
        @JsonSubTypes.Type(value = RosterForLoop.class, name = "RosterForLoop"),
        @JsonSubTypes.Type(value = Loop.class, name = "Loop"),
        @JsonSubTypes.Type(value = Table.class, name = "Table"),
        @JsonSubTypes.Type(value = Input.class, name = "Input"),
        @JsonSubTypes.Type(value = PairwiseLinks.class, name = "PairwiseLinks"),
        @JsonSubTypes.Type(value = Datepicker.class, name = "Datepicker"),
        @JsonSubTypes.Type(value = CheckboxGroup.class, name = "CheckboxGroup"),
        @JsonSubTypes.Type(value = CheckboxOne.class, name = "CheckboxOne"),
        @JsonSubTypes.Type(value = CheckboxBoolean.class, name = "CheckboxBoolean"),
        @JsonSubTypes.Type(value = Dropdown.class, name = "Dropdown"),
        @JsonSubTypes.Type(value = Textarea.class, name = "Textarea"),
        @JsonSubTypes.Type(value = Suggester.class, name = "Suggester")
})
@Getter
@Setter
public abstract class ComponentType {

    protected LabelType label;
    @JsonInclude(Include.NON_EMPTY)
    protected List<DeclarationType> declarations;
    protected ConditionFilterType conditionFilter;
    @JsonInclude(Include.NON_EMPTY)
    protected List<ControlType> controls;
    protected Hierarchy hierarchy;
    protected ResponseType missingResponse;

    /** Collected or external variable names required to evaluate expressions used in the component properties.
     * @deprecated Binding dependencies at component level are not used anymore in Lunatic. */
    @JsonInclude(Include.NON_EMPTY)
    @Deprecated(since = "3.4.0")
    protected List<String> bindingDependencies = new ArrayList<>();

    @JsonProperty(required = true)
    protected String id;
    protected ComponentTypeEnum componentType;
    protected Boolean mandatory;
    protected String page;

    /** This property should be moved in the Suggester component.
     * Yet having this property defined here makes Eno suggester specific treatment easier.
     * To be moved in the Suggester class when the suggester specific treatment is removed in Eno. */
    protected String storeName;

    protected ComponentType() {
        controls = new ArrayList<>();
        declarations = new ArrayList<>();
    }
}
