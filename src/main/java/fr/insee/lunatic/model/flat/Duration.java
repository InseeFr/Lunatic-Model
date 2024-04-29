package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Duration extends ComponentType implements ComponentSimpleResponseType {

    /** Value for a years/months duration format. */
    public static final String YEARS_MONTHS_FORMAT = "PnYnM";

    /** Value for a hours/minutes duration format. */
    public static final String HOURS_MINUTES_FORMAT = "PTnHnM";

    public Duration() {
        this.componentType = ComponentTypeEnum.DURATION;
    }

    /** Duration format in the XSD Duration Data Type style.
     * Must start with a 'P' (that stands for period). Then can be followed by 'nY' (years), 'nM' (months), 'nD' (days).
     * 'T' indicates the start of a time section that can be followed by 'nH' (hours), 'nM' (minutes), 'nS' (seconds). */
    @JsonProperty(required = true)
    private String format;

    @JsonProperty(required = true)
    @Setter
    protected ResponseType response;

    /**
     * Sets the duration format. Warning: this method doesn't do any strict validation.
     * @param format Format in the XSD Duration Data Type style.
     */
    public void setFormat(String format) {
        if (! (YEARS_MONTHS_FORMAT.equals(format) || HOURS_MINUTES_FORMAT.equals(format)))
            log.warn("Format '{}' does not match Lunatic commonly-used formats.", format);
        this.format = format;
    }

}
