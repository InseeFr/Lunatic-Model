package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Roundabout component. Its behavior is similar to a loop, but with refinements.
 * The core idea is to display a menu (the "roundabout") for the iterations.
 */
@Getter
@Setter
public class Roundabout extends ComponentType implements ComponentNestingType {

    /**
     * Object that holds the configuration of roundabout's items.
     */
    @Getter
    @Setter
    public static class Item {

        /** Label of the items collected in the roundabout. */
        @JsonProperty(required = true)
        private LabelType label;

        /** Optional description of the items collected in the roundabout. */
        private LabelType description;

        /** Expression that determines if a roundabout's item has to receive answers or not. */
        private LabelType disabled;
    }

    public Roundabout() {
        super(ComponentTypeEnum.ROUNDABOUT);
        this.components = new ArrayList<>();
        this.loopDependencies = new ArrayList<>();
    }

    /** Expression that defines the number of items in the roundabout. */
    private LabelType iterations;

    /** Boolean option to lock or not an item answers when it gets the 'completed' status. */
    private Boolean locked;

    /** Name of the variable that stores the current progress in each roundabout's item. */
    private String progressVariable;

    /** List of variable names that are used in the size expression ('iterations') of the roundabout. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> loopDependencies;

    /** {@link Item} */
    private Item item;

    /** Roundabout components (sequences and response components). */
    private List<ComponentType> components;

}
