package com.thinkgem.jeesite.interceptor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thinkgem.jeesite.utils.TimeCostHelper;

/**
 * 日志拦截器
 * 
 * @author duanshao
 * 
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

	private final Logger LOG = LoggerFactory.getLogger("access");

	private String[] excludeMethods;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Map<String, Object> carrier = new HashMap<String, Object>();
		carrier.put("request", queryString(request));
		carrier.put("uri", request.getRequestURI());
		TimeCostHelper.watch(carrier);
		return true;
	}

	private String queryString(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		String queryString = "";
		Set<String> set = new HashSet<String>(params.keySet());
		for (String key : set) {
			String[] values = params.get(key);
			if (values == null || values.length == 0) {
				continue;
			}
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		return queryString;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOG.info("cost:{};uri:{}", TimeCostHelper.getTimeCost(),
				request.getRequestURI());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "token");
		TimeCostHelper.clean();
	}

	public String[] getExcludeMethods() {
		return excludeMethods;
	}

	public void setExcludeMethods(String[] excludeMethods) {
		this.excludeMethods = excludeMethods;
	}
}
