package com.gladunalexander;

import com.gladunalexander.vectorizer.Vectorizer;
import com.gladunalexander.vectorizer.embeddings.OpenAIEmbeddingsProvider;
import com.gladunalexander.vectorizer.parsers.FileTextParser;

public class Example {
    public static void main(String[] args) {

        var textParser = new FileTextParser("sentences.txt");
        var text = textParser.parse();

        var embeddingsProvider = new OpenAIEmbeddingsProvider(System.getenv("OPEN_API_KEY"), "text-embedding-ada-002");

        var vectorizer = Vectorizer.from(text, embeddingsProvider);

        var input = "I love playing guitar";

        var similarText = vectorizer.similaritySearch(input);

        similarText.forEach(System.out::println);
    }

}