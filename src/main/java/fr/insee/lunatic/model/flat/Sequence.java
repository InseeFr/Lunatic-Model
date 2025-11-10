package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sequence extends ComponentType implements ComponentSequenceType {

    public Sequence() {
        super(ComponentTypeEnum.SEQUENCE);
    }

}
