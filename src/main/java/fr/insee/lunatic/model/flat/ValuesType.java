package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "previous",
        "collected",
        "forced",
        "edited",
        "inputed"
})
@Getter
@Setter
public class ValuesType {

    @JsonProperty(value = "PREVIOUS")
    protected String previous;
    @JsonProperty(value = "COLLECTED")
    protected String collected;
    @JsonProperty(value = "FORCED")
    protected String forced;
    @JsonProperty(value = "EDITED")
    protected String edited;
    @JsonProperty(value = "INPUTED")
    protected String inputed;
}
