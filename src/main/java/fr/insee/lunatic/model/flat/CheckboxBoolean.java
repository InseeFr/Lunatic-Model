package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckboxBoolean extends ComponentType implements ComponentSimpleResponseType, ComponentMandatory {

    public CheckboxBoolean() {
        super(ComponentTypeName.CHECKBOX_BOOLEAN);
    }

    @JsonProperty(required = true)
    protected ResponseType response;

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;
}
