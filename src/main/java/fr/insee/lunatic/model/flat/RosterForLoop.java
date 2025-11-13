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

    /**
     * Metadata on how the table should be displayed.
     * Value should be one of "HORIZONTAL", "VERTICAL" or "DEFAULT".
     * @deprecated Unused in Lunatic, to be removed.
     */
    @Deprecated(since = "3.14.0", forRemoval = true)
    protected String positioning;

    public RosterForLoop() {
        super(ComponentTypeEnum.ROSTER_FOR_LOOP);
        this.header = new ArrayList<>();
        this.components = new ArrayList<>();
    }
}
