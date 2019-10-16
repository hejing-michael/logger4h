package org.slf4j.impl.appender;

import org.slf4j.event.Level;
import org.slf4j.impl.interceptor.Interceptor;
import org.slf4j.impl.interceptor.LevelInterceptor;
import org.slf4j.impl.interceptor.LogData;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hejing
 */
public abstract class AbsAppender implements Appender {

    private List<Interceptor> interceptors = new ArrayList<>();

    private LevelInterceptor levelInterceptor = new LevelInterceptor();
    private LogData mLogData = new LogData();

    public AbsAppender() {
        addInterceptor(levelInterceptor);
    }

    public void setLevel(Level level) {
        levelInterceptor.setLevel(level);
    }

    public void addInterceptor(List<Interceptor> interceptors) {
        if (interceptors != null && !interceptors.isEmpty()) {
            this.interceptors.addAll(interceptors);
        }
    }

    public void addInterceptor(Interceptor interceptor) {
        if (interceptor != null) {
            interceptors.add(interceptor);
        }
    }

    @Override
    public void append(Level logLevel, String tag, String msg) {
        LogData logData = mLogData.obtain(logLevel, tag, msg);
        boolean print = true;
        for (Interceptor interceptor : interceptors) {
            if (interceptor.intercept(logData)) {
                print = false;
                break;
            }
        }
        if (print) {
            doAppend(logLevel, tag, msg);
        }
        logData.recycle();
    }

    protected abstract void doAppend(Level logLevel, String tag, String msg);

    @Override
    public void flush() {

    }

    @Override
    public void release() {

    }
}
