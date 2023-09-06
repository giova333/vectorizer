package com.gladunalexander.vectorizer.core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record Vector(List<Double> value) {

    public Vector(Double... value) {
        this(Arrays.asList(value));
    }

    public Vector(List<Double> value) {
        this.value = Objects.requireNonNull(value);
    }

    public int dimension() {
        return value.size();
    }

    public double get(int index) {
        if (index < 0 || index >= value.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bound");
        }
        return value.get(index);
    }

    public double dotProduct(Vector other) {
        double dotProduct = 0.0;

        for (int i = 0; i < value.size(); i++) {
            dotProduct += this.get(i) * other.get(i);
        }
        return dotProduct;
    }

    public double norm() {
        return Math.sqrt(
                value.stream()
                        .mapToDouble(element -> Math.pow(element, 2))
                        .sum()
        );
    }
}
