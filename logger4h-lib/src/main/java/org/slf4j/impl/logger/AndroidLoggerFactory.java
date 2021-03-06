/*
 * Created 21.10.2009
 *
 * Copyright (c) 2009 SLF4J.ORG
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.slf4j.impl.logger;

import android.text.TextUtils;
import android.util.Log;

import org.slf4j.ILoggerFactory;
import org.slf4j.impl.appender.AndroidAppender;
import org.slf4j.impl.appender.Appender;
import org.slf4j.impl.appender.FileAppender;
import org.slf4j.impl.formatter.DateFileFormatter;
import org.slf4j.impl.formatter.Formatter;
import org.slf4j.impl.interceptor.Interceptor;
import org.slf4j.impl.utils.FileOutTimeUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * An implementation of {@link ILoggerFactory} which always returns
 * {@link AndroidLogger} instances.
 *
 * @author Thorsten M&ouml;ler
 * @version $Rev:$; $Author:$; $Date:$
 */
public class AndroidLoggerFactory implements ILoggerFactory {
    private static final String TAG = "LoggerFactory";
    /**
     * tag names cannot be longer on Android platform
     */
    private final Map<String, AndroidLogger> loggerMap = new HashMap<>();
    private final Builder mBuilder;
    private Executor executor = Executors.newCachedThreadPool();

    private AndroidLoggerFactory(Builder builder) {
        this.mBuilder = builder;
    }

    private AndroidLoggerFactory(Executor executor, Builder builder) {
        this.executor = executor;
        this.mBuilder = builder;
    }

    /**
     * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
     */
    @Override
    public org.slf4j.Logger getLogger(final String name) {
        // fix for bug #173
        final String actualName = forceValidName(name, mBuilder.maxFileNameLength);

        AndroidLogger slogger;
        // protect against concurrent access of the loggerMap
        synchronized (this) {
            slogger = loggerMap.get(actualName);
            if (slogger == null) {
                if (!actualName.equals(name)) {
                    Log.i(AndroidLoggerFactory.class.getSimpleName(),
                            "LoggerImpl name '" + name + "' exceeds maximum length of " + mBuilder.maxFileNameLength +
                                    " characters, using '" + actualName + "' instead.");
                }
                int messageLength = mBuilder.bufferSize - mBuilder.maxHeaderLength;
                slogger = new AndroidLogger(actualName, messageLength, getLoggerList(actualName), mBuilder.showStackTrace, mBuilder.currentStack);
                executor.execute(slogger);
                loggerMap.put(actualName, slogger);
            }
        }
        return slogger;
    }

    private List<Appender> getLoggerList(String actualName) {
        String[] keyStringArray = actualName.split("_");
        List<Appender> loggerList = new ArrayList<>();
        loggerList.add(new AndroidAppender.Builder()
                .setActualName(actualName)
                .addInterceptor(mBuilder.interceptors)
                .create());

        if (!TextUtils.isEmpty(actualName) && keyStringArray.length > 0 && mBuilder.baseTagList.contains(keyStringArray[0])) {
            String localPath = mBuilder.logDirPath + File.separator + actualName + File.separator + actualName + "_" + mBuilder.mLastDataFormatTime + mBuilder.suffix;
            String bufferPath = mBuilder.bufferDirPath + File.separator + actualName + File.separator + actualName + ".logCache";
            FileOutTimeUtils.makeDirs(mBuilder.logDirPath + File.separator + actualName);
            FileOutTimeUtils.makeDirs(mBuilder.bufferDirPath + File.separator + actualName);

            FileAppender fileAppender = new FileAppender.Builder(bufferPath)
                    .setLogFilePath(localPath)
                    .setBufferSize(mBuilder.bufferSize)
                    .setCompress(mBuilder.compress)
                    .setFormatter(mBuilder.formatter != null ? mBuilder.formatter : new DateFileFormatter())
                    .addInterceptor(mBuilder.interceptors)
                    .create();
            loggerList.add(fileAppender);
        }
        return loggerList;
    }

    /**
     * Trim name in case it exceeds maximum length of mBuilder.maxFileNameLength characters.
     */
    public static String forceValidName(String name, int maxFileNameLength) {
        if (name != null && name.length() > maxFileNameLength) {
            final StringTokenizer st = new StringTokenizer(name, ".");
            // note that empty tokens are skipped, i.e., "aa..bb" has tokens "aa", "bb"
            if (st.hasMoreTokens()) {
                final StringBuilder sb = new StringBuilder();
                String token;
                do {
                    token = st.nextToken();
                    // token of one character appended as is
                    if (token.length() == 1) {
                        sb.append(token);
                        sb.append('.');
                        // truncate all but the last token
                    } else if (st.hasMoreTokens()) {
                        sb.append(token.charAt(0));
                        sb.append("*.");
                    } else // last token (usually class name) appended as is
                    {
                        sb.append(token);
                    }
                } while (st.hasMoreTokens());

                name = sb.toString();
            }

            // Either we had no useful dot location at all or name still too long.
            // Take leading part and append '*' to indicate that it was truncated
            if (name.length() > maxFileNameLength) {
                name = name.substring(0, maxFileNameLength - 1) + '*';
            }
        }
        return name;
    }

