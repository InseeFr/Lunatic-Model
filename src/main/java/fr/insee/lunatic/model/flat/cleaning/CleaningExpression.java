package fr.insee.lunatic.model.flat.cleaning;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CleaningExpression {
    private String expression;
    private String shapeFrom;
    /**
     * isAggregatorUsed: determine if expression includes an aggregator (like count, sum,...)
     * Use-full to improve performance during resizing
     */
    @JsonProperty(value = "isAggregatorUsed")
    private Boolean isAggregatorUsed;
    /**
     * shouldCheckAllIterations: determine if expression need to be evaluated at each iteration
     */
    @JsonProperty(value = "shouldCheckAllIterations")
    private Boolean shouldCheckAllIterations ;

    public CleaningExpression(String expression, String shapeFrom, boolean isAggregatorUsed) {
        this.expression = expression;
        this.shapeFrom = shapeFrom;
        this.isAggregatorUsed = isAggregatorUsed;
    }
}
