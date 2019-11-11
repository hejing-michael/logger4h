package org.slf4j.impl.logger;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.impl.appender.AndroidLoggerAdapter;

/**
 * @author hejin
 */
public class AndroidLogcatFactory implements ILoggerFactory {
    @Override
    public Logger getLogger(String name) {
        return new AndroidLoggerAdapter(name);
    }
}
