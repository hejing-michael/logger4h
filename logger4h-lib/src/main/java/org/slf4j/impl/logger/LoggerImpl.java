package org.slf4j.impl.logger;


import android.util.Log;

import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.impl.appender.Appender;

/**
 * @author hejing
 */
public class LoggerImpl extends MarkerIgnoringBase implements Appender {
    private static final long serialVersionUID = -1227274521521287937L;
    private Appender mAppender;

    /**
     * Package access allows only {@link AndroidLoggerFactory} to instantiate
     * SimpleLogger instances.
     */
    LoggerImpl(final String name, Appender appender) {
        this.name = name;
        this.mAppender = appender;
    }

    /**
     * @see org.slf4j.Logger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled() {
        return Log.isLoggable(name, Log.VERBOSE);
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String)
     */
    @Override
    public void trace(final String msg) {
        mAppender.append(Level.TRACE, name, msg);
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String)
     */
    public void trace(String tag, String msg) {
        mAppender.append(Level.TRACE, name + " " + tag, msg);
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1) {
        mAppender.append(Level.TRACE, name, format(format, param1, null));
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1, final Object param2) {
        mAppender.append(Level.TRACE, name, format(format, param1, param2));
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(final String format, final Object[] argArray) {
        mAppender.append(Level.TRACE, name, format(format, argArray));
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(final String msg, final Throwable t) {
        mAppender.append(Level.TRACE, name, msg + ":\n" + Log.getStackTraceString(t));
    }

    /**
     * @see org.slf4j.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return Log.isLoggable(name, Log.DEBUG);
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String)
     */
    @Override
    public void debug(final String msg) {
        mAppender.append(Level.DEBUG, name, msg);
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String)
     */
    public void debug(String tag, String msg) {
        mAppender.append(Level.DEBUG, name + " " + tag, msg);
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object arg1) {
        mAppender.append(Level.DEBUG, name, format(format, arg1, null));
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object param1, final Object param2) {
        mAppender.append(Level.DEBUG, name, format(format, param1, param2));
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(final String format, final Object[] argArray) {
        mAppender.append(Level.DEBUG, name, format(format, argArray));
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(final String msg, final Throwable t) {
        mAppender.append(Level.DEBUG, name, msg + ":\n" + Log.getStackTraceString(t));
    }

    /**
     * @see org.slf4j.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return Log.isLoggable(name, Log.INFO);
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String)
     */
    @Override
    public void info(final String msg) {
        mAppender.append(Level.INFO, name, msg);
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String)
     */
    public void info(String tag, final String msg) {
        mAppender.append(Level.INFO, name + " " + tag, msg);
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg) {
        mAppender.append(Level.INFO, name, format(format, arg, null));
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        mAppender.append(Level.INFO, name, format(format, arg1, arg2));
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(final String format, final Object[] argArray) {
        mAppender.append(Level.INFO, name, format(format, argArray));
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(final String msg, final Throwable t) {
        mAppender.append(Level.INFO, name, msg + ":\n" + Log.getStackTraceString(t));
    }

    /**
     * @see org.slf4j.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return Log.isLoggable(name, Log.WARN);
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String)
     */
    @Override
    public void warn(final String msg) {
        mAppender.append(Level.WARN, name, msg);
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String)
     */
    public void warn(String tag, final String msg) {
        mAppender.append(Level.WARN, name + " " + tag, msg);
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg) {
        mAppender.append(Level.WARN, name, format(format, arg, null));
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        mAppender.append(Level.WARN, name, format(format, arg1, arg2));
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(final String format, final Object[] argArray) {
        mAppender.append(Level.WARN, name, format(format, argArray));
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(final String msg, final Throwable t) {
        mAppender.append(Level.WARN, name, msg + ":\n" + Log.getStackTraceString(t));
    }

    /* @see org.slf4j.LoggerImpl#isErrorEnabled() */
    @Override
    public boolean isErrorEnabled() {
        return Log.isLoggable(name, Log.ERROR);
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String)
     */
    @Override
    public void error(final String msg) {
        mAppender.append(Level.ERROR, name, msg);
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String)
     */
    public void error(String tag, String msg) {
        mAppender.append(Level.ERROR, name + " " + tag, msg);
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg) {
        mAppender.append(Level.ERROR, name, format(format, arg, null));
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        mAppender.append(Level.ERROR, name, format(format, arg1, arg2));
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(final String format, final Object[] argArray) {
        mAppender.append(Level.ERROR, name, format(format, argArray));
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(final String msg, final Throwable t) {
        mAppender.append(Level.ERROR, name, msg + ":\n" + Log.getStackTraceString(t));
    }

    /**
     * For formatted messages substitute arguments.
     *
     * @param format
     * @param arg1
     * @param arg2
     */
    private String format(final String format, final Object arg1, final Object arg2) {
        return MessageFormatter.format(format, arg1, arg2).getMessage();
    }

    /**
     * For formatted messages substitute arguments.
     *
     * @param format
     * @param args
     */
    private String format(final String format, final Object[] args) {
        return MessageFormatter.arrayFormat(format, args).getMessage();
    }

    @Override
    public void append(Level logLevel, String tag, String msg) {
        if (mAppender != null) {
            mAppender.append(logLevel, tag, msg);
        }
    }

    @Override
    public void flush() {
        if (mAppender != null) {
            mAppender.flush();
        }
    }

    @Override
    public void release() {
        if (mAppender != null) {
            mAppender.release();
        }
    }
}
