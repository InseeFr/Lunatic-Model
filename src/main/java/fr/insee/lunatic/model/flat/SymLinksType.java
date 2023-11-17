package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.insee.lunatic.conversion.SymLinksDeserializer;
import fr.insee.lunatic.conversion.SymLinksSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "link"
})
@Getter
@Setter
@JsonSerialize(using = SymLinksSerializer.class)
@JsonDeserialize(using = SymLinksDeserializer.class)
public class SymLinksType {

    protected String name;

    protected List<LINK> link;

    public SymLinksType() {
        this.link = new ArrayList<>();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LINK {
        protected String source;
        protected String target;
    }
}
