package fr.insee.lunatic.model.flat;

import lombok.NonNull;

public record CleanedVariableEntry(
        @NonNull String variableName,
        @NonNull String filterExpression) {}
