package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Input response component to collect a single line of text.
 */
@Getter
@Setter
public class Input extends ComponentType implements ComponentSimpleResponseType {

    public Input() {
        super();
        this.componentType = ComponentTypeEnum.INPUT;
    }

    /** Regular expression that the input's content must match. (Unused yet in Lunatic.) */
    protected String format;

    /** {@link ResponseType} */
    @JsonProperty(required = true)
    protected ResponseType response;

    /** Maximal length of the text that can be inputted. */
    protected BigInteger maxLength;

}
