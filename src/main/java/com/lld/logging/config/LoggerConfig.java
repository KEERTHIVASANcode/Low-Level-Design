package com.lld.logging.config;

import com.lld.logging.appender.LogAppender;
import com.lld.logging.formatter.LogFormatter;
import com.lld.logging.model.LogLevel;

import java.util.List;

public class LoggerConfig {
    private final LogLevel minimumLevel;
    private final LogFormatter formatter;
    private final List<LogAppender> appenders;

    public LoggerConfig(LogLevel minimumLevel, LogFormatter formatter, List<LogAppender> appenders) {
        if (appenders == null || appenders.isEmpty()) {
            throw new IllegalArgumentException("At least one appender is required");
        }
        this.minimumLevel = minimumLevel;
        this.formatter = formatter;
        this.appenders = List.copyOf(appenders);
    }

    public LogLevel getMinimumLevel() {
        return minimumLevel;
    }

    public LogFormatter getFormatter() {
        return formatter;
    }

    public List<LogAppender> getAppenders() {
        return appenders;
    }
}
