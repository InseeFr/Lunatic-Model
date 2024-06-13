package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

// TODO: this object class should be renamed to "Option" (singular), the current plural name is misleading.

/**
 * Option / modality of a unique choice component (dropdown, radio, checkbox one).
 */
@JsonPropertyOrder({
        "value",
        "label"
})
@Getter
@Setter
public class Options {

    /** Response value associated to the modality. */
    @JsonProperty(required = true)
    protected String value;

    /** Displayed label of the modality. */
    @JsonProperty(required = true)
    protected LabelType label;

    /** {@link DetailResponse} */
    protected DetailResponse detail;

}
