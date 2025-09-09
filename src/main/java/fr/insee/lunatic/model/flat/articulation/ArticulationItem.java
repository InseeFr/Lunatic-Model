package fr.insee.lunatic.model.flat.articulation;

import fr.insee.lunatic.model.flat.LabelType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticulationItem extends LabelType {
    /**
     * label: name of column for articulation table
     */
    private String label;
}
