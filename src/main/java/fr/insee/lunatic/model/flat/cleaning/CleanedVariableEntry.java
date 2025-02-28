package fr.insee.lunatic.model.flat.cleaning;

import lombok.NonNull;

public record CleanedVariableEntry(
        @NonNull String variableName,
        @NonNull String filterExpression) {}
