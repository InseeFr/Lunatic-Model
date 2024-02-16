package fr.insee.lunatic.model.flat;

import lombok.NonNull;

public record CleaningEntry(
        @NonNull String variableName,
        @NonNull String filterExpression) {}
