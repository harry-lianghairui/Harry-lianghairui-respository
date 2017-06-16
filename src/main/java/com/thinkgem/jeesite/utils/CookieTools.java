package com.thinkgem.jeesite.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CookieTools {

	/**
	 * 获取一个Cookie
	 * @param cookieName
	 * @return Cookie
	 * */
	public static Cookie getCookie(String cookieName){
		ServletRequestAttributes requestAttr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttr.getRequest();
		Cookie[] cookies = request.getCookies();
		if( null != cookies ){
			for( Cookie cookie : cookies ){
				if(cookie.getName().equals( cookieName )){
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
	 * 添加cookie
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param maxAge
	 * @return void
	 * */
	public static void addCookie(HttpServletResponse response,String cookieName,String cookieValue,int maxAge){
		Cookie cookie = getCookie(cookieName);
		if( cookie == null ){
			cookie = new Cookie(cookieName,cookieValue);
		}
		cookie.setValue(cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	
	/**
	 * 删除cookie
	 * @param response
	 * @param cookieName
	 * @return void
	 * */
	public static void removeCookie(HttpServletResponse response,String cookieName){
		Cookie cookie = getCookie(cookieName);
		if( cookie != null ){
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	
}
