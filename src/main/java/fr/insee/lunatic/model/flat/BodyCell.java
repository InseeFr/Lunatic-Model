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
public class BodyCell {

    /** Orientation for radio / checkbox cells. */
    @Getter @Setter
    protected Orientation orientation;

    @Getter @Setter
    protected String value;
    @Getter @Setter
    protected LabelType label;
    @Getter @Setter
    protected String format;

    @Getter @Setter
    protected String dateFormat;

    /** For input number cells. */
    protected InputNumber.Unit unit;

    @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Option> options;
    /** For suggester cells: Name of the code list used for auto-completion. */

    @Getter @Setter
    private String storeName;

    /** For component cells: collected response of the cell. */
    @Getter @Setter
    protected ResponseType response;

    /** Collected or external variable names required to evaluate expressions used in the component properties.
     * @deprecated Binding dependencies at component level are not used anymore in Lunatic. */
    @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Deprecated(since = "3.4.0", forRemoval = true)
    protected List<String> bindingDependencies = new ArrayList<>();

    @Getter @Setter
    protected ComponentTypeEnum componentType;
    @Getter @Setter
    protected BigInteger maxLength;

    @JsonIgnore
    private Boundaries boundaries;

    @Getter @Setter
    protected BigInteger decimals;
    @Getter @Setter
    protected BigInteger colspan;
    @Getter @Setter
    protected BigInteger rowspan;

    /** For suggester cells: option responses. */
    @Getter @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Suggester.OptionResponse> optionResponses;

    @Getter @Setter
    protected ConditionFilterType conditionFilter;

    @Getter @Setter
    protected LabelType conditionReadOnly;

    @Getter @Setter
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
    @Deprecated(since = "3.15.2", forRemoval = true) //NOSONAR
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
    @Deprecated(since = "3.15.2", forRemoval = true) //NOSONAR
    public void setUnit(String value) {
        unit = new InputNumber.Unit();
        unit.setValue(value);
    }

    @JsonIgnore
    public void setUnit(LabelType labelType) {
        unit = new InputNumber.Unit();
        unit.setLabel(labelType);
    }

    @JsonProperty("min")
    public Object getMin() {
        return boundaries != null ? boundaries.getMin() : null;
    }

    @JsonProperty("max")
    public Object getMax() {
        return boundaries != null ? boundaries.getMax() : null;
    }

    @JsonProperty("min")
    public void setMin(Object min) {
        if (boundaries == null) {
            instantiateBoundaries();
        }
        if (boundaries instanceof NumberBoundaries numberBoundaries && min instanceof Number number) {
            // Note: using Number to work with integers deserialization
            numberBoundaries.setMin(number.doubleValue());
            return;
        }
        if (boundaries instanceof StringBoundaries stringBoundaries && min instanceof String string) {
            stringBoundaries.setMin(string);
            return;
        }
        throw new IllegalArgumentException("Invalid or inconsistent type for min value in body cell.");
    }

    @JsonProperty("max")
    public void setMax(Object max) {
        if (boundaries == null) {
            instantiateBoundaries();
        }
        if (boundaries instanceof NumberBoundaries numberBoundaries && max instanceof Number number) {
            // Note: using Number to work with integers deserialization
            numberBoundaries.setMax(number.doubleValue());
            return;
        }
        if (boundaries instanceof StringBoundaries stringBoundaries && max instanceof String string) {
            stringBoundaries.setMax(string);
            return;
        }
        throw new IllegalArgumentException("Invalid or inconsistent type for max value in body cell.");
    }

    private void instantiateBoundaries() {
        boundaries = switch (componentType) {
            case INPUT_NUMBER -> new NumberBoundaries();
            case DATEPICKER -> new StringBoundaries();
            default -> throw new IllegalStateException(
                    "Component of type '" + componentType + "' doesn't have a min or max property.");
        };
    }

}
