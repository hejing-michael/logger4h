package org.slf4j.impl.appender;

import android.util.Log;

import org.slf4j.event.Level;
import org.slf4j.impl.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author hejing
 */
public class AndroidAppender extends AbsAppender {

    private AndroidAppender(Builder builder) {
        setLevel(builder.level);
        addInterceptor(builder.interceptors);
    }

    @Override
    protected void doAppend(Level logLevel, String tag, String msg) {
        Log.println(getLogLevel(logLevel), tag, msg);
    }

    private int getLogLevel(Level logLevel) {
        return logLevel.toInt() / 10 + (Level.ERROR.toInt() / 10 - Log.ERROR);
    }

    public static class Builder {
        private Level level = Level.DEBUG;
        private List<Interceptor> interceptors;

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

        public AndroidAppender create() {
            return new AndroidAppender(this);
        }
    }

}
