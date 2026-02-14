package com.lld.logging.model;

public enum LogLevel {
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    FATAL(5);

    private final int severity;

    LogLevel(int severity) {
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }

    public boolean isEnabledFor(LogLevel configuredLevel) {
        return this.severity >= configuredLevel.severity;
    }
}
