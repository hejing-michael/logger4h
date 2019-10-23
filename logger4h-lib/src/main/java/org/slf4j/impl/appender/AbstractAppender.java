package org.slf4j.impl.appender;

import org.slf4j.event.Level;
import org.slf4j.impl.interceptor.Interceptor;
import org.slf4j.impl.interceptor.LevelInterceptor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hejing
 */
public abstract class AbstractAppender implements Appender {
    private final int maxSingleLength;

    private List<Interceptor> interceptors = new ArrayList<>();

    private LevelInterceptor levelInterceptor = new LevelInterceptor();

    AbstractAppender(int maxSingleLength) {
        this.maxSingleLength = maxSingleLength;
        addInterceptor(levelInterceptor);
    }

    public void setLevel(Level level) {
        levelInterceptor.setLevel(level);
    }

    void addInterceptor(List<Interceptor> interceptors) {
        if (interceptors != null && !interceptors.isEmpty()) {
            this.interceptors.addAll(interceptors);
        }
    }

    private void addInterceptor(Interceptor interceptor) {
        if (interceptor != null) {
            interceptors.add(interceptor);
        }
    }

    @Override
    public void append(Level logLevel, String tag, String msg) {

        boolean print = true;
        for (Interceptor interceptor : interceptors) {
            if (interceptor.intercept(logLevel, tag, msg)) {
                print = false;
                break;
            }
        }
        if (print) {
            appendInner(logLevel, tag, msg);
        }
    }

    protected void appendInner(Level logLevel, String tag, String msg) {
        if (msg.length() <= maxSingleLength) {
            doAppend(logLevel, tag, msg);
            return;
        }
        int msgLength = msg.length();
        int start = 0;
        int end = start + maxSingleLength;
        while (start < msgLength) {
            doAppend(logLevel, tag, msg.substring(start, end));
            start = end + 1;
            end = Math.min(start + maxSingleLength, msgLength);
        }
    }

    protected abstract void doAppend(Level logLevel, String tag, String msg);

    @Override
    public void flush() {

    }

    @Override
    public void release() {

    }
}
