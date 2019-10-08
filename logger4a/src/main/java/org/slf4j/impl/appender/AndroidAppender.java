package org.slf4j.impl.appender;

import android.util.Log;

import org.slf4j.event.EventConstants;
import org.slf4j.event.Level;
import org.slf4j.impl.interceptor.Interceptor;
import org.slf4j.impl.interceptor.LogData;

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
        switch (logLevel.toInt()) {
            case EventConstants.DEBUG_INT:
                Log.d(tag, msg);
                break;
            case EventConstants.INFO_INT:
                Log.i(tag, msg);
                break;
            case EventConstants.WARN_INT:
                Log.w(tag, msg);
                break;
            case EventConstants.ERROR_INT:
                Log.e(tag, msg);
                break;
            default:
                Log.v(tag, msg);
                break;
        }
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
