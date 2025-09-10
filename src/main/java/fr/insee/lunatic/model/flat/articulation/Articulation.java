package fr.insee.lunatic.model.flat.articulation;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Articulation {
    /**
     * source: id of lunatic roundabout (articulation is based on roundabout)
     */
    @Getter
    @Setter
    private String source;

    /**
     * list of items to compute table of articulation
     */
    @Getter
    private List<ArticulationItem> items = new ArrayList<>();

    /** @deprecated The list is instantiated when the articulation object is created.
     * Use the getter method. */
    @Deprecated(since = "5.8.1")
    public void setItems(List<ArticulationItem> items) {
        this.items = items;
    }

}
