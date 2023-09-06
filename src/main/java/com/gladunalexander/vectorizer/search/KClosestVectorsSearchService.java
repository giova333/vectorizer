package com.gladunalexander.vectorizer.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.gladunalexander.vectorizer.core.SimilarityCalculator;
import com.gladunalexander.vectorizer.core.Vector;
import com.gladunalexander.vectorizer.core.VectorData;
import com.gladunalexander.vectorizer.core.VectorStorage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import static java.util.Comparator.comparing;

@RequiredArgsConstructor
public class KClosestVectorsSearchService {

    private final VectorStorage vectorStorage;
    private final SimilarityCalculator similarityCalculator;

    public List<VectorData> findKClosestVectors(Vector vector, int k) {
        var vectors = vectorStorage.getVectors();
        var vectorToSimilarityPq = new PriorityQueue<Pair<VectorData, Double>>(comparing(Pair::getRight));

        for (var candidate : vectors) {
            var similarity = similarityCalculator.calculateSimilarity(vector, candidate.vector());
            vectorToSimilarityPq.offer(Pair.of(candidate, similarity));

            if (vectorToSimilarityPq.size() > k) {
                vectorToSimilarityPq.poll();
            }
        }
        var topK = new ArrayList<VectorData>();
        while (!vectorToSimilarityPq.isEmpty()) {
            topK.add(vectorToSimilarityPq.poll().getLeft());
        }
        Collections.reverse(topK);
        return topK;
    }
}
