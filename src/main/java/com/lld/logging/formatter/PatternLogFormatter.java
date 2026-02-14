package com.lld.logging.formatter;

import com.lld.logging.model.LogMessage;

import java.time.format.DateTimeFormatter;

public class PatternLogFormatter implements LogFormatter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final String pattern;

    public PatternLogFormatter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String format(LogMessage logMessage) {
        return pattern
                .replace("%d", DATE_FORMATTER.format(logMessage.getTimestamp()))
                .replace("%p", logMessage.getLevel().name())
                .replace("%c", logMessage.getLoggerName())
                .replace("%m", logMessage.getMessage())
                .replace("%t", logMessage.getThreadName());
    }
}
