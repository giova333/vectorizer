package com.gladunalexander.vectorizer.embeddings;

import java.util.List;

import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;

public class OpenAIEmbeddingsProvider implements EmbeddingsProvider {

    private final OpenAiService openAiService;
    private final String model;

    public OpenAIEmbeddingsProvider(String token, String model) {
        this.openAiService = new OpenAiService(token);
        this.model = model;
    }

    @Override
    public List<Embedding> toEmbeddings(List<String> inputs) {
        var embeddingRequest = EmbeddingRequest.builder()
                .input(inputs)
                .model(model)
                .build();

        var embeddingResult = openAiService.createEmbeddings(embeddingRequest);

        return embeddingResult.getData().stream()
                .map(com.theokanning.openai.embedding.Embedding::getEmbedding)
                .map(Embedding::new)
                .toList();
    }
}
