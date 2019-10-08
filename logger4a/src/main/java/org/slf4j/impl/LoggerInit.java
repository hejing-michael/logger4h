package org.slf4j.impl;

import org.slf4j.impl.logger.AndroidLoggerFactory;
import org.slf4j.impl.utils.FileOutTimeUtils;

import java.io.File;

public class LoggerInit {

    public static void init(File log) {
        String logPath = log.getAbsolutePath();

        FileOutTimeUtils.makeDirs(logPath);
        StaticLoggerBinder.getSingleton().setLoggerFactory(new AndroidLoggerFactory.Builder()
                .setMaxSaveDay(1)
                .setBufferSize(1024 * 2)
                .setBufferDirPath(logPath)
                .setLogDirPath(logPath)
                .setPattern("yyyy-MM-dd_HH")
                .setSuffix(".log")
                .setCompress(false)
                .clearAllOutOfDateFiles()
                .create());
    }
}
