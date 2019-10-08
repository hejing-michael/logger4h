package org.slf4j.impl;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import org.slf4j.impl.logger.AndroidLoggerFactory;
import org.slf4j.impl.utils.FileOutTimeUtils;

import java.io.File;

public class LoggerInit {
    private static AndroidLoggerFactory mAndroidLoggerFactory;

    public static void init(Context context, File log) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED
                    || context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                throw new RuntimeException("Currently need read and write permissions, please grant read and write permissions.");
            }
        }

        String logPath = log.getAbsolutePath();

        FileOutTimeUtils.makeDirs(logPath);
        mAndroidLoggerFactory = new AndroidLoggerFactory.Builder()
                .setMaxSaveDay(1)
                .setBufferSize(1024 * 2)
                .setBufferDirPath(logPath)
                .setLogDirPath(logPath)
                .setPattern("yyyy-MM-dd_HH")
                .setSuffix(".log")
                .setCompress(false)
                .clearAllOutOfDateFiles()
                .create();
        StaticLoggerBinder.getSingleton().setLoggerFactory(mAndroidLoggerFactory);
    }


    public static void release() {
        if (mAndroidLoggerFactory != null) {
            mAndroidLoggerFactory.release();
        }
    }
}
