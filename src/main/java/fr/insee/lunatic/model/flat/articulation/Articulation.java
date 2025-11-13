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
    private final List<ArticulationItem> items = new ArrayList<>();

}
