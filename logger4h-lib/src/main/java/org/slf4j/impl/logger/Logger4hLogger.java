package org.slf4j.impl.logger;

import org.slf4j.Logger;

public interface Logger4hLogger extends Logger {

    void println(int priority, String tag, String msg);

    void flush();

    void release();
}
