package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "id",
        "label",
        "response",
        "detail"
})
@Getter
@Setter
public class ResponsesCheckboxGroup {

    @JsonProperty(required = true)
    protected LabelType label;

    @JsonProperty(required = true)
    protected ResponseType response;

    protected DetailResponse detail;

    @JsonProperty(required = true)
    protected String id;
}
