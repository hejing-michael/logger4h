package org.slf4j.impl.interceptor;


import org.slf4j.event.Level;

/**
 * @author hejing
 */
public interface Interceptor {
    boolean intercept(Level logLevel, String tag, String msg);
}
