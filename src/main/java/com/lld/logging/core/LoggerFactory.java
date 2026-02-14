package com.lld.logging.core;

import com.lld.logging.config.LoggerConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class LoggerFactory {
    private static final ConcurrentMap<String, Logger> LOGGER_MAP = new ConcurrentHashMap<>();

    private LoggerFactory() {
    }

    public static Logger getLogger(String name, LoggerConfig config) {
        return LOGGER_MAP.computeIfAbsent(name, key -> new Logger(key, config));
    }
}
