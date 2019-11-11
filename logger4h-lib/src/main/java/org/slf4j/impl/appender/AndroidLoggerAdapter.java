/*
 * Copyright (c) 2004-2013 QOS.ch
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
 *
 */
package org.slf4j.impl.appender;

import android.util.Log;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

/**
 * @author hejin
 */
public class AndroidLoggerAdapter extends MarkerIgnoringBase {
    private static final long serialVersionUID = -1227274521521287937L;

    /**
     * Package access allows only {@link AndroidLoggerAdapter} to instantiate
     * SimpleLogger instances.
     */
    public AndroidLoggerAdapter(String tag) {
        this.name = tag;
    }

    /**
     * Is this logger instance enabled for the VERBOSE level?
     *
     * @return True if this Logger is enabled for level VERBOSE, false otherwise.
     */
    @Override
    public boolean isTraceEnabled() {
        return isLoggable(Log.VERBOSE);
    }

    /**
     * Log a message object at level VERBOSE.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void trace(String msg) {
        log(Log.VERBOSE, msg, null);
    }

    /**
     * Log a message at level VERBOSE according to the specified format and
     * argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level VERBOSE.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void trace(String format, Object arg) {
        formatAndLog(Log.VERBOSE, format, arg);
    }

    /**
     * Log a message at level VERBOSE according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the VERBOSE level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void trace(String format, Object arg1, Object arg2) {
        formatAndLog(Log.VERBOSE, format, arg1, arg2);
    }

    /**
     * Log a message at level VERBOSE according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the VERBOSE level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void trace(String format, Object... argArray) {
        formatAndLog(Log.VERBOSE, format, argArray);
    }

    /**
     * Log an exception (throwable) at level VERBOSE with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void trace(String msg, Throwable t) {
        log(Log.VERBOSE, msg, t);
    }

    /**
     * Is this logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for level DEBUG, false otherwise.
     */
    @Override
    public boolean isDebugEnabled() {
        return isLoggable(Log.DEBUG);
    }

    /**
     * Log a message object at level DEBUG.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void debug(String msg) {
        log(Log.DEBUG, msg, null);
    }

    /**
     * Log a message at level DEBUG according to the specified format and argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level DEBUG.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void debug(String format, Object arg) {
        formatAndLog(Log.DEBUG, format, arg);
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void debug(String format, Object arg1, Object arg2) {
        formatAndLog(Log.DEBUG, format, arg1, arg2);
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void debug(String format, Object... argArray) {
        formatAndLog(Log.DEBUG, format, argArray);
    }

    /**
     * Log an exception (throwable) at level DEBUG with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void debug(String msg, Throwable t) {
        log(Log.VERBOSE, msg, t);
    }

    /**
     * Is this logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false otherwise.
     */
    @Override
    public boolean isInfoEnabled() {
        return isLoggable(Log.INFO);
    }

    /**
     * Log a message object at the INFO level.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void info(String msg) {
        log(Log.INFO, msg, null);
    }

    /**
     * Log a message at level INFO according to the specified format and argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void info(String format, Object arg) {
        formatAndLog(Log.INFO, format, arg);
    }

    /**
     * Log a message at the INFO level according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void info(String format, Object arg1, Object arg2) {
        formatAndLog(Log.INFO, format, arg1, arg2);
    }

    /**
     * Log a message at level INFO according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void info(String format, Object... argArray) {
        formatAndLog(Log.INFO, format, argArray);
    }

    /**
     * Log an exception (throwable) at the INFO level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void info(String msg, Throwable t) {
        log(Log.INFO, msg, t);
    }

    /**
     * Is this logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level, false
     * otherwise.
     */
    @Override
    public boolean isWarnEnabled() {
        return isLoggable(Log.WARN);
    }

    /**
     * Log a message object at the WARN level.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void warn(String msg) {
        log(Log.WARN, msg, null);
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void warn(String format, Object arg) {
        formatAndLog(Log.WARN, format, arg);
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void warn(String format, Object arg1, Object arg2) {
        formatAndLog(Log.WARN, format, arg1, arg2);
    }

    /**
     * Log a message at level WARN according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void warn(String format, Object... argArray) {
        formatAndLog(Log.WARN, format, argArray);
    }

    /**
     * Log an exception (throwable) at the WARN level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void warn(String msg, Throwable t) {
        log(Log.WARN, msg, t);
    }

    /**
     * Is this logger instance enabled for level ERROR?
     *
     * @return True if this Logger is enabled for level ERROR, false otherwise.
     */
    @Override
    public boolean isErrorEnabled() {
        return isLoggable(Log.ERROR);
    }

    /**
     * Log a message object at the ERROR level.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void error(String msg) {
        log(Log.ERROR, msg, null);
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void error(String format, Object arg) {
        formatAndLog(Log.ERROR, format, arg);
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void error(String format, Object arg1, Object arg2) {
        formatAndLog(Log.ERROR, format, arg1, arg2);
    }

    /**
     * Log a message at level ERROR according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void error(String format, Object... argArray) {
        formatAndLog(Log.ERROR, format, argArray);
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void error(String msg, Throwable t) {
        log(Log.ERROR, msg, t);
    }

    private void formatAndLog(int priority, String format, Object... argArray) {
        if (isLoggable(priority)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logInternal(priority, ft.getMessage(), ft.getThrowable());
        }
    }

    private void log(int priority, String message, Throwable throwable) {
        if (isLoggable(priority)) {
            logInternal(priority, message, throwable);
        }
    }

    private boolean isLoggable(int priority) {
        return Log.isLoggable(name, priority);
    }

    private void logInternal(int priority, String message, Throwable throwable) {
        if (throwable != null) {
            message += '\n' + Log.getStackTraceString(throwable);
        }
        Log.println(priority, name, message);
    }
}