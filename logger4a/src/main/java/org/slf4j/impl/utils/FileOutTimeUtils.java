package org.slf4j.impl.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FileOutTimeUtils {
    private static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath();

    public static File getLogDir() {
        File root = new File(ROOT_PATH, "logger4h");
        Log.v("Files", "log mkdirs success:" + makeDirs(root.getPath()));
        File log = new File(root.getPath(), "logs");
        if (!log.exists()) {
            boolean success = log.mkdirs();
            Log.v("Files", "log mkdirs success:" + success);
        }
        return log;
    }

    /**
     * @param filePath 路径
     * @return 是否创建成功
     */
    public static boolean makeDirs(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
    }

    /**
     * 判断过期时间
     */
    public static Boolean isOutOfTime(long referenceTime, String fileNameTime, String datePattern, int keepDays) {
        SimpleDateFormat format = new SimpleDateFormat(datePattern, Locale.CHINESE);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(new Date(referenceTime));
            c2.setTime(format.parse(fileNameTime));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        c2.set(Calendar.DAY_OF_MONTH, c2.get(Calendar.DAY_OF_MONTH) + keepDays);
        return c1.compareTo(c2) > 0;
    }

    /**
     * 删除指定文件夹下所有过期文件
     */
    public static void deleteAllOutOfDateAndNonDesignatedFiles(String tag, String path, String logFileTimePattern,
                                                               long referenceTime, int keepDays, String suffix) {
        Log.i(tag, "deleteAllOutOfDateFiles path:" + path);
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp;
        for (String aTempList : tempList) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + aTempList);
            } else {
                temp = new File(path + File.separator + aTempList);
            }
            if (temp.isDirectory()) {
                deleteAllOutOfDateAndNonDesignatedFiles(tag, temp.getAbsolutePath(), logFileTimePattern, referenceTime, keepDays, suffix);
                continue;
            }

            String name = temp.getName();

            if (TextUtils.isEmpty(name) || !name.contains(".")) {
                deleteFile(tag, temp, "Non-designated file:");
                continue;
            }

            if (!name.endsWith(suffix)) {
                //keep cache files
                if (name.endsWith(".logCache")) {
                    continue;
                }
                deleteFile(tag, temp, "Non-designated '" + suffix + "' file:");
                continue;
            }


            String fileTimeName = temp.getName()
                    .replaceAll(temp.getParentFile().getName() + "_", "")
                    .replaceAll(suffix, "");
            Boolean isOutOfTime = isOutOfTime(referenceTime, fileTimeName, logFileTimePattern, keepDays);
            //是否可以删除
            if (isOutOfTime == null || isOutOfTime) {
                deleteFile(tag, temp, "oldFile:");
            }
        }
    }

    private static void deleteFile(String tag, File temp, String s) {
        if (temp.delete()) {
            Log.w(tag, s + temp.getAbsolutePath() + " delete successfully.");
        } else {
            Log.w(tag, s + temp.getAbsolutePath() + " delete failed.");
        }
    }
}

