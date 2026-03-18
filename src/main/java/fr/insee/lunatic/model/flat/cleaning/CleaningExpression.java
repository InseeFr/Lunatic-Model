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
    private Boolean isAggregatorUsed;
    @JsonProperty(value = "shouldCheckAllIterations")
    private Boolean shouldCheckAllIterations ;

    public CleaningExpression(String expression, String shapeFrom, boolean isAggregatorUsed) {
        this.expression = expression;
        this.shapeFrom = shapeFrom;
        this.isAggregatorUsed = isAggregatorUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CleaningExpression that = (CleaningExpression) o;
        return Objects.equals(expression, that.expression) && Objects.equals(shapeFrom, that.shapeFrom) && Objects.equals(isAggregatorUsed, that.isAggregatorUsed) && Objects.equals(shouldCheckAllIterations, that.shouldCheckAllIterations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, shapeFrom, isAggregatorUsed, shouldCheckAllIterations);
    }
}
