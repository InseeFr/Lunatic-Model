package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "componentType",
        "maxLength",
        "min",
        "max",
        "decimals",
        "id",
        "value",
        "label",
        "format",
        "dateFormat",
        "unit",
        "options",
        "response",
        "bindingDependencies"
})
@Getter
@Setter
public class BodyCell {

    protected String value;
    protected LabelType label;
    protected String format;
    protected String dateFormat;
    protected String unit;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Options> options;
    protected ResponseType response;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> bindingDependencies;
    protected String componentType;
    protected BigInteger maxLength;
    protected Double min;
    protected Double max;
    protected BigInteger decimals;
    protected BigInteger colspan;
    protected BigInteger rowspan;

    protected String id;

    public BodyCell() {
        this.options = new ArrayList<>();
        this.bindingDependencies = new ArrayList<>();
    }
}
