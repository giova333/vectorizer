package com.gladunalexander.vectorizer.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CosineSimilarityCalculatorTest {

    private final SimilarityCalculator similarityCalculator = new CosineSimilarityCalculator();

    @Test
    void shouldCalculateCosineSimilarity() {
        var vector1 = new Vector(3.0, 1.1, 8.9);
        var vector2 = new Vector(8.9, 4.7, 9.2);

        var actualSimilarity = similarityCalculator.calculateSimilarity(vector1, vector2);

        assertThat(actualSimilarity).isEqualTo(0.8821606046732509);
    }

    @Test
    void shouldCalculateCosineSimilarityForTheSameVector() {
        var vector1 = new Vector(3.0, 1.1, 8.9);

        var actualSimilarity = similarityCalculator.calculateSimilarity(vector1, vector1);

        assertThat(actualSimilarity).isEqualTo(0.9999999999999999);
    }

    @Test
    void shouldCalculateCosineSimilarityForTheTwoOppositeVectors() {
        var vector1 = new Vector(3.0, 1.1, 8.9);
        var vector2 = new Vector(-3.0, -1.1, -8.9);

        var actualSimilarity = similarityCalculator.calculateSimilarity(vector1, vector2);

        assertThat(actualSimilarity).isEqualTo(-0.9999999999999999);
    }
}