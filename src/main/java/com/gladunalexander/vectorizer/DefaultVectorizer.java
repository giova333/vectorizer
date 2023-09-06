package com.gladunalexander.vectorizer;

import java.util.List;

import com.gladunalexander.vectorizer.core.CosineSimilarityCalculator;
import com.gladunalexander.vectorizer.core.InMemoryVectorStorage;
import com.gladunalexander.vectorizer.core.SimilarityCalculator;
import com.gladunalexander.vectorizer.core.Vector;
import com.gladunalexander.vectorizer.core.VectorData;
import com.gladunalexander.vectorizer.core.VectorStorage;
import com.gladunalexander.vectorizer.embeddings.EmbeddingsProvider;
import com.gladunalexander.vectorizer.search.KClosestVectorsSearchService;

public class DefaultVectorizer implements Vectorizer {

    private static final int DEFAULT_K = 3;

    private final KClosestVectorsSearchService searchService;
    private final EmbeddingsProvider embeddingsProvider;

    public DefaultVectorizer(List<String> text,
                             EmbeddingsProvider embeddingsProvider) {
        this(text, embeddingsProvider, new InMemoryVectorStorage(), new CosineSimilarityCalculator());
    }

    public DefaultVectorizer(List<String> text,
                             EmbeddingsProvider embeddingsProvider,
                             VectorStorage vectorStorage,
                             SimilarityCalculator similarityCalculator) {
        this.embeddingsProvider = embeddingsProvider;
        this.searchService = new KClosestVectorsSearchService(vectorStorage, similarityCalculator);

        var embeddings = embeddingsProvider.toEmbeddings(text);
        for (int i = 0; i < text.size(); i++) {
            vectorStorage.store(new Vector(embeddings.get(i).value()), text.get(i));
        }
    }

    @Override
    public List<String> similaritySearch(String text) {
        return similaritySearch(text, DEFAULT_K);
    }

    @Override
    public List<String> similaritySearch(String text, int k) {
        var embeddings = embeddingsProvider.toEmbeddings(List.of(text));
        var vector = new Vector(embeddings.get(0).value());
        return searchService.findKClosestVectors(vector, k).stream()
                .map(VectorData::text)
                .toList();
    }
}
