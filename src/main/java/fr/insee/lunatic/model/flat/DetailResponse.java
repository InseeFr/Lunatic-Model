package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

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

}
