package org.slf4j.impl.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * ABNumberUtil 字符串中的数字处理
 *
 * @author Michael_hj
 * @date 2015/12/15
 */
public class ABNumberUtil {
    /**
     * 判断number参数是否是整型数或者浮点数表示方式
     *
     * @param number the mNumber
     * @return boolean
     */
    public static boolean isIntegerOrFloatNumber(String number) {
        return !TextUtils.isEmpty(number) && (isIntegerNumber(number)
                || isFloatPointNumber(number));
    }

    /**
     * 判断number参数是否是整型数表示方式
     *
     * @param number the mNumber
     * @return boolean
     */
    public static boolean isIntegerNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        number = number.trim();
        String intNumRegex = "\\-?\\d+";//整数的正则表达式
        return number.matches(intNumRegex);
    }

    /**
     * 判断number参数是否是浮点数表示方式
     *
     * @param number the mNumber
     * @return boolean
     */
    public static boolean isFloatPointNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        number = number.trim();
        String pointPrefix = "(\\-|\\+)?\\d*\\.\\d+";//浮点数的正则表达式-小数点在中间与前面
        String pointSuffix = "(\\-|\\+)?\\d+\\.";//浮点数的正则表达式-小数点在后面
        return number.matches(pointPrefix) || number.matches(pointSuffix);
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    public static boolean isUUID(String str) {
        Pattern pattern = Pattern.compile("/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/");
        return pattern.matcher(str).matches();
    }
    /**
     * 修剪浮点类型  规则(如:0.00保留2位小数)
     *
     * @param value the value
     * @param rules the rules
     * @return String
     */
    public static String getTrim(String value, String rules) {
        if (value == null || value.length() == 0 || rules == null || rules.length() == 0) {
            return "";
        }
        try {
            return getTrim(Double.parseDouble(value), rules);
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * 修剪浮点类型 规则(如:0.00保留2位小数)
     *
     * @param value the value
     * @param rules the rules
     * @return Trim
     */
    public static String getTrim(double value, String rules) {
        DecimalFormat df = new DecimalFormat(rules);
        return df.format(value);
    }

    /**
     * 判断奇、偶数
     *
     * @param number the mNumber
     * @return boolean
     */
    public static boolean isOdd(int number) {
        return (number & 1) == 0;
    }

    /**
     * 数据分页--获取总页数
     *
     * @param count    总条数
     * @param pageSize 每页数据长度
     * @return : totalPage
     */
    public static int getTotalPage(int count, int pageSize) {
        int totalPage;
        if (count <= pageSize) {
            totalPage = 1;
        } else {
            totalPage = count / pageSize;//赋值
            if (pageSize != 0 && count % pageSize > 0) {
                totalPage++;//实际为totalPage=totalPage+1
            }
        }
        return totalPage;
    }

}
