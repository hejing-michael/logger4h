package com.slf4j.logger;

import android.app.Application;

import org.slf4j.impl.LoggerInit;
import org.slf4j.impl.utils.FileOutTimeUtils;

/**
 * @author hejing
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerInit.init(this, FileOutTimeUtils.getLogDir().getAbsolutePath());
    }
}
