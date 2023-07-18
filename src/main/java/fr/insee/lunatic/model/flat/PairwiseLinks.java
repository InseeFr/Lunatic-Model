package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "componentType",
        "mandatory",
        "label",
        "declarations",
        "conditionFilter",
        "controls",
        "hierarchy",
        "bindingDependencies",
        "response",
        "xAxisIterations",
        "yAxisIterations",
        "components",
        "symLinks"
})
@Getter
@Setter
public class PairwiseLinks
    extends ComponentType
    implements ComponentNestingType
{

    protected LabelType xAxisIterations;
    protected LabelType yAxisIterations;
    protected List<ComponentType> components;
    protected SymLinksType symLinks;

    public PairwiseLinks() {
        super();
        this.components = new ArrayList<>();
}
}
