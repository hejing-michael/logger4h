package org.slf4j.impl.formatter;

import org.slf4j.event.Level;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author hejing
 */
public class DateFileFormatter implements Formatter {
    private SimpleDateFormat simpleDateFormat;
    private Date mDate;
    private String lastDataFormated = null;
    private StringBuffer mStringBuffer;
    private int mTimeLength = 0;

    public DateFileFormatter() {
        this("yyyy-MM-dd HH:mm:ss:SSS");
    }

    public DateFileFormatter(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        mStringBuffer = new StringBuffer();
        //重置秒数
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MILLISECOND, 0);
        mDate = instance.getTime();
    }

    @Override
    public synchronized String format(Level logLevel, String tag, String msg) {
        if ((System.currentTimeMillis() - mDate.getTime()) > 1000 || lastDataFormated == null) {
            mDate.setTime(System.currentTimeMillis());
            lastDataFormated = simpleDateFormat.format(mDate);
            resetTimePrefix();
            return formatString(logLevel, tag, msg);
        }
        return formatString(logLevel, tag, msg);
    }

    private void resetTimePrefix() {
        if (mStringBuffer.length() > 0) {
            mStringBuffer.delete(0, mStringBuffer.length());
        }
        mTimeLength = mStringBuffer.append(lastDataFormated).append(' ').length();
    }

    public String formatString(Level logLevel, String tag, String msg) {
        if (mStringBuffer.length() > mTimeLength) {
            mStringBuffer.delete(mTimeLength, mStringBuffer.length());
        }
        return mStringBuffer.append(logLevel.toString()).append(" ").append(tag).append(" ").append(msg).append('\n').toString();
    }
}