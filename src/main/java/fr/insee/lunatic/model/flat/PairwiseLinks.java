package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "componentType",
        "mandatory",
        "page",
        "label",
        "declarations",
        "conditionFilter",
        "controls",
        "hierarchy",
        "bindingDependencies",
        "xAxisIterations",
        "yAxisIterations",
        "sourceVariables",
        "components",
        "symLinks"
})
@Getter
@Setter
public class PairwiseLinks extends ComponentType implements ComponentNestingType {

    /**
     * Variables associated with the pairwise links component.
     * @param name Name variable name.
     * @param gender Gender variable name.
     */
    public record SourceVariables(
            String name,
            String gender) {}

    @JsonProperty("xAxisIterations")
    protected LabelType xAxisIterations;

    @JsonProperty("yAxisIterations")
    protected LabelType yAxisIterations;

    /** {@link SourceVariables} */
    private SourceVariables sourceVariables;

    protected List<ComponentType> components;
    protected SymLinksType symLinks;

    public PairwiseLinks() {
        super(ComponentTypeEnum.PAIRWISE_LINKS);
        this.components = new ArrayList<>();
    }

    public static SymLinksType createDefaultSymLinks(String name) {
        SymLinksType symLinksType = new SymLinksType();
        symLinksType.setName(name);
        List<SymLinksType.LINK> links = symLinksType.getLink();

        links.add(new SymLinksType.LINK("1", "1"));
        links.add(new SymLinksType.LINK("2", "3"));
        links.add(new SymLinksType.LINK("3", "2"));
        links.add(new SymLinksType.LINK("4", "4"));
        links.add(new SymLinksType.LINK("5", "6"));
        links.add(new SymLinksType.LINK("6", "5"));
        links.add(new SymLinksType.LINK("7", "8"));
        links.add(new SymLinksType.LINK("8", "7"));
        links.add(new SymLinksType.LINK("9", "10"));
        links.add(new SymLinksType.LINK("10", "9"));
        links.add(new SymLinksType.LINK("11", "13"));
        links.add(new SymLinksType.LINK("12", "12"));
        links.add(new SymLinksType.LINK("13", "11"));
        links.add(new SymLinksType.LINK("14", null));
        links.add(new SymLinksType.LINK("15", "15"));
        links.add(new SymLinksType.LINK("16", "16"));
        links.add(new SymLinksType.LINK("17", "17"));
        links.add(new SymLinksType.LINK("18", "18"));

        return symLinksType;
    }
}
