package fr.insee.lunatic.model.flat.multimode;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MultimodeLeaf extends MultimodeQuestionnaire{
    /**
     * source: id of lunatic roundabout (articulation is based on roundabout)
     */
    private String source;
}
