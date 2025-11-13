package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RosterForLoop
    extends ComponentType
    implements ComponentMultipleResponseType
{

    protected LinesRoster lines;
    protected List<HeaderType> header;
    protected List<BodyCell> components;
    protected LabelType iterations;

    public RosterForLoop() {
        super(ComponentTypeName.ROSTER_FOR_LOOP);
        this.header = new ArrayList<>();
        this.components = new ArrayList<>();
    }
}
