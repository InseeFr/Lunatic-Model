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
@Getter @Setter
public class BodyCell {

    /** Orientation for radio / checkbox cells. */
    protected Orientation orientation;

    protected String value;
    protected LabelType label;
    protected String format;

    protected String dateFormat;

    /** For input number cells. */
    protected LabelType unit;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Option> options;
    /** For suggester cells: Name of the code list used for auto-completion. */

    private String storeName;

    /** For component cells: collected response of the cell. */
    protected ResponseType response;

    protected ComponentTypeName componentType;
    protected BigInteger maxLength;

    @JsonIgnore
    private Boundaries boundaries;

    protected BigInteger decimals;
    protected BigInteger colspan;
    protected BigInteger rowspan;

    /** For suggester cells: option responses. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<Suggester.OptionResponse> optionResponses;

    protected ConditionFilterType conditionFilter;

    protected LabelType conditionReadOnly;

    protected String id;

    public BodyCell() {
        this.options = new ArrayList<>();
        this.optionResponses = new ArrayList<>();
    }

    /**
     * @deprecated Use <code>getUnit()</code> method.
     */
    @JsonIgnore
    @Deprecated(since = "6.0.0")
    public LabelType getUnitLabel() {
        return unit;
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
