package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NumberBoundaries implements Boundaries {
    private Double min;
    private Double max;
}
