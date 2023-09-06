package com.gladunalexander.vectorizer.core;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryVectorStorage implements VectorStorage {

    private final AtomicLong idGenerator = new AtomicLong();
    private final Map<Long, VectorData> storage = new ConcurrentHashMap<>();

    @Override
    public VectorData store(Vector vector, String text) {
        var id = idGenerator.incrementAndGet();
        var vectorEntry = VectorData.builder()
                .id(id)
                .vector(vector)
                .text(text)
                .build();
        storage.put(id, vectorEntry);
        return vectorEntry;
    }

    @Override
    public VectorData get(Long id) {
        return storage.get(id);
    }

    @Override
    public Collection<VectorData> getVectors() {
        return storage.values();
    }
}
