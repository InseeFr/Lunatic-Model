package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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

    public ComponentType() {
        bindingDependencies = new ArrayList<>();
        controls = new ArrayList<>();
        declarations = new ArrayList<>();
    }
}
