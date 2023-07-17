package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "name",
        "fields",
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
    protected BigInteger max;
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
