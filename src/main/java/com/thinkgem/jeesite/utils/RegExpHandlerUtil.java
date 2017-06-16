package com.thinkgem.jeesite.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * author Elson
 * 2014年11月20日 上午11:37:04
 * email 372069412@qq.com
 */
public class RegExpHandlerUtil {

	/**
	 * 正则验证
	 * 
	 * @param regx	正则规则
	 * @param str	字符串
	 * @return boolean 
	 * */
	public static boolean match(String regx,String str){
		
		return matchHandler(regx,str);
	}
	
	public static boolean match(String regx,Integer str){
		return matchHandler(regx,String.valueOf(str));
	}

	private static boolean matchHandler(String regx,String str){
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(str);
		return matcher.lookingAt();
	}
	
}
