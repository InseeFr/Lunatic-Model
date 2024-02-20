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
        "lines",
        "header",
        "body",
        "options",
        "dateFormat",
        "format",
        "unit",
        "response",
        "responses"
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
        @JsonSubTypes.Type(value = FilterDescription.class, name = "FilterDescription"),
        //@JsonSubTypes.Type(value = Suggester.class, name = "Suggester"), TODO: create class for the suggester component
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
    protected String storeName;
    @JsonInclude(Include.NON_EMPTY)
    protected List<String> bindingDependencies;
    @JsonProperty(required = true)
    protected String id;
    protected ComponentTypeEnum componentType;
    protected Boolean mandatory;
    protected String page;

    protected ComponentType() {
        bindingDependencies = new ArrayList<>();
        controls = new ArrayList<>();
        declarations = new ArrayList<>();
    }
}
