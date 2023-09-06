package com.gladunalexander.vectorizer;

import java.util.List;

import com.gladunalexander.vectorizer.embeddings.EmbeddingsProvider;

public interface Vectorizer {

    static Vectorizer from(List<String> text, EmbeddingsProvider embeddingsProvider) {
        return new DefaultVectorizer(text, embeddingsProvider);
    }

    List<String> similaritySearch(String text);

    List<String> similaritySearch(String text, int k);
}
