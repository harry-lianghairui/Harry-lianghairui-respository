package com.thinkgem.jeesite.utils;

import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于对HTTP的请求和响应对象进行操作的工具类.
 * 
 * @author jiangnan@chinaduo.com
 * 
 */
public final class HttpUtils {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
    
    public static final String DEFAULT_CALLBACK_PARAM = "callback";
    /**
     * 获取用户真实IP
     * 
     * @param request HTTP请求
     * @return 尝试返回用户的真实IP
     */
    public static String getRequestorIp(HttpServletRequest request) {

        // 优先级１：从X-Real-IP中取，有多重代理时，这个地址可能不准确，但一般不会被伪造
        String ip = request.getHeader(HttpHeaderConstants.X_REAL_IP);
        if (StringUtils.isBlank(ip)) {
            // 优先级２：从X-Forwarded-For中取，有被伪造的风险
            ip = request.getHeader(HttpHeaderConstants.X_FORWARDED_FOR);
            if (StringUtils.isNotBlank(ip) && ip.indexOf(",") >= 0) {
                // Replaced by Gzc
                // ip = null;
                ip = getXForwardedFor(request, 0);
                // Replaced by Gzc // END
            }
        }
        if (StringUtils.isBlank(ip)) {
            // 优先级３：直接获取RemoteAddr，有反向代理时这个地址会不准确
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 检查请求是否有指定参数名
     * @param req 请求
     * @param names 参数名
     * @return
     */
    public static boolean hasParameters(HttpServletRequest req, String... names){
        for(String name : names){
            if(StringUtils.isBlank(req.getParameter(name))) return false;
        }
        return true;
    }

    private final static Pattern comma = Pattern.compile(",\\s*");

    /**
     * 获取X-Forward-For中特定索引的值, 如果没有XFF返回RemoteAddr
     * 
     * @author GongZichao
     * @param request
     * @param index 从0开始; 负数代表从后向前, -1即最后一个值
     * @return 指定索引在适应边界后对应的值, 如果没有XFF, 则返回RemoteAddr
     */
    public static String getXForwardedFor(HttpServletRequest request, int index) {
        String value = request.getHeader(HttpHeaderConstants.X_FORWARDED_FOR);
        if (StringUtils.isBlank(value)) {
            return request.getRemoteAddr();
        } else {
            String[] ips = comma.split(value);
            if (index < 0) {
                index += ips.length;
            }
            return ips[index < 0 ? 0 : index >= ips.length ? ips.length - 1 : index];
        }
    }

    /**
     * 获取Cookie信息
     * 
     * @param request 请求
     * @param key 关键字
     * @return
     */
    public static String getCookie(HttpServletRequest request, String key) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 设置超时头信息
     * 
     * @param resp HTTP响应
     * @param expireMilSeconds 超时毫秒数
     */
    public static void addExpiresHeader(HttpServletResponse resp, long expireMilSeconds) {
        long exp = System.currentTimeMillis() + expireMilSeconds;
        resp.setDateHeader(HttpHeaderConstants.EXPIRES, exp);
    }

    /**
     * 设置不适用缓存的header
     * 
     * @param resp
     */
    public static void setNoCacheHeader(HttpServletResponse resp) {
        resp.addHeader(HttpHeaderConstants.PRAGMA, HttpHeaderConstants.NO_CACHE);
        resp.addHeader(HttpHeaderConstants.CACHE_CONTROL, HttpHeaderConstants.NO_CACHE);
        resp.setDateHeader(HttpHeaderConstants.EXPIRES, -1);
    }
    
    /**
     * 添加回调函数
     * @param output 输出字符串
     * @param req 请求对象
     * @return 
     * - 如果output或者req为空，返回空字串
     * - 如果请求中不含有callback参数，返回output
     * - 否则，获取请求中的callback, 返回callback(output)
     */
    public static String addCallback(String output,HttpServletRequest req){
        return addCallback(output,req,DEFAULT_CALLBACK_PARAM);
    }
    
    /**
     * 添加回调函数
     * @param output 输出字符串
     * @param req 请求对象
     * @param callbackParam 回调函数的参数，默认是callback
     * @return 
     * - 如果output或者req为空，返回空字串
     * - 如果请求中不含有callback参数，返回output
     * - 否则，获取请求中的callback, 返回callback(output)
     */
    public static String addCallback(String output,HttpServletRequest req,String callbackParam){
        if(req == null || StringUtils.isBlank(output)) return "";
        
        String callback = req.getParameter(StringUtils.defaultString(callbackParam, DEFAULT_CALLBACK_PARAM));
        String result = output;
        if(StringUtils.isNotBlank(callback)){
            result = callback+"("+output+")";
        }
        return result;
        
    }
    
    /**
     * 把请求内容封装成指定的实例
     * @param <T> 封装类型
     * @param req 请求
     * @param type 类型类
     * @return
     */
    public static <T> T bindForm(HttpServletRequest req,Class<T> type){
        return bindForm(req,type,null);
    }
    
    /**
     * 把请求内容封装成指定的实例
     * @param <T> 封装类型
     * @param req 请求
     * @param type 类型类
     * @param prefix 前缀
     * @return
     */
    public static <T> T bindForm(HttpServletRequest req,Class<T> type,String prefix){
        if(type == null || req == null) return null;
        T inst = null;
        try {
            inst = type.newInstance();
            String name;
            boolean hasPrefix = StringUtils.isNotBlank(prefix);
            String fixedPrefix = prefix + ".";
            Enumeration<?> names =req.getParameterNames();
            for(;names.hasMoreElements();){
                name = (String)names.nextElement();
                if(hasPrefix && !name.startsWith(fixedPrefix)) continue;    //skip this param
                try {
                    BeanUtils.setProperty(inst,name, req.getParameter(name));
                } catch (Exception e) {
                    LOG.warn("could not bind property {} because of : {}",name,e.getMessage());
                }
            }
        } catch (Exception e) {
            LOG.warn("could not initiailize instance for type [{}] because : {}",type,e.getMessage());
        }
        return inst;
    }
    
    
  
}
