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
import org.slf4j.impl.interceptor.Message;
import org.slf4j.impl.utils.StringUtil;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * @author hejing
 */
public class AndroidLogger extends MarkerIgnoringBase implements Appender, Runnable {
    private static final long serialVersionUID = -1227274521521287937L;
    private final int maxSingleLength;
    private List<Appender> loggerList;
    private boolean showStackTrace;
    private int currentStack;
    private BlockingDeque<Message> mBlockingQueue;

    /**
     * Package access allows only {@link AndroidLoggerFactory} to instantiate
     * SimpleLogger instances.
     */
    AndroidLogger(final String name, int maxSingleLength, List<Appender> loggerList,
                  boolean showStackTrace, int currentStack) {
        this.name = name;
        this.maxSingleLength = maxSingleLength;
        this.loggerList = loggerList;
        this.showStackTrace = showStackTrace;
        this.currentStack = currentStack;
        this.mBlockingQueue = new LinkedBlockingDeque<>();
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\hejin\\Desktop\\test.txt";
        String msg = StringUtil.readToString(path);
        int maxSingleLength = 1024 * 2;
        if (msg.length() <= maxSingleLength) {
            System.out.println(msg);
            return;
        }
        int msgLength = msg.length();
        int start = 0;
        int end = start + maxSingleLength;
        while (start < msgLength) {
            System.out.println(msg.substring(start, end));
            start = end;
            end = Math.min(start + maxSingleLength, msgLength);
        }
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
        putMessage(new Message(Level.TRACE, tag, msg));
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, param1, null);
        putMessage(new Message(Level.TRACE, tag, message));
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(final String format, final Object param1, final Object param2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, param1, param2);
        putMessage(new Message(Level.TRACE, tag, message));
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        putMessage(new Message(Level.TRACE, tag, message));
    }

    /**
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        putMessage(new Message(Level.TRACE, tag, msg + " " + Log.getStackTraceString(t)));
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
        putMessage(new Message(Level.DEBUG, tag, msg));
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object arg1) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, null);
        putMessage(new Message(Level.DEBUG, tag, message));
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(final String format, final Object param1, final Object param2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, param1, param2);
        putMessage(new Message(Level.DEBUG, tag, message));
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        putMessage(new Message(Level.DEBUG, tag, message));
    }

    /**
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        putMessage(new Message(Level.DEBUG, tag, msg + " " + Log.getStackTraceString(t)));
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
        putMessage(new Message(Level.INFO, tag, msg));
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg, null);
        putMessage(new Message(Level.INFO, tag, message));
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, arg2);
        putMessage(new Message(Level.INFO, tag, message));
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        putMessage(new Message(Level.INFO, tag, message));
    }

    /**
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        putMessage(new Message(Level.INFO, tag, msg + " " + Log.getStackTraceString(t)));
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
        putMessage(new Message(Level.WARN, tag, msg));
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg, null);
        putMessage(new Message(Level.WARN, tag, message));
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, arg2);
        putMessage(new Message(Level.WARN, tag, message));
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        putMessage(new Message(Level.WARN, tag, message));
    }

    /**
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        putMessage(new Message(Level.WARN, tag, msg + " " + Log.getStackTraceString(t)));
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
        putMessage(new Message(Level.ERROR, tag, msg));
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg, null);
        putMessage(new Message(Level.ERROR, tag, message));
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, arg1, arg2);
        putMessage(new Message(Level.ERROR, tag, message));
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(final String format, final Object[] argArray) {
        String tag = getStackTag(showStackTrace);
        String message = format(format, argArray);
        putMessage(new Message(Level.ERROR, tag, message));
    }

    /**
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(final String msg, final Throwable t) {
        String tag = getStackTag(showStackTrace);
        putMessage(new Message(Level.ERROR, tag, msg + " " + Log.getStackTraceString(t)));
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

    private void putMessage(Message message) {
        try {
            mBlockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            Message message = null;
            try {
                message = mBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (message == null) {
                continue;
            }

            if (message.isBreak()) {
                break;
            }

            appendInner(message.getLogLevel(), message.getTag(), message.getMsg(), maxSingleLength);
        }
    }

    private void splitMessage(Level logLevel, String tag, String msg, int maxMessageLength) {
        String tempRemaining = msg;
        while (!StringUtil.isEmpty(tempRemaining)) {
            if (tempRemaining.length() <= maxMessageLength) {
                append(logLevel, tag, tempRemaining);
                break;
            }

            String current = tempRemaining.substring(0, maxMessageLength);
            tempRemaining = tempRemaining.substring(maxMessageLength);
            append(logLevel, tag, current);
        }
    }

    private void appendInner(Level logLevel, String tag, String msg, int maxSingleLength) {
        if (msg.length() <= maxSingleLength) {
            append(logLevel, tag, msg);
            return;
        }
        int msgLength = msg.length();
        int start = 0;
        int end = start + maxSingleLength;
        while (start < msgLength) {
            append(logLevel, tag, msg.substring(start, end));
            start = end;
            end = Math.min(start + maxSingleLength, msgLength);
        }
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
        putMessage(new Message(true));
        for (Appender appender : loggerList) {
            appender.release();
        }
    }
}
