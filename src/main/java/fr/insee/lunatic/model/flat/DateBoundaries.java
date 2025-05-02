package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class DateBoundaries implements Boundaries {
    private String min;
    private String max;
}