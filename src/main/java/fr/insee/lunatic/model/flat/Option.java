package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * Option / modality of a unique choice component (dropdown, radio, checkbox one).
 */
@JsonPropertyOrder({
        "value",
        "label"
})
@Getter
@Setter
public class Option {

    /** Response value associated to the modality. */
    @JsonProperty(required = true)
    protected String value;

    /** Displayed label of the modality. */
    @JsonProperty(required = true)
    protected LabelType label;

    /** Filter that determines if the option is displayed or not.
     * If null, the option is displayed. */
    protected ConditionFilterType conditionFilter;

    /** {@link DetailResponse} */
    protected DetailResponse detail;

}
