/**
 * Copyright © 2010 duowan.com [多玩游戏]
 */
package com.thinkgem.jeesite.utils;

/**
 * 列举我们关系的HttpHeader的值.
 * 
 * @author jiangnan@chinaduo.com
 * 
 */
public interface HttpHeaderConstants {
    /**
     * Cache控制
     */
    static final String CACHE_CONTROL = "Cache-Control";
    /**
     * 缓存控制
     */
    static final String PRAGMA = "Pragma";
    
    static final String NO_CACHE = "no-cache";
    /**
     * 用户代理
     */
    static final String USER_AGENT = "User-Agent";
    /**
     * 响应内容过期时间
     */
    static final String EXPIRES = "Expires";

    /**
     * 请求响应日期
     */
    static final String DATE = "Date";
    /**
     * 连接设置
     */
    static final String CONNECTION = "Connection";
    /**
     * 处理完当前请求后关闭连接
     */
    static final String CONNECTION_VAL_CLOSE = "close";

    /**
     * XML数据类型header值
     */
    static final String CONTENT_TYPE_VAL_XML = "text/xml; charset=utf-8";

    /**
     * 不适用缓存
     */
    static final String CACHE_CONTROL_VAL_NO_CACHE = "max-age=1, no-cache";
    /**
     * 重定向IP
     */
    static final String X_FORWARDED_FOR = "X-Forwarded-For";
    /**
     * Context-Type系列
     */
    static final String CONTENT_TYPE_JSON = "application/json";
    static final String CONTENT_TYPE_JAVASCRIPT = "application/javascript";
    static final String CONTENT_TYPE_TEXT = "text/plain";
    static final String CONTENT_TYPE_HTML = "text/html";
    static final String CONTENT_TYPE_XML = "text/xml";

    /**
     * 真实客户IP
     */
    static final String X_REAL_IP = "X-Real-IP";
    /**
     * UTF8编码字符集
     */
    static final String CHARACTOR_ENCODING_UTF8 = "utf8";
    /**
     * GBK编码字符集
     */
    static final String CHARACTOR_ENCODING_GBK = "gbk";
}
