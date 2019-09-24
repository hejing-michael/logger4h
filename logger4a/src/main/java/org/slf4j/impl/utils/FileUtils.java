package org.slf4j.impl.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pqpo on 2017/11/21.
 */

public class FileUtils {
    private static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/log4a/";

    public static File getLogDir() {
        File log = new File(ROOT_PATH, "logs");
        if (!log.exists()) {
            log.mkdirs();
        }
        return log;
    }

    public static File getLogDir(Context context) {
        File log = context.getExternalFilesDir("logs");
        if (log == null) {
            log = new File(context.getFilesDir(), "logs");
        }
        if (!log.exists()) {
            log.mkdir();
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
     * 删除指定文件夹下所有过期文件
     */
    public static void deleteAllOutOfDateFiles(String tag, String path, String pattern,
                                               long referenceTime, int maxSaveDay, String suffix) {
        Log.i(tag, "deleteAllOutOfDateFiles path:" + path);
        DateFormat format = new SimpleDateFormat(pattern, Locale.CHINESE);

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
                deleteAllOutOfDateFiles(tag, temp.getAbsolutePath(), pattern, referenceTime, maxSaveDay, suffix);
                continue;
            }

            String name = temp.getName();

            if (TextUtils.isEmpty(name) || !name.contains(".") || !name.endsWith(suffix)) {
                continue;
            }

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(new Date(referenceTime));
            String fileNameTime = name.substring(0, name.lastIndexOf("."));
            try {
                c2.setTime(format.parse(fileNameTime));
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }
            c2.set(Calendar.DAY_OF_MONTH, c2.get(Calendar.DAY_OF_MONTH) + maxSaveDay);
            //是否可以删除
            if (c1.compareTo(c2) > 0) {
                if (temp.delete()) {
                    Log.w(tag, "oldFile:" + temp.getAbsolutePath() + " delete successfully.");
                } else {
                    Log.w(tag, "oldFile:" + temp.getAbsolutePath() + " delete failed.");
                }
            }
        }
    }
}
