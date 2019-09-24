package org.slf4j.impl;

import org.slf4j.impl.logger.AndroidLoggerFactory;
import org.slf4j.impl.utils.FileUtils;

import java.io.File;

/**
 * @author pqpo
 * @date 2017/11/24
 */
public class LoggerInit {

    public static void init(File log) {
        String bufferPath = log.getAbsolutePath();
        String logPath = log.getAbsolutePath();

        FileUtils.makeDirs(bufferPath);
        FileUtils.makeDirs(logPath);
        AndroidLoggerFactory factory = new AndroidLoggerFactory.Builder()
                .setMaxSaveDay(1)
                .setBufferSize(1024 * 2)
                .setBufferDirPath(bufferPath)
                .setLogDirPath(logPath)
                .setPattern("yyyy-MM-dd HH:mm")
                .setSuffix("log")
                .create();
        factory.clearAllOutOfDateFiles();
        StaticLoggerBinder.getSingleton().setLoggerFactory(factory);
    }

}
