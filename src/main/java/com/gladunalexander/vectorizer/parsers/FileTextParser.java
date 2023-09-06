package com.gladunalexander.vectorizer.parsers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

public class FileTextParser implements TextParser {

    private final File file;

    public FileTextParser(String fileLocation) {
        this.file = new File(fileLocation);
    }

    @Override
    @SneakyThrows
    public List<String> parse() {
        var text = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return Arrays.asList(text.split("\n"));
    }
}
