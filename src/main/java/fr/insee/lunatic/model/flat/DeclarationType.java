package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "id",
        "declarationType",
        "position",
        "label"
})
@Getter
@Setter
public class DeclarationType {

    @JsonProperty(required = true)
    protected LabelType label;
    @JsonProperty(required = true)
    protected String id;
    protected DeclarationTypeEnum declarationType;
    protected DeclarationPositionEnum position;
}
