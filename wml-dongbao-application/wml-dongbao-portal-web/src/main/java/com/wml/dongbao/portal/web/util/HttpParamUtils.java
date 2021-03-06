package com.wml.dongbao.portal.web.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;


/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/5/26
 */
public class HttpParamUtils {

	public static SortedMap<String,String> getAllParams(HttpServletRequest request){

		// 获取 url上的参数
		Map<String, String> urlParams = getUrlParams(request);
		System.out.println("url 参数："+urlParams);

		// 获取 body上的参数

		return null;
	}

	/**
	 * 获取 url中的参数  /asdf/asd?a=b&b=c
	 * @param request
	 * @return
	 */
	public static Map<String,String> getUrlParams(HttpServletRequest request){
		String queryParam = "";
		try {
			queryParam = URLDecoder.decode(request.getQueryString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Map<String,String> result = new HashMap<>();
		String[] split = queryParam.split("&");
		for (String s: split){
			int i = s.indexOf("=");
			result.put(s.substring(0,i),s.substring(i+1));
		}
		System.out.println("url 参数："+result);
		return result;
	}
}
