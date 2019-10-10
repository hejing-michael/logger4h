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

    public static final int MAX_LENGTH_OF_SINGLE_MESSAGE = 4063;

    public int maxSingleLength = MAX_LENGTH_OF_SINGLE_MESSAGE;

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

    public void setMaxSingleLength(int maxSingleLength) {
        this.maxSingleLength = maxSingleLength;
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
            appendInner(logData.logLevel, logData.tag, logData.msg);
        }
        logData.recycle();
    }

    private void appendInner(Level logLevel, String tag, String msg) {
        if (msg.length() <= maxSingleLength) {
            doAppend(logLevel, tag, msg);
            return;
        }
        int msgLength = msg.length();
        int start = 0;
        int end = start + maxSingleLength;
        while (start < msgLength) {
            doAppend(logLevel, tag, msg.substring(start, end));
            start = end;
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
