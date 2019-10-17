package org.slf4j.impl.appender;

import org.slf4j.event.Level;

/**
 * @author hejin
 */
public interface Appender {
    void append(Level logLevel, String tag, String msg);

    void flush();

    void release();
}
