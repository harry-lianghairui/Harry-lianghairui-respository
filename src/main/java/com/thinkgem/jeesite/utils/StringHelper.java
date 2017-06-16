package com.thinkgem.jeesite.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author duanshao
 * 
 */
public class StringHelper {

    /**
     * 从str找出最匹配key的字符串
     * 
     * @param title
     * @param key
     * @return
     */
    public static String match(String str, String key) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(key)) {
            return null;
        }
        if (str.contains(key)) {
            return key;
        }
        if (key.length() < 2) {
            return null;
        }
        try {
            List<String> tokens = TokenSpliter.showToken(key);
            for (String t : tokens) {
                if (str.contains(t)) {
                    return t;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(match("我爱你", "的你的的的顶顶顶顶的订单的顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶"));
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(match("我爱你", "噢噢噢噢哦哦哦哦哦哦哦我爱你哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦"));
        System.out.println(System.currentTimeMillis() - start);
    }
}
