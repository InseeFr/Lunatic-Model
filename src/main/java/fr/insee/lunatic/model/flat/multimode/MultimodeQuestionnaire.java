package fr.insee.lunatic.model.flat.multimode;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MultimodeQuestionnaire {
    /**
     * exemple: { "IS_MOVED": {type:VTL, value:"true"}, "IS_SPLIT": {type:VTL, value:"true"} }
     */
    private Map<String, MultimodeRule> rules;
}
