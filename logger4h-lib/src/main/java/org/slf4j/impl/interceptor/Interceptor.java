package org.slf4j.impl.interceptor;


/**
 * @author hejing
 */
public interface Interceptor {
    boolean intercept(LogData logData);
}
