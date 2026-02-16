package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * Filter applied to dynamic options (for QCU components).
 */
@JsonPropertyOrder({"type", "value", "shapeFrom"})
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionFilter {

    /** Type of the filter (e.g., "VTL"). */
    @JsonProperty(required = true)
    private String type;

    /** Filter expression to apply on the dynamic options. */
    @JsonProperty(required = true)
    private String value;

    /** Variable that defines the shape of the options (optionSource). */
    @JsonProperty(required = true)
    private String shapeFrom;

    public OptionFilter() {
    }

    public OptionFilter(String type, String value, String shapeFrom) {
        this.type = type;
        this.value = value;
        this.shapeFrom = shapeFrom;
    }
}