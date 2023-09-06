package com.gladunalexander.vectorizer.core;

import java.util.Collection;

public interface VectorStorage {

    VectorData store(Vector vector, String text);

    VectorData get(Long id);

    Collection<VectorData> getVectors();
}
