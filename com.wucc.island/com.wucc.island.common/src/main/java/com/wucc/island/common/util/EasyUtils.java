package com.wucc.island.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/* 基于参数是Object的String工具类 */
public class EasyUtils {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String DatetoString(Date date, SimpleDateFormat simpleDateFormat) {
        if (date == null)
            return "";

        return simpleDateFormat.format(date);
    }

    public static Boolean isBlank(Object obj) {
        return obj == null ? true : "".equals(obj.toString());
    }

    public static Boolean isNotBlank(Object obj) {
        return obj == null ? false : !"".equals(obj.toString());
    }

    public static Boolean isEmpty(Object obj) {
        return obj == null ? true : StringUtils.isEmpty(obj.toString());
    }

    public static Boolean isNotEmpty(Object obj) {
        return obj == null ? false : StringUtils.isNotEmpty(obj.toString());
    }

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 驼峰转下划线 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 下划线转get方法 */
    public static String lineToGetMethod(String str) {
        return lineToHump("get_" + str);
    }

    /**
     * 动态sql拼接
     *
     * @param ids
     *            字段值集合
     * @param field
     *            字段
     * @param batchCount
     *            批数
     * @return
     */
    public static String getDynamicSql(List<String> ids, String field, int batchCount) {
        StringBuffer sb = new StringBuffer();
        sb.append(" and (");
        int size = ids.size();
        int time = ids.size() / batchCount;
        for (int i = 0; i < time; i++) {
            if (i > 0)
                sb.append(" or");
            sb.append(" (" + field + " in (");
            int next = i * batchCount;
            for (int j = next; j < next + batchCount; j++) {
                sb.append("'").append(ids.get(j)).append("',");
            }
            sb.append("'-1'))");
        }
        if (size % batchCount != 0) {
            if (time > 0)
                sb.append(" or");
            sb.append(" (" + field + " in (");
            for (int i = time * batchCount; i < size; i++) {
                sb.append("'").append(ids.get(i)).append("',");
            }
            sb.append("'-1'))");
        }
        sb.append(")");
        String s = sb.toString();
        return s;
    }

    /**
     * 将List<Map<String, Object>> 转为 List<Map<String, String>>
     *
     * @param list
     *            List<Map<String, String>>
     * @author zengyjb
     * @date 12/28/20
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     */
    public static List<Map<String, String>> convertListMapToString(List<Map<String, Object>> list) {
        return list.parallelStream()
            .map(map -> map.entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toString())))
            .collect(Collectors.toList());
    }

    /**
     * 校验字符串是否符合日期格式
     *
     * @param date
     *            字符串日期
     * @author zengyjb
     * @since 1/12/21
     * @return boolean
     */
    public static boolean checkDateFormat(String date) {
        String regular = "^((?:18|19|20|21)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
        String temp = date.replaceAll("/", "-");
        return temp.matches(regular);
    }
}
