package com.lld.logging.formatter;

import com.lld.logging.model.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
