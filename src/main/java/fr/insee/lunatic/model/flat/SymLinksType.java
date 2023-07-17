package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "link"
})
@Getter
@Setter
public class SymLinksType {

    @JsonProperty(value="LINK", required = true)
    protected List<LINK> link;
    protected String name;

    public SymLinksType() {
        this.link = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class LINK {
        @JsonKey
        protected String source;
        @JsonValue
        protected String target;
    }
}
