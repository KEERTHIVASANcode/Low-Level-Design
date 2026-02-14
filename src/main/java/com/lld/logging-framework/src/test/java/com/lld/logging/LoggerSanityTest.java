package com.lld.logging;

import com.lld.logging.appender.LogAppender;
import com.lld.logging.config.LoggerConfig;
import com.lld.logging.core.Logger;
import com.lld.logging.formatter.PatternLogFormatter;
import com.lld.logging.model.LogLevel;

import java.util.ArrayList;
import java.util.List;

public class LoggerSanityTest {
    public static void main(String[] args) {
        List<String> captured = new ArrayList<>();
        LogAppender testAppender = captured::add;

        LoggerConfig config = new LoggerConfig(
                LogLevel.INFO,
                new PatternLogFormatter("[%p] %m"),
                List.of(testAppender)
        );

        Logger logger = new Logger("TestLogger", config);
        logger.debug("ignored");
        logger.info("included");

        if (captured.size() != 1 || !captured.get(0).contains("included")) {
            throw new AssertionError("Logger filtering failed");
        }

        System.out.println("LoggerSanityTest passed");
    }
}
