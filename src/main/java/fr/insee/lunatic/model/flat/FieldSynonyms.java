package fr.insee.lunatic.model.flat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.insee.lunatic.conversion.FieldSynonymsSerializer;

import java.util.ArrayList;

@JsonSerialize(using = FieldSynonymsSerializer.class)
public class FieldSynonyms extends ArrayList<FieldSynonym> {}
