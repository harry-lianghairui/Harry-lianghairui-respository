package com.thinkgem.jeesite.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thinkgem.jeesite.domain.Resp;
import com.thinkgem.jeesite.utils.Consts;
import com.thinkgem.jeesite.utils.CryptUtil;
import com.thinkgem.jeesite.utils.JsonHelper;

/**
 * sign校验拦截器
 * 
 */
public class SignInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SignInterceptor.class);

    private String key;
    private boolean online;
    private String ip;

    private List<String> uriWhitelist;

   	public void setUriWhitelist(List<String> uriWhitelist) {
   		this.uriWhitelist = uriWhitelist;
   	}
   	
    public void setKey(String key) {
        this.key = key;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if (isWhitelist(request.getRequestURI())) {
    		return true;
    	}
    	long time = System.currentTimeMillis();
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        Set<String> set = new TreeSet<String>(params.keySet());
        for (String key : set) { // 遍历所有key
            if ("sign".equalsIgnoreCase(key)) {
                continue;
            }
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) { //
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        Resp resp = new Resp();
        String sign = StringUtils.trim(request.getParameter("sign"));
        if (StringUtils.isNotBlank(queryString) && StringUtils.isNotBlank(sign)) {
            String toBeEncrypt = queryString;
            String signKey = Consts.Key.SIGN;
    		String version = request.getHeader("gzVersion");
    		if (StringUtils.isNotBlank(version)&& versionCompare(version, "1.0.1") <= 0) {
    			toBeEncrypt=toBeEncrypt+"key=" +signKey;
    		}else{
    			toBeEncrypt=toBeEncrypt+"key=" +  key;
    		}
            String expectSign = CryptUtil.md5(toBeEncrypt);
            if (StringUtils.equalsIgnoreCase(sign, expectSign)) {
                LOG.info("[sign] uri:{}, toBeEncrypt:{}, expectSign:{},sign:{},cost:{}", request.getRequestURI(),
                        toBeEncrypt, expectSign, sign, System.currentTimeMillis() - time);
                return true;
            } else {
                LOG.info("[sign] uri:{} request failed,toBeEncrypt:{},expectSign:{},sign:{} not match",
                        request.getRequestURI(), toBeEncrypt, expectSign, sign);
                resp.setCode(Consts.Code.SIGN_ERROR);
            }
        } else {
            resp.setCode(Consts.Code.SIGN_ERROR);
        }
        response.getWriter().write(JsonHelper.toJson(resp));
        return false;
    }
    
    private static int versionCompare(String v1, String v2) {
		if (v1 == null) {
			if (v2 == null) {
				return 0;
			}
			return -1;
		}
		if (v2 == null) {
			return 1;
		}
		String[] v11 = v1.split("\\.");
		String[] v22 = v2.split("\\.");
		int t1 = NumberUtils.toInt(v11[0]);
		int t2 = NumberUtils.toInt(v22[0]);
		if (t1 != t2) {
			return t1 - t2;
		}
		t1 = NumberUtils.toInt(v11[1]);
		t2 = NumberUtils.toInt(v22[1]);
		if (t1 != t2) {
			return t1 - t2;
		}
		t1 = NumberUtils.toInt(v11[2]);
		t2 = NumberUtils.toInt(v22[2]);
		if (t1 != t2) {
			return t1 - t2;
		}
		return 0;
	}
    
    private boolean isWhitelist(String requestURI) {
		for (String w : uriWhitelist) {
			if (StringUtils.contains(requestURI, w)) {
				return true;
			}
		}
		return false;
	}

}
