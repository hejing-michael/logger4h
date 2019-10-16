package org.slf4j.impl.appender;


import org.slf4j.event.Level;
import org.slf4j.impl.formatter.Formatter;
import org.slf4j.impl.interceptor.Interceptor;
import org.slf4j.impl.logger.LogBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hejing
 */
public class FileAppender extends AbsAppender {

    private LogBuffer logBuffer;

    private Formatter formatter;

    private FileAppender(Builder builder) {
        logBuffer = new LogBuffer(builder.bufferFilePath, builder.bufferSize, builder.logFilePath, builder.compress);
        setLevel(builder.level);
        addInterceptor(builder.interceptors);
        setFormatter(builder.formatter);
    }

    public String getBufferPath() {
        return logBuffer.getBufferPath();
    }

    public int getBufferSize() {
        return logBuffer.getBufferSize();
    }

    public String getLogPath() {
        return logBuffer.getLogPath();
    }

    public void changeLogPath(String logPath) {
        logBuffer.changeLogPath(logPath);
    }

    public void setFormatter(Formatter formatter) {
        if (formatter != null) {
            this.formatter = formatter;
        }
    }

    @Override
    protected void doAppend(Level logLevel, String tag, String msg) {
        logBuffer.write(formatter.format(logLevel, tag, msg));
    }

    @Override
    public void flush() {
        super.flush();
        logBuffer.flushAsync();
    }

    @Override
    public void release() {
        super.release();
        logBuffer.release();
    }

    public static class Builder {

        private final String bufferFilePath;
        private String logFilePath;
        private int bufferSize = 4096;
        private Level level = Level.TRACE;
        private List<Interceptor> interceptors;
        private Formatter formatter;
        private boolean compress;

        public Builder(String defaultBufferPath) {
            this.bufferFilePath = defaultBufferPath;
        }

        public Builder setBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public Builder setLogFilePath(String logFilePath) {
            this.logFilePath = logFilePath;
            return this;
        }

        public Builder setLevel(Level level) {
            this.level = level;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            interceptors.add(interceptor);
            return this;
        }

        public Builder addInterceptor(List<Interceptor> interceptorList) {
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            interceptors.addAll(interceptorList);
            return this;
        }

        public Builder setFormatter(Formatter formatter) {
            this.formatter = formatter;
            return this;
        }

        public Builder setCompress(boolean compress) {
            this.compress = compress;
            return this;
        }

        public FileAppender create() {
            if (logFilePath == null) {
                throw new IllegalArgumentException("logFilePath cannot be null");
            }
            if (formatter == null) {
                formatter = new Formatter() {
                    @Override
                    public String format(Level logLevel, String tag, String msg) {
                        return String.format("%s/%s: %s\n", logLevel.toString(), tag, msg);
                    }
                };
            }
            return new FileAppender(this);
        }
    }

}
