package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "componentType",
        "page",
        "maxPage",
        "depth",
        "paginatedLoop",
        "conditionFilter",
        "hierarchy",
        "bindingDependencies",
        "loopDependencies",
        "lines",
        "components",
        "iterations"
})
@Getter
@Setter
public class Loop
    extends ComponentType
    implements ComponentNestingType
{

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<String> loopDependencies;
    protected LinesLoop lines;
    protected List<ComponentType> components;
    protected LabelType iterations;
    protected String maxPage;
    protected BigInteger depth;
    protected Boolean paginatedLoop;

    public Loop() {
        super();
        this.loopDependencies = new ArrayList<>();
        this.components = new ArrayList<>();
    }
}
