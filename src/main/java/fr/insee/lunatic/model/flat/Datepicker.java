package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Response component for dates.
 */
@Getter
@Setter
public class Datepicker extends ComponentType implements ComponentSimpleResponseType, ComponentMandatory {

    public Datepicker() {
        super(ComponentTypeName.DATEPICKER);
    }

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    /** Date format. Value must be "YYYY-MM-DD", "YYYY-MM" or "YYYY".
     * This property should be refactored from String to an enum. */
    @JsonProperty(required = true)
    protected String dateFormat;

    @JsonProperty(required = true)
    protected ResponseType response;

    /** Minimum value allowed for the response. */
    protected String min;

    /** Maximum value allowed for the response. */
    protected String max;
}
