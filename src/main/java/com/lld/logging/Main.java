package com.lld.logging;

import com.lld.logging.appender.ConsoleAppender;
import com.lld.logging.appender.FileAppender;
import com.lld.logging.config.LoggerConfig;
import com.lld.logging.core.Logger;
import com.lld.logging.core.LoggerFactory;
import com.lld.logging.formatter.PatternLogFormatter;
import com.lld.logging.model.LogLevel;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoggerConfig config = new LoggerConfig(
                LogLevel.INFO,
                new PatternLogFormatter("[%d] [%p] [%c] [%t] %m"),
                List.of(new ConsoleAppender(), new FileAppender("logs/app.log"))
        );

        Logger logger = LoggerFactory.getLogger("LoggingLLD", config);

        logger.debug("This debug log is filtered.");
        logger.info("Application started.");
        logger.warn("Potential issue detected.");
        logger.error("Something went wrong.");
    }
}
