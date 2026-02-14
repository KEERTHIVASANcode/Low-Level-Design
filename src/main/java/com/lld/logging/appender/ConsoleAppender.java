package com.lld.logging.appender;

public class ConsoleAppender implements LogAppender {
    @Override
    public void append(String formattedMessage) {
        System.out.println(formattedMessage);
    }
}
