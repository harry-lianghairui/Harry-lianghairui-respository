package com.thinkgem.jeesite.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class KeywordFilter {
    private static Pattern pattern = null;

    public static void initPattern() {
        StringBuffer patternBuf = new StringBuffer("");
        try {
            InputStream in = KeywordFilter.class.getClassLoader().getResourceAsStream("filter.txt");
            Properties pro = new Properties();
            pro.load(in);
            Enumeration enu = pro.propertyNames();
            while (enu.hasMoreElements()) {
                patternBuf.append((String) enu.nextElement() + "|");
            }
            patternBuf.deleteCharAt(patternBuf.length() - 1);
            pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO8859-1"), "UTF-8"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public static String doFilter(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        if (pattern == null)
            initPattern();
        try {
            Matcher m = pattern.matcher(str);
            str = m.replaceAll("**");
        } catch (Exception e) {
        }
        return str;
    }

    public static String getKeyWord(String str) {
        String result = "";
        if (pattern == null)
            initPattern();
        try {
            Matcher m = pattern.matcher(str);
            while (m.find()) {
                result += "," + m.group();
            }
        } catch (Exception e) {
        }
        if (result.length() > 0)
            result = result.substring(1);
        return result;
    }

    public static void main(String[] args) {
        String str = "aaaaaaaa醉乙醚ddd";
        initPattern();
        try {
            System.out.println(KeywordFilter.doFilter(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
