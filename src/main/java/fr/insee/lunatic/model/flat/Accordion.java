package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Accordion extends ComponentType {

    List<Item> items;

    public Accordion() {
        super(ComponentTypeName.ACCORDION);
        this.items = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class Item {
        LabelType label;
        LabelType body;
    }

}
