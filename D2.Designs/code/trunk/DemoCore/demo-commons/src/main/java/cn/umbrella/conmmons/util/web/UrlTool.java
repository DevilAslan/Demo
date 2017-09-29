package cn.umbrella.conmmons.util.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.umbrella.conmmons.util.base.DateStringUtility;
import cn.umbrella.conmmons.util.base.StringTool;

public class UrlTool {

	/**
	 * 遍历对象的属性，整理成URL样式的条件字符串，
	 * @param object
	 * @param except
	 * @return String 如：&name=test&age=10&height=100
	 */
	public static String pojoToUrlQueryStr(Object object, String[] except) {
		Map<String, Object> map = new HashMap<String, Object>();

		Field[] flds = object.getClass().getDeclaredFields();
		for (Field f : flds) {
			map.put(f.getName(), null);
		}
		Class<?> cls = object.getClass().getSuperclass();
		while (cls != Object.class) {
			Field[] fldz = cls.getDeclaredFields();
			for (Field f : fldz) {
				if (!map.containsKey(f.getName())) {
					map.put(f.getName(), null);
				}
			}
			cls = cls.getSuperclass();
		}
		StringBuilder sb = new StringBuilder();
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		Set<String> set = new HashSet<String>();
		if(except != null && except.length>0){
			while (it.hasNext()) {
				String key = it.next();
				for(String s:except){
					if(s.equals(key)){
						set.add(key);
						break;
					}
				}
			}
		}
		
		keySet.removeAll(set);
		it = keySet.iterator();
		while (it.hasNext()) {
			try {
				String key = it.next();
				
				String methodName = "get" + StringTool.toUpperCaseFirstOne(key);
				Method m = object.getClass().getMethod(methodName);
				// 调用getter方法获取属性值
				Object value = (Object) m.invoke(object);
				if (value != null) {
					sb.append("&");
					String s = key + "=";
					if(value instanceof String){
						s += java.net.URLEncoder.encode(value.toString(), "UTF-8");
					} else if(value instanceof Date){
						s += DateStringUtility.dateToString((Date)value, DateStringUtility.YYYY_MM_DD_HH_MM_SS);
					} else {
						s += value;
					}
					sb.append(s);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
//	public static void main(String[] args){
//		String[] s = {"pageNo"};
//		System.out.println(pojoToUrlQueryStr(new cn.com.dhcc.common.bean.BaseQuery(), s));
//		s = new String[0];
//		System.out.println(pojoToUrlQueryStr(new cn.com.dhcc.common.bean.BaseQuery(), s));
//		System.out.println(pojoToUrlQueryStr(new cn.com.dhcc.common.bean.BaseQuery(), null));
//	}
}
