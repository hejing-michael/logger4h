package com.slf4j.logger;

import android.app.Application;

import org.slf4j.impl.LoggerInit;
import org.slf4j.impl.utils.FileUtils;

/**
 * @author hejing
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerInit.init(FileUtils.getLogDir());
    }
}
