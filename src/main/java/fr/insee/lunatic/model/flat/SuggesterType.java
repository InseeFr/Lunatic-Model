package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Object that contains configuration options for suggester components used in the questionnaire.
 * This object belongs in the "suggesters" list at questionnaire level.
 */
@JsonPropertyOrder({
        "name",
        "fields",
        "meloto",
        "max",
        "stopWords",
        "order",
        "queryParser",
        "url",
        "version"
})
@Getter
@Setter
public class SuggesterType {

    @JsonProperty(required = true)
    protected String name;
    @JsonProperty(required = true)
    protected List<SuggesterField> fields;
    protected Boolean meloto;
    protected BigInteger max;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> stopWords;
    protected SuggesterOrder order;
    @JsonProperty(required = true)
    protected SuggesterQueryParser queryParser;
    protected String url;
    @JsonProperty(required = true)
    protected String version;

    public SuggesterType() {
        this.stopWords = new ArrayList<>();
        this.fields = new ArrayList<>();
    }
}
