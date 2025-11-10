package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Response component to collect a block of text.
 */
@Getter
@Setter
public class Textarea extends ComponentType implements ComponentSimpleResponseType, ComponentMandatory {

    public Textarea() {
        super(ComponentTypeEnum.TEXTAREA);
    }

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    @JsonProperty(required = true)
    protected ResponseType response;

    /** Maximum length of the text response. */
    protected BigInteger maxLength;

}
