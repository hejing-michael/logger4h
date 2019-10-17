package org.slf4j.impl.logger;


import org.slf4j.event.Level;
import org.slf4j.impl.appender.Appender;

/**
 * @author hejing
 */
public class LoggerImpl implements Appender {
    private Appender mAppender;

    /**
     * Package access allows only {@link AndroidLoggerFactory} to instantiate
     * SimpleLogger instances.
     */
    LoggerImpl(Appender appender) {
        this.mAppender = appender;
    }

    @Override
    public void append(Level logLevel, String tag, String msg) {
        if (mAppender != null) {
            mAppender.append(logLevel, tag, msg);
        }
    }

    @Override
    public void flush() {
        if (mAppender != null) {
            mAppender.flush();
        }
    }

    @Override
    public void release() {
        if (mAppender != null) {
            mAppender.release();
        }
    }
}
