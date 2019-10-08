package org.slf4j.impl.interceptor;


import org.slf4j.event.Level;

/**
 * @author hejing
 */
public class LogData {
    private static final int MAX_POOL_SIZE = 50;

    private final Object sPoolSync = new Object();
    public Level logLevel;
    public String tag;
    public String msg;
    private LogData next;
    private LogData sPool;
    private int sPoolSize = 0;

    public LogData obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                LogData m = sPool;
                sPool = m.next;
                m.next = null;
                sPoolSize--;
                return m;
            }
        }
        return new LogData();
    }

    public LogData obtain(Level logLevel, String tag, String msg) {
        LogData obtain = obtain();
        obtain.logLevel = logLevel;
        obtain.tag = tag;
        obtain.msg = msg;
        return obtain;
    }

    public void recycle() {
        logLevel = Level.TRACE;
        tag = null;
        msg = null;

        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }

}
