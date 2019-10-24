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
    private final List<Interceptor> interceptors = new ArrayList<>();

    private LevelInterceptor levelInterceptor = new LevelInterceptor();

    AbstractAppender() {
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
            doAppend(logLevel, tag, msg);
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