    public void release() {
        for (Map.Entry<String, AndroidLogger> entry : loggerMap.entrySet()) {
            AndroidLogger androidLogger = entry.getValue();
            androidLogger.flush();
            androidLogger.release();
        }
    }

    public void clearOverdueLog() {
        if (mBuilder != null) {
            mBuilder.clearOverdueLog();
        }
    }

    public static class Builder {
        private String bufferDirPath;
        private String logDirPath;
        private String mLastDataFormatTime;
        private int bufferSize = 4096;
        private int maxHeaderLength = 150;
        private int maxFileNameLength = 50;
        private int maxSaveDay = 7;
        private String pattern;
        private String suffix = ".log";
        private boolean compress;
        private List<String> baseTagList = new ArrayList<>();
        private boolean showStackTrace = true;
        private int currentStack = 4;
        private Formatter formatter;
        private List<Interceptor> interceptors = new ArrayList<>();

        public Builder setMaxFileNameLength(int maxFileNameLength) {
            this.maxFileNameLength = maxFileNameLength;
            return this;
        }

        public Builder setMaxHeaderLength(int maxHeaderLength) {
            this.maxHeaderLength = maxHeaderLength;
            return this;
        }

        public Builder setFormatter(Formatter formatter) {
            this.formatter = formatter;
            return this;
        }

        public Builder addInterceptor(List<Interceptor> interceptors) {
            if (interceptors != null && !interceptors.isEmpty()) {
                this.interceptors.addAll(interceptors);
            }
            return this;
        }

        public Builder addInterceptor(Interceptor interceptors) {
            if (interceptors != null) {
                this.interceptors.add(interceptors);
            }
            return this;
        }

        public Builder setCurrentStack(int currentStack) {
            this.currentStack = currentStack;
            return this;
        }

        public Builder setShowStackTrace(boolean showStackTrace) {
            this.showStackTrace = showStackTrace;
            return this;
        }

        public Builder addBaseTag(String baseTag) {
            this.baseTagList.add(baseTag);
            return this;
        }

        public Builder addBaseTag(String ...baseTag) {
            this.baseTagList.addAll(Arrays.asList(baseTag));
            return this;
        }

        public Builder addBaseTag(List<String> baseTagList) {
            this.baseTagList.addAll(baseTagList);
            return this;
        }

        public Builder setBufferDirPath(String bufferDirPath) {
            this.bufferDirPath = bufferDirPath;
            return this;
        }

        public Builder setLogDirPath(String logDirPath) {
            this.logDirPath = logDirPath;
            return this;
        }

        public Builder setBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public Builder setMaxSaveDay(int maxSaveDay) {
            this.maxSaveDay = maxSaveDay;
            return this;
        }

        public Builder setPattern(String pattern) {
            this.pattern = pattern;
            this.mLastDataFormatTime = new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date());
            return this;
        }

        public Builder setSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        public Builder setCompress(boolean compress) {
            this.compress = compress;
            return this;
        }

        public Builder clearOverdueLog() {
            FileOutTimeUtils.deleteAllOutOfDateAndNonDesignatedFiles(TAG, logDirPath, pattern, System.currentTimeMillis(), maxSaveDay, suffix);
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"bufferDirPath\":\"")
                    .append(bufferDirPath).append('\"');
            sb.append(",\"logDirPath\":\"")
                    .append(logDirPath).append('\"');
            sb.append(",\"mLastDataFormatTime\":\"")
                    .append(mLastDataFormatTime).append('\"');
            sb.append(",\"bufferSize\":")
                    .append(bufferSize);
            sb.append(",\"maxHeaderLength\":")
                    .append(maxHeaderLength);
            sb.append(",\"maxSaveDay\":")
                    .append(maxSaveDay);
            sb.append(",\"pattern\":\"")
                    .append(pattern).append('\"');
            sb.append(",\"suffix\":\"")
                    .append(suffix).append('\"');
            sb.append(",\"compress\":")
                    .append(compress);
            sb.append(",\"baseTagList\":\"")
                    .append(baseTagList).append('\"');
            sb.append(",\"showStackTrace\":")
                    .append(showStackTrace);
            sb.append(",\"currentStack\":")
                    .append(currentStack);
            sb.append(",\"formatter\":")
                    .append(formatter);
            sb.append(",\"interceptors\":")
                    .append(interceptors);
            sb.append('}');
            return sb.toString();
        }

        public AndroidLoggerFactory create() {
            if (logDirPath == null) {
                throw new IllegalArgumentException("logDirPath cannot be null");
            }

            if (pattern == null) {
                throw new IllegalArgumentException("pattern cannot be null");
            }

            if (suffix == null) {
                throw new IllegalArgumentException("suffix cannot be null");
            }

            if (maxHeaderLength > bufferSize) {
                throw new IllegalArgumentException("maxHeaderLength greater or  equalto bufferSize");
            }
            Log.w("AndroidLoggerFactory", "builder:" + toString());
            return new AndroidLoggerFactory(this);
        }
    }
}
