package com.thinkgem.jeesite.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author duanshao
 * 
 */
public class PinyinUtils {

    private static HanyuPinyinOutputFormat format = null;

    static {
        format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    private static String char2Pinyin(char c) {
        String[] pinyin = null;
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
        }
        if (pinyin == null) {
            return null;
        }
        // 取第一个发音
        return pinyin[0];
    }

    public static String pinyin(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String p = null;
        boolean isChar = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            p = char2Pinyin(c);
            if (p != null && isChar) {
                sb.append(" ");
            }
            sb.append(p == null ? c : p);
            if (p == null) {
                isChar = true;
            } else {
                isChar = false;
                sb.append(" ");
            }
        }
        return StringUtils.trim(sb.toString());
    }

    public static void main(String[] args) {
        
        System.out.println(pinyin("广州市咕吱网络有限公司  goodd的 morning"));
    }
}