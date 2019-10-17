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

import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.impl.appender.Appender;

import java.util.List;


/**
 * @author hejing
 */
public class AndroidLogger extends MarkerIgnoringBase implements Appender {
    private static final long serialVersionUID = -1227274521521287937L;
    private List<LoggerImpl> loggerList;
    private boolean showStackTrace;
    private int currentStack;

    /**
     * Package access allows only {@link AndroidLoggerFactory} to instantiate
     * SimpleLogger instances.
     */
    AndroidLogger(final String name, List<LoggerImpl> loggerList,
                  boolean showStackTrace, int currentStack) {
        this.name = name;
        this.loggerList = loggerList;
        this.showStackTrace = showStackTrace;
        this.currentStack = currentStack;
    }

    /**
     * @see org.slf4j.Logger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String)
     */
    @Override
    public void trace(final String msg) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.TRACE, tag, msg);
        }
    }


    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, param1, null);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.TRACE, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1, final Object param2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, param1, param2);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.TRACE, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.TRACE, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.TRACE, tag, msg + " " + Log.getStackTraceString(t));
        }
    }

    /**
     * @see org.slf4j.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String)
     */
    @Override
    public void debug(final String msg) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.DEBUG, tag, msg);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object arg1) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, null);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.DEBUG, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object param1, final Object param2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, param1, param2);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.DEBUG, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.DEBUG, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.DEBUG, tag, msg + " " + Log.getStackTraceString(t));
        }
    }

    /**
     * @see org.slf4j.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String)
     */
    @Override
    public void info(final String msg) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.INFO, tag, msg);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg, null);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.INFO, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, arg2);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.INFO, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.INFO, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.INFO, tag, msg + " " + Log.getStackTraceString(t));
        }
    }

    /**
     * @see org.slf4j.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String)
     */
    @Override
    public void warn(final String msg) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.WARN, tag, msg);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg, null);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.WARN, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, arg2);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.WARN, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.WARN, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.WARN, tag, msg + " " + Log.getStackTraceString(t));
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String)
     */
    @Override
    public void error(final String msg) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.ERROR, tag, msg);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg, null);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.ERROR, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, arg2);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.ERROR, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.ERROR, tag, message);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        for (LoggerImpl logger : loggerList) {
            logger.append(Level.ERROR, tag, msg + " " + Log.getStackTraceString(t));
        }
    }

    /**
     * For formatted messages substitute arguments.
     */
    private String format(final String format, final Object arg1, final Object arg2) {
        return MessageFormatter.format(format, arg1, arg2).getMessage();
    }

    /**
     * For formatted messages substitute arguments.
     */
    private String format(final String format, final Object[] args) {
        return MessageFormatter.arrayFormat(format, args).getMessage();
    }

    @Override
    public void append(Level logLevel, String tag, String msg) {
        for (Appender appender : loggerList) {
            appender.append(logLevel, tag, msg);
        }
    }

    @Override
    public void flush() {
        for (Appender appender : loggerList) {
            appender.flush();
        }
    }

    @Override
    public void release() {
        for (Appender appender : loggerList) {
            appender.release();
        }
    }

    private String getStackTag(boolean showStackTrace) {
        if (!showStackTrace) {
            return "";
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = LogHeaderMessageUtil.getFileName(stackTrace, currentStack);
        return "[" + (TextUtils.isEmpty(fileName) ? fileName : fileName.replaceAll(".java", ""))
                + " line：" + LogHeaderMessageUtil.getLineNumber(stackTrace, currentStack)
                + " thread：" + Thread.currentThread().getId()
                + "] ";
    }
}
