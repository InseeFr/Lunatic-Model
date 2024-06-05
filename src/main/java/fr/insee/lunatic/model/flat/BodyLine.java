package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    /**
     * Private constructor only meant to be used by jackson (through reflection) for deserialization.
     * @param bodyCells List mapped by jackson.
     */
    @JsonCreator @SuppressWarnings("unused")
    private BodyLine(final List<BodyCell> bodyCells) {
        this.bodyCells = bodyCells;
    }

    @JsonValue
    protected List<BodyCell> bodyCells;

}
