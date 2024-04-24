package fr.insee.lunatic.model.flat;

import lombok.Getter;
import lombok.Setter;

/**
 * Component to be used for Lunatic "management mode".
 * Its purpose is to show if a filter would be applied on a component in the management mode where filters are
 * by-passed.
 * @deprecated Has been used in Lunatic v1 but not anymore. To be deleted for now.
 */
@Getter
@Setter
@Deprecated(since = "3.7.0", forRemoval = true)
class FilterDescription extends ComponentType {

    protected Boolean filterDescription;

}
