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
     * shouldCheckAllIterations: determine if expression need to be evaluated during resizing operations
     *  Use-full to improve performance during resizing
     *  Used for expression includes an aggregator (like count, sum,...), or for other business case
     */
    @JsonProperty(value = "shouldCheckDuringResizing")
    private Boolean shouldCheckDuringResizing;
    /**
     * shouldCheckAllIterations: determine if expression need to be evaluated at each iteration
     */
    @JsonProperty(value = "shouldCheckAllIterations")
    private Boolean shouldCheckAllIterations ;

    public CleaningExpression(String expression, String shapeFrom, Boolean shouldCheckDuringResizing) {
        this.expression = expression;
        this.shapeFrom = shapeFrom;
        this.shouldCheckDuringResizing = shouldCheckDuringResizing;
    }
}
