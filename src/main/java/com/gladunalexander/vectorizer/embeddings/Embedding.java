package com.gladunalexander.vectorizer.embeddings;

import java.util.List;

import static java.util.Objects.requireNonNull;

public record Embedding(List<Double> value) {

    public Embedding(List<Double> value) {
        this.value = requireNonNull(value);
    }
}
