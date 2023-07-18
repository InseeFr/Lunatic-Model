package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "type",
        "params"
})
@Getter
@Setter
public class SuggesterQueryParser {

    @JsonProperty(required = true)
    protected String type;
    protected SuggesterQueryParserParams params;
}
