package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResizingPairwiseEntry extends ResizingEntry {

    private List<String> sizeForLinksVariables = new ArrayList<>();
    private List<String> linksVariables = new ArrayList<>();

}
