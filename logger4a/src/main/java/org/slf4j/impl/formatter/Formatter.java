package org.slf4j.impl.formatter;

import org.slf4j.event.Level;

/**
 * @author hejing
 */
public interface Formatter {
    String format(Level logLevel, String tag, String msg);
}
