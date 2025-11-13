package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.insee.lunatic.conversion.UnitDeserializer;
import fr.insee.lunatic.conversion.UnitSerializer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Response component to collect numeric data.
 */
@Getter
@Setter
public class InputNumber extends ComponentType implements ComponentSimpleResponseType, ComponentMandatory {

    public InputNumber() {
        super(ComponentTypeEnum.INPUT_NUMBER);
    }

    /** Indicates whether the response is mandatory for this component. */
    @JsonProperty("isMandatory")
    private Boolean mandatory;

    /**
     * Wrapper class for the unit property to ensure backward compatibility in serialization/deserialization.
     */
    @JsonSerialize(using = UnitSerializer.class)
    @JsonDeserialize(using = UnitDeserializer.class)
    @Getter
    @Setter
    public static class Unit {
        private String value;
        private LabelType label;
    }

    /** Minimum value allowed. */
    @Getter @Setter
    protected Double min;

    /** Maximum value allowed. */
    @Getter @Setter
    protected Double max;

    /** Number of decimals allowed. null is equivalent to zero. */
    @Getter @Setter
    protected BigInteger decimals;

    /** Unit of the numeric response. */
    protected Unit unit;

    @JsonProperty("unit")
    public Unit getUnitWrapper() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /** Legacy unit string property.
     * @deprecated Use label unit. */
    @SuppressWarnings("java:S1192") // Sonar identifies deprecated tag as duplicate code
    @JsonIgnore
    @Deprecated(since = "3.14.0", forRemoval = true)
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
    @SuppressWarnings("java:S1192") // Sonar identifies deprecated tag as duplicate code
    @JsonIgnore
    @Deprecated(since = "3.14.0", forRemoval = true)
    public void setUnit(String value) {
        unit = new Unit();
        unit.setValue(value);
    }

    @JsonIgnore
    public void setUnit(LabelType labelType) {
        unit = new Unit();
        unit.setLabel(labelType);
    }

    /** {@link ResponseType} */
    @Getter @Setter
    @JsonProperty(required = true)
    protected ResponseType response;

}
