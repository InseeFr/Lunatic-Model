package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * Response object for the "other, please specify" feature.
 */
@Getter
@Setter
public class DetailResponse {

    /** "Please, specify" label. */
    LabelType label;

    /** Detail response. */
    ResponseType response;

    /** Maximum length allowed for the text value, if applicable. */
    @JsonProperty("maxLength")
    protected BigInteger maxLength;

}
