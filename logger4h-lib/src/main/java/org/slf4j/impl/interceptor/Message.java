package org.slf4j.impl.interceptor;

import org.slf4j.event.Level;

public class Message {
    private Level logLevel;
    private String tag;
    private String msg;
    private boolean breakThread = false;

    public Message(boolean breakThread) {
        this.breakThread = breakThread;
    }

    public Message(Level logLevel, String tag, String msg) {
        this.logLevel = logLevel;
        this.tag = tag;
        this.msg = msg;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public String getTag() {
        return tag;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "{" + "\"logLevel\":" +
                logLevel +
                ",\"tag\":\"" +
                tag + '\"' +
                ",\"msg\":\"" +
                msg + '\"' +
                '}';
    }

    public boolean isBreak() {
        return breakThread;
    }
}
