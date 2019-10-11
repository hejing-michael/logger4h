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
        for (MarkerIgnoringBase logger : loggerList) {
            if (logger.isTraceEnabled()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String)
     */
    @Override
    public void trace(final String msg) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.trace(message);
        }
    }


    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1) {
        String message = getStackMessage(showStackTrace, format(format, param1, null));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.trace(message);
        }
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1, final Object param2) {
        String message = getStackMessage(showStackTrace, format(format, param1, param2));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.trace(message);
        }
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(final String format, final Object[] argArray) {
        String message = getStackMessage(showStackTrace, format(format, argArray));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.trace(message);
        }
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(final String msg, final Throwable t) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.trace(message, t);
        }
    }

    /**
     * @see org.slf4j.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        for (MarkerIgnoringBase logger : loggerList) {
            if (logger.isDebugEnabled()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String)
     */
    @Override
    public void debug(final String msg) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.debug(message);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object arg1) {
        String message = getStackMessage(showStackTrace, format(format, arg1, null));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.debug(message);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object param1, final Object param2) {
        String message = getStackMessage(showStackTrace, format(format, param1, param2));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.debug(message);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(final String format, final Object[] argArray) {
        String message = getStackMessage(showStackTrace, format(format, argArray));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.debug(message);
        }
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(final String msg, final Throwable t) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.debug(message, t);
        }
    }

    /**
     * @see org.slf4j.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        for (MarkerIgnoringBase logger : loggerList) {
            if (logger.isInfoEnabled()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String)
     */
    @Override
    public void info(final String msg) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.info(message);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg) {
        String message = getStackMessage(showStackTrace, format(format, arg, null));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.info(message);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        String message = getStackMessage(showStackTrace, format(format, arg1, arg2));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.info(message);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(final String format, final Object[] argArray) {
        String message = getStackMessage(showStackTrace, format(format, argArray));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.info(message);
        }
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(final String msg, final Throwable t) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.info(message, t);
        }
    }

    /**
     * @see org.slf4j.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        for (MarkerIgnoringBase logger : loggerList) {
            if (logger.isWarnEnabled()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String)
     */
    @Override
    public void warn(final String msg) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.warn(message);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg) {
        String message = getStackMessage(showStackTrace, format(format, arg, null));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.warn(message);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        String message = getStackMessage(showStackTrace, format(format, arg1, arg2));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.warn(message);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(final String format, final Object[] argArray) {
        String message = getStackMessage(showStackTrace, format(format, argArray));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.warn(message);
        }
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(final String msg, final Throwable t) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.warn(message, t);
        }
    }

    /* @see org.slf4j.LoggerImpl#isErrorEnabled() */
    @Override
    public boolean isErrorEnabled() {
        for (MarkerIgnoringBase logger : loggerList) {
            if (logger.isErrorEnabled()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String)
     */
    @Override
    public void error(final String msg) {
        String message = getStackMessage(showStackTrace, msg);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.error(message);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg) {
        String message = getStackMessage(showStackTrace, format(format, arg, null));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.error(message);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        String message = getStackMessage(showStackTrace, format(format, arg1, arg2));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.error(message);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(final String format, final Object[] argArray) {
        String message = getStackMessage(showStackTrace, format(format, argArray));
        for (MarkerIgnoringBase logger : loggerList) {
            logger.error(message);
        }
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(final String msg, final Throwable t) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String header = LogHeaderMessageUtil.getFileName(stackTrace, 3)
                + " " + LogHeaderMessageUtil.getLineNumber(stackTrace, 3);
        for (MarkerIgnoringBase logger : loggerList) {
            logger.error(header + " " + msg, t);
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

    private String getStackMessage(boolean showStackTrace, String msg) {
        if (!showStackTrace) {
            return msg;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String fileName = LogHeaderMessageUtil.getFileName(stackTrace, currentStack);
        return (TextUtils.isEmpty(fileName) ? fileName : fileName.replaceAll(".java", ""))
                + " line：" + LogHeaderMessageUtil.getLineNumber(stackTrace, currentStack)
                + " thread：" + Thread.currentThread().getId()
                + " " + msg;
    }
}
