package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Control that can be added to response components.
 */
@JsonPropertyOrder({
        "id",
        "typeOfControl",
        "criticality",
        "control",
        "errorMessage",
        "bindingDependencies"
})
@Getter
@Setter
public class ControlType {

    /** Expression that determines when the control is triggered. */
    @JsonProperty(required = true)
    protected LabelType control;

    /** Message that is displayed to the respondent when the control is triggered. */
    @JsonProperty(required = true)
    protected LabelType errorMessage;

    /** Name of the collected and/or external variables that are required to evaluate the control's expression. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies;

    /** Identifier of the control object. */
    protected String id;

    /** {@link ControlContextType} */
    protected ControlContextType type;

    /** {@link ControlTypeEnum} */
    protected ControlTypeEnum typeOfControl;

    /** {@link ControlCriticalityEnum} */
    protected ControlCriticalityEnum criticality;

    public ControlType() {
        this.type = ControlContextType.SIMPLE;
        this.bindingDependencies = new ArrayList<>();
    }
}
