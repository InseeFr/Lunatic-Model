package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "name",
        "rules",
        "fieldRules",
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected FieldRules rules;
    protected String language;
    protected BigInteger min;
    protected Boolean stemmer;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected Map<String, List<String>> synonyms;

    public SuggesterField() {
        synonyms = new HashMap<>();
    }

}
