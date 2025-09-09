package fr.insee.lunatic.model.flat.articulation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Articulation {
    /**
     * source: id of lunatic roundabout (articulation is based on roundabout)
     */
    private String source;

    /**
     * list of items to compute table of articulation
     */
    private List<ArticulationItem> items;
}
