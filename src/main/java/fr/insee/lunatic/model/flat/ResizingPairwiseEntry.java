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
    @Getter
    @Setter
    public static class Size {

        /** VTL expression to resize the pairwise variable. */
        @JsonProperty("xAxisSize")
        private String xAxisSize;

        /** VTL expression to resize the arrays in the pairwise variable. */
        @JsonProperty("yAxisSize")
        private String yAxisSize;
    }

    /** {@link Size} */
    private Size sizeForLinksVariables;

    /** Name of Pairwise links variables to be resized. */
    private List<String> linksVariables = new ArrayList<>();

}
