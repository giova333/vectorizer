package com.gladunalexander.vectorizer.core;

import lombok.Builder;

@Builder
public record VectorData(
        Long id,
        Vector vector,
        String text) {
}
