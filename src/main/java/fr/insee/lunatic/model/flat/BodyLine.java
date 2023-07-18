package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "bodyCells"
})
@Getter
@Setter
public class BodyLine {

    public BodyLine() {
        this.bodyCells = new ArrayList<>();
    }

    @JsonValue
    protected List<BodyCell> bodyCells;
}
