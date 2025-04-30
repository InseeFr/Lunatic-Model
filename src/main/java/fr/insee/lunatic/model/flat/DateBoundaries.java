package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateBoundaries implements Boundaries {
    private String min;
    private String max;

    @Override
    public ComponentTypeEnum getComponentType() {
        return ComponentTypeEnum.DATEPICKER;
    }
}