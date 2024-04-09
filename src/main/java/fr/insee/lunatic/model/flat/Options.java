package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "value",
        "label"
})
@Getter
@Setter
public class Options {

    @JsonProperty(required = true)
    protected String value;
    @JsonProperty(required = true)
    protected LabelType label;

    protected DetailResponse detail;
}
