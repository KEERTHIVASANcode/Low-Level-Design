package com.lld.logging.appender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileAppender implements LogAppender {
    private final Path filePath;

    public FileAppender(String filePath) {
        this.filePath = Path.of(filePath);
        initializeFile();
    }

    private void initializeFile() {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to initialize log file: " + filePath, e);
        }
    }

    @Override
    public synchronized void append(String formattedMessage) {
        try {
            Files.writeString(filePath, formattedMessage + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to write log to file: " + filePath, e);
        }
    }
}
