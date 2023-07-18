package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "value",
        "label",
        "options"
})
@Getter
@Setter
public class HeaderType {

    protected String value;
    protected LabelType label;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Options> options;
    protected BigInteger colspan;
    protected BigInteger rowspan;

    public HeaderType() {
        this.options = new ArrayList<>();
    }
}
