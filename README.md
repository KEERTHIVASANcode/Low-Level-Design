# Logging Framework - Low Level Design

This repository contains a simple low level design implementation of a configurable logging framework in Java.

## Features
- Multiple log levels (`DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`)
- Pluggable appenders (`ConsoleAppender`, `FileAppender`)
- Pattern based formatter
- Thread-safe logger
- Logger factory for creating named loggers

## Project structure
```
src/main/java/com/lld/logging
├── appender
├── config
├── core
├── formatter
└── model
```

## Example usage
```java
LoggerConfig config = new LoggerConfig(
    LogLevel.INFO,
    new PatternLogFormatter("[%d] [%p] [%c] %m"),
    List.of(new ConsoleAppender(), new FileAppender("logs/app.log"))
);

Logger logger = LoggerFactory.getLogger("PaymentService", config);
logger.info("Payment initialized");
logger.error("Payment failed");
```
