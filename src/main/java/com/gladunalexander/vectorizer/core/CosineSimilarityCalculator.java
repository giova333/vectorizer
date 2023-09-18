package com.gladunalexander.vectorizer.core;

public class CosineSimilarityCalculator implements SimilarityCalculator {

    @Override
    public Double calculateSimilarity(Vector v1, Vector v2) {
        var dotProduct = v1.dotProduct(v2);
        var v1Norm = v1.norm();
        var v2Norm = v2.norm();

        return dotProduct / (v1Norm * v2Norm);
    }
}
