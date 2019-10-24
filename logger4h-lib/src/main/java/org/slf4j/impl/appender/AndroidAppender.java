package org.slf4j.impl.appender;

import android.text.TextUtils;
import android.util.Log;

import org.slf4j.event.EventConstants;
import org.slf4j.event.Level;
import org.slf4j.impl.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author hejing
 */
public class AndroidAppender extends AbstractAppender {
    private Builder mBuilder;

    private AndroidAppender(Builder builder) {
        this.mBuilder = builder;
        setLevel(builder.level);
        addInterceptor(builder.interceptors);
    }

    @Override
    protected void doAppend(Level logLevel, String tag, String msg) {
        tag = TextUtils.isEmpty(mBuilder.actualName) ? tag : mBuilder.actualName + " " + tag;
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
        private String actualName = "";
        private int bufferSize;

        public Builder setLevel(Level level) {
            this.level = level;
            return this;
        }

        public Builder setBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            addInterceptor(Collections.singletonList(interceptor));
            return this;
        }

        public Builder setActualName(String actualName) {
            this.actualName = actualName;
            return this;
        }

        public Builder addInterceptor(List<Interceptor> interceptorList) {
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            interceptors.addAll(interceptorList);
            return this;
        }

        public AndroidAppender create() {
            return new AndroidAppender(this);
        }
    }

}
