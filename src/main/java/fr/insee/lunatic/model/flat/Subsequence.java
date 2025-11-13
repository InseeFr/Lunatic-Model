package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subsequence extends ComponentType implements ComponentSequenceType {

    public Subsequence() {
        super(ComponentTypeName.SUBSEQUENCE);
    }

    protected String goToPage;
}
