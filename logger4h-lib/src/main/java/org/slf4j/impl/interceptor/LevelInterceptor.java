package org.slf4j.impl.interceptor;

import org.slf4j.event.Level;

/**
 * @author hejing
 */
public class LevelInterceptor implements Interceptor {

    private Level level = Level.TRACE;

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean intercept(Level logLevel, String tag, String msg) {
        return logLevel != null && logLevel.toInt() < level.toInt();
    }
}
