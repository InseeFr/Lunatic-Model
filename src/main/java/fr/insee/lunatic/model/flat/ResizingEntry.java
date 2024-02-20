package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "size",
        "variables",
        "sizeForLinksVariables",
        "linksVariables"})
@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION, defaultImpl = ResizingIterationEntry.class)
@JsonSubTypes({
        @JsonSubTypes.Type(ResizingIterationEntry.class),
        @JsonSubTypes.Type(ResizingPairwiseEntry.class)})
@Getter
@Setter
public abstract class ResizingEntry {

    private String size;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> variables = new ArrayList<>();

}
