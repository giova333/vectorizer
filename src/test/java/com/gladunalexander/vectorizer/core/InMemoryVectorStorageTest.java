package com.gladunalexander.vectorizer.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryVectorStorageTest {

    private final VectorStorage vectorStorage = new InMemoryVectorStorage();

    @Test
    void shouldAddVectorsToVectorStorage() {
        var vector1 = new Vector(3.0, 1.1, 8.9);
        var vector1Text = "Associated text for vector1";

        var vector2 = new Vector(8.9, 4.7, 9.2);
        var vector2Text = "Associated text for vector2";

        var vectorData1 = vectorStorage.store(vector1, vector1Text);
        var vectorData2 = vectorStorage.store(vector2, vector2Text);

        assertThat(vectorData1).isEqualTo(
                VectorData.builder()
                        .id(1L)
                        .vector(vector1)
                        .text(vector1Text)
                        .build());

        assertThat(vectorData2).isEqualTo(
                VectorData.builder()
                        .id(2L)
                        .vector(vector2)
                        .text(vector2Text)
                        .build());

        assertThat(vectorStorage.get(1L)).isEqualTo(vectorData1);
        assertThat(vectorStorage.get(2L)).isEqualTo(vectorData2);

        assertThat(vectorStorage.getVectors()).containsExactlyInAnyOrder(vectorData1, vectorData2);
    }
}