package com.gladunalexander.vectorizer.search;

import com.gladunalexander.vectorizer.core.CosineSimilarityCalculator;
import com.gladunalexander.vectorizer.core.InMemoryVectorStorage;
import com.gladunalexander.vectorizer.core.SimilarityCalculator;
import com.gladunalexander.vectorizer.core.Vector;
import com.gladunalexander.vectorizer.core.VectorData;
import com.gladunalexander.vectorizer.core.VectorStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KClosestVectorsSearchServiceTest {

    private final VectorStorage vectorStorage = new InMemoryVectorStorage();
    private final SimilarityCalculator similarityCalculator = new CosineSimilarityCalculator();
    private final KClosestVectorsSearchService searchService =
            new KClosestVectorsSearchService(vectorStorage, similarityCalculator);

    @BeforeEach
    void setUp() {
        vectorStorage.store(new Vector(9.3, 4.9, 6.2), "Text1");
        vectorStorage.store(new Vector(-9.3, -4.9, -6.2), "Text2");
        vectorStorage.store(new Vector(6.2, -4.9, 5.6), "Text3");
    }

    @Test
    void shouldFindKClosestVectors() {
        var vector = new Vector(8.3, 5.0, 6.3);

        var kClosestVectors = searchService.findKClosestVectors(vector, 2);

        assertThat(kClosestVectors).containsExactly(
                VectorData.builder()
                        .id(1L)
                        .vector(new Vector(9.3, 4.9, 6.2))
                        .text("Text1")
                        .build(),
                VectorData.builder()
                        .id(3L)
                        .vector(new Vector(6.2, -4.9, 5.6))
                        .text("Text3")
                        .build()
        );
    }
}