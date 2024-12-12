package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** Tables contain cells that can be a simple label or a component.
 * This class holds all the possible properties for such a cell.
 * TODO: remove this and do proper polymorphisme in tables instead. */
@JsonPropertyOrder({
        "componentType",
        "orientation",
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
        "optionResponses",
        "bindingDependencies"
})
@Getter
@Setter
public class BodyCell {

    /** Orientation for radio / checkbox cells. */
    protected Orientation orientation;

    protected String value;
    protected LabelType label;
    protected String format;
    protected String dateFormat;

    /** For input number cells. */
    protected InputNumber.Unit unit;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Option> options;
    /** For suggester cells: Name of the code list used for auto-completion. */
    private String storeName;
    /** For component cells: collected response of the cell. */
    protected ResponseType response;

    /** Collected or external variable names required to evaluate expressions used in the component properties.
     * @deprecated Binding dependencies at component level are not used anymore in Lunatic. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Deprecated(since = "3.4.0")
    protected List<String> bindingDependencies = new ArrayList<>();

    protected ComponentTypeEnum componentType;
    protected BigInteger maxLength;
    protected Double min;
    protected Double max;
    protected BigInteger decimals;
    protected BigInteger colspan;
    protected BigInteger rowspan;

    /** For suggester cells: option responses. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Suggester.OptionResponse> optionResponses;

    protected String id;

    public BodyCell() {
        this.options = new ArrayList<>();
        this.optionResponses = new ArrayList<>();
    }

    @JsonProperty("unit")
    public InputNumber.Unit getUnitWrapper() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(InputNumber.Unit unit) {
        this.unit = unit;
    }

    /** Legacy unit string property.
     * @deprecated Use label unit. */
    @JsonIgnore
    @Deprecated(since = "3.15.2")
    public String getUnit() {
        if (unit == null)
            return null;
        return unit.getValue();
    }

    @JsonIgnore
    public LabelType getUnitLabel() {
        if (unit == null)
            return null;
        return unit.getLabel();
    }

    /** Legacy unit string property.
     * @deprecated Use label unit. */
    @JsonIgnore
    @Deprecated(since = "3.15.2")
    public void setUnit(String value) {
        unit = new InputNumber.Unit();
        unit.setValue(value);
    }

    @JsonIgnore
    public void setUnit(LabelType labelType) {
        unit = new InputNumber.Unit();
        unit.setLabel(labelType);
    }

}
