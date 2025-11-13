package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Response component to collect numeric data.
 */
@Getter @Setter
public class InputNumber extends ComponentType implements ComponentSimpleResponseType, ComponentMandatory {

    public InputNumber() {
        super(ComponentTypeName.INPUT_NUMBER);
    }

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    /** Minimum value allowed. */
    protected Double min;

    /** Maximum value allowed. */
    protected Double max;

    /** Number of decimals allowed. null is equivalent to zero. */
    protected BigInteger decimals;

    /** Unit of the numeric response. */
    protected LabelType unit;

    /**
     * @deprecated Use <code>getUnit()</code> method.
     */
    @JsonIgnore
    @Deprecated(since = "6.0.0")
    public LabelType getUnitLabel() {
        return unit;
    }

    /** {@link ResponseType} */
    @JsonProperty(required = true)
    protected ResponseType response;

}
