package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Resizing entry for a variable name that triggers the resizing of pairwise variable(s).
 */
@Getter
@Setter
public class ResizingPairwiseEntry extends ResizingEntry {

    /**
     * Size expressions for pairwise links variables.
     */
    @Setter
    public static class Size {

        // Note: the @JsonProperty was put on the getter methods due to a bug in jackson-databind 2.21.2

        /**
         * VTL expression to resize the pairwise variable.
         */
        private String xAxisSize;

        /**
         * VTL expression to resize the arrays in the pairwise variable.
         */
        private String yAxisSize;

        @JsonProperty("xAxisSize")
        public String getXAxisSize() {
            return this.xAxisSize;
        }

        @JsonProperty("yAxisSize")
        public String getYAxisSize() {
            return this.yAxisSize;
        }
    }

    /** {@link Size} */
    private Size sizeForLinksVariables;

    /** Name of Pairwise links variables to be resized. */
    private List<String> linksVariables = new ArrayList<>();

}
