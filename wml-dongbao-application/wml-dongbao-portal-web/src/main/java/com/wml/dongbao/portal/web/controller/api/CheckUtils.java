package com.wml.dongbao.portal.web.controller.api;

import com.wml.dongbao.common.util.MD5Util;

import java.util.*;

/**
 * @author 马士兵教育:chaopengfei
 * @date 2021/5/24
 */
public class CheckUtils {

	/**
	 * app secret和 appId，一一对应
	 */
	public static String appSecret = "aaa";

	// 根据map生成签名
	public static String generatorSign(Map<String, Object> map){
		map.remove("sign");
		// 排序:
		Map<String, Object> stringObjectMap = sortMapByKey(map);
		// 转格式:   name=张三&age=10,:  name,张三,age,10
		Set<Map.Entry<String, Object>> entries = stringObjectMap.entrySet();
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String,Object> e : entries){
			sb.append(e.getKey()+","+e.getValue()).append("#");
		}


		// 组装secret  在参数的后面 添加 secret
		sb.append("secret").append(appSecret);
		// 生成签名
		return MD5Util.md5(sb.toString());
		// sha256生成 签名
//		return Sha256Utils.getSHA256(sb.toString());
	}

	public static Map<String,Object> sortMapByKey(Map<String,Object> map){
		// 判断一下map是否为空，自己写

		Map<String,Object> sortMap = new TreeMap<>(new MyMapComparator());

		sortMap.putAll(map);

		return  sortMap;

	}

	static class MyMapComparator implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	}

	public static void main(String[] args) {
		HashMap<String,Object> map = new HashMap<>();
//		map.put("ap",1);
//		map.put("z1",2);
//		map.put("he",3);
//
//		Map<String, Object> stringObjectMap = sortMapByKey(map);
//		System.out.println(stringObjectMap);

		map.put("appId",1);
		map.put("name",2);
		//map.put("timestamp",)


		String s = generatorSign(map);
		// 74f0c8c14fd2869121c910601e9ea859
		System.out.println(s);
	}

}
