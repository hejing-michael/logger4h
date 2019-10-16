package org.slf4j.impl.formatter;

import org.slf4j.event.Level;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author hejing
 */
public class DateFileFormatter implements Formatter {
    private final SimpleDateFormat mSimpleDateFormat;
    private final StringBuffer mStringBuffer;

    public DateFileFormatter() {
        this("yyyy-MM-dd HH:mm:ss:SSS");
    }

    public DateFileFormatter(String pattern) {
        mSimpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        mStringBuffer = new StringBuffer();
    }

    @Override
    public synchronized String format(Level logLevel, String tag, String msg) {
        if (mStringBuffer.length() > 0) {
            mStringBuffer.delete(0, mStringBuffer.length());
        }

        return mStringBuffer
                .append('\n')
                .append(mSimpleDateFormat.format(System.currentTimeMillis()))
                .append(' ')
                .append(logLevel.toString())
                .append(" ")
                .append(tag)
                .append(" ")
                .append(msg)
                .toString();
    }
}