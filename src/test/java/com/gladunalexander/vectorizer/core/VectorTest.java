package com.gladunalexander.vectorizer.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VectorTest {

    @Test
    void shouldGetVectorDimension() {
        var vector = new Vector(3.0, 1.1, 8.9);

        var actualDimension = vector.dimension();

        assertThat(actualDimension).isEqualTo(3);
    }

    @Test
    void shouldCalculatedDotProductOfTwoVectors() {
        var vector1 = new Vector(3.0, 1.1, 8.9);
        var vector2 = new Vector(8.9, 4.7, 9.2);

        var actualDotProduct = vector1.dotProduct(vector2);

        assertThat(actualDotProduct).isEqualTo(113.75);
    }

    @Test
    void shouldCalculateVectorNorm() {
        var vector = new Vector(3.0, 1.1, 8.9);

        var actualNorm = vector.norm();

        assertThat(actualNorm).isEqualTo(9.456214887575262);
    }
}