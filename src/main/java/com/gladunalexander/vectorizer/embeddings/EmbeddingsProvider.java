package com.gladunalexander.vectorizer.embeddings;

import java.util.List;

public interface EmbeddingsProvider {

    List<Embedding> toEmbeddings(List<String> inputs);
}
