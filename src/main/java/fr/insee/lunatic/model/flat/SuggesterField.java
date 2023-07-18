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
        "rules",
        "language",
        "min",
        "stemmer",
        "synonyms"
})
@Getter
@Setter
public class SuggesterField {

    @JsonProperty(required = true)
    protected String name;
    protected List<String> rules;
    protected String language;
    protected BigInteger min;
    protected Boolean stemmer;
    protected List<FieldSynonym> synonyms;

    public SuggesterField() {
        this.rules = new ArrayList<>();
        this.synonyms = new ArrayList<>();
    }
}