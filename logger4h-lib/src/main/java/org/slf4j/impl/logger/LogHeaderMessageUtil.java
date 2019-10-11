package org.slf4j.impl.logger;

public class LogHeaderMessageUtil {
    public static int getLineNumber() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[1].getLineNumber();
    }

    public static int getLineNumber(StackTraceElement[] stackTrace,int currentStack) {
        return stackTrace[currentStack].getLineNumber();
    }

    public static String getMethodName() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[1].getMethodName();
    }


    public static String getMethodName(StackTraceElement[] stackTrace,int currentStack) {
        return stackTrace[currentStack].getMethodName();
    }

    public static String getFileName() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[1].getFileName();
    }

    public static String getFileName(StackTraceElement[] stackTrace,int currentStack) {
        return stackTrace[currentStack].getFileName();
    }

    public static String getClassName() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[1].getClassName();
    }

    public static String getClassName(StackTraceElement[] stackTrace,int currentStack) {
        return stackTrace[currentStack].getClassName();
    }
}
