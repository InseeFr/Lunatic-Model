package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @JsonProperty(required = true)
    protected LabelType control;
    @JsonProperty(required = true)
    protected LabelType errorMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies;
    protected String id;
    protected ControlTypeOfControlEnum typeOfControl;
    protected ControlCriticityEnum criticality;

    public ControlType() {
        this.bindingDependencies = new ArrayList<>();
    }
}
