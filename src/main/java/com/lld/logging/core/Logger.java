package com.lld.logging.core;

import com.lld.logging.config.LoggerConfig;
import com.lld.logging.model.LogLevel;
import com.lld.logging.model.LogMessage;

public class Logger {
    private final String name;
    private final LoggerConfig config;

    public Logger(String name, LoggerConfig config) {
        this.name = name;
        this.config = config;
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    private void log(LogLevel level, String message) {
        if (!level.isEnabledFor(config.getMinimumLevel())) {
            return;
        }

        LogMessage logMessage = new LogMessage(level, name, message);
        String formattedMessage = config.getFormatter().format(logMessage);

        for (var appender : config.getAppenders()) {
            appender.append(formattedMessage);
        }
    }
}
