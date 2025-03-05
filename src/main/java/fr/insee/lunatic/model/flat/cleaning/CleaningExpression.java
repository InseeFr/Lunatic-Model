package fr.insee.lunatic.model.flat.cleaning;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CleaningExpression {
    private String expression;
    private String shapeFrom;
    @JsonProperty(value = "isAggregatorUsed")
    private boolean isAggregatorUsed;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CleaningExpression that = (CleaningExpression) object;
        return isAggregatorUsed == that.isAggregatorUsed && Objects.equals(expression, that.expression) && Objects.equals(shapeFrom, that.shapeFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, shapeFrom, isAggregatorUsed);
    }
}
