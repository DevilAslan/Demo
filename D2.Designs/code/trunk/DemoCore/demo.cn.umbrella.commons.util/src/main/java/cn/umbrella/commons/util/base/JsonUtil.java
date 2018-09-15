/** 
 * JsonUtil.java Created on Jun 30, 2009
 * Copyright 2009@Aslan. 
 * All right reserved. 
 */
package cn.umbrella.commons.util.base;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 用来输出JSON字符串,交给Javascript在页面上进行处理.支持JSON对象的嵌套,支持数组
 * 
 * @Time 2:54:56 PM
 */
public class JsonUtil {

	private Map<String, Object> jsonMap = new HashMap<String, Object>();
	private static SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd");

	public void clear() {
		jsonMap.clear();
	}

	/**
	 * 添加元素 <br/>
	 * 邮件：wallimn@sohu.com<br/>
	 * 博客：http://blog.csdn.net/wallimn<br/>
	 * 参数：<br/>
	 * 
	 * @param key
	 * @param value
	 *            　支持简单类型（即原生类型的包装器类）、bean对象、List<Object>、Map<String,Object>以及数组
	 * @return
	 */
	public Map<String, Object> put(String key, Object value) {
		jsonMap.put(key, value);
		return jsonMap;
	}

	// 判断是否要加引号
	private static boolean isNoQuote(Object value) {
		return (value instanceof Integer || value instanceof Boolean
				|| value instanceof Double || value instanceof Float
				|| value instanceof Short || value instanceof Long || value instanceof Byte);
	}

	private static boolean isQuote(Object value) {
		return (value instanceof String || value instanceof Character);
	}

	@SuppressWarnings("unchecked")
	@Override
	/*
	 * 返回形如{'apple':'red','lemon':'yellow'}的字符串
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Set<Entry<String, Object>> set = jsonMap.entrySet();
		for (Entry<String, Object> entry : set) {
			Object value = entry.getValue();
			if (value == null) {
				continue;// 对于null值，不进行处理，页面上的js取不到值时也是null
			}
			sb.append("'").append(entry.getKey()).append("':");
			if (value instanceof JsonUtil) {
				sb.append(value.toString());
			} else if (isNoQuote(value)) {
				sb.append(value);
			} else if (value instanceof Date) {
				sb.append("'").append(formatter.format(value)).append("'");
			} else if (isQuote(value)) {
				sb.append("'").append(value).append("'");
			} else if (value.getClass().isArray()) {
				sb.append(ArrayToStr(value));
			} else if (value instanceof Map) {
				sb.append(fromObject((Map<String, Object>) value).toString());
			} else if (value instanceof List) {
				sb.append(ListToStr((List<Object>) value));
			} else {
				sb.append(fromObject(value).toString());
			}
			sb.append(",");
		}
		int len = sb.length();
		if (len > 1) {
			sb.delete(len - 1, len);
		}
		sb.append("}");
		return sb.toString();
	}

	public static String ArrayToStr(Object array) {
		if (!array.getClass().isArray())
			return "[]";
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int len = Array.getLength(array);
		Object v = null;
		for (int i = 0; i < len; i++) {
			v = Array.get(array, i);
			if (v instanceof Date) {
				sb.append("'").append(formatter.format(v)).append("'").append(
						",");
			} else if (isQuote(v)) {
				sb.append("'").append(v).append("'").append(",");
			} else if (isNoQuote(v)) {
				sb.append(i).append(",");
			} else {
				sb.append(fromObject(v)).append(",");
			}
		}
		len = sb.length();
		if (len > 1)
			sb.delete(len - 1, len);
		sb.append("]");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static String ListToStr(List<Object> list) {
		if (list == null)
			return null;
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		Object value = null;
		for (java.util.Iterator<Object> it = list.iterator(); it.hasNext();) {
			value = it.next();
			if (value instanceof Map) {
				sb.append(fromObject((Map<String, Object>) value).toString()).append(",");
			} else if (isNoQuote(value)) {
				sb.append(value).append(",");
			} else if (isQuote(value)) {
				sb.append("'").append(value).append("'").append(",");
			} else {
				sb.append(fromObject(value).toString()).append(",");
			}
		}
		int len = sb.length();
		if (len > 1)
			sb.delete(len - 1, len);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 从一个bean装载数据，返回一个JsonUtil对象。 <br/>
	 * 邮件：wallimn@sohu.com<br/>
	 * 博客：http://blog.csdn.net/wallimn<br/>
	 * 参数：<br/>
	 * 
	 * @param object
	 * @return
	 */
	public static JsonUtil fromObject(Object bean) {
		JsonUtil json = new JsonUtil();
		if (bean == null)
			return json;
		Class<?> cls = bean.getClass();
		Field[] fs = cls.getDeclaredFields();
		Object value = null;
		String fieldName = null;
		Method method = null;
		int len = fs.length;
		for (int i = 0; i < len; i++) {
			fieldName = fs[i].getName();
			try {
				method = cls.getMethod(getGetter(fieldName), (Class[]) null);
				value = method.invoke(bean, (Object[]) null);
			} catch (Exception e) {
				// System.out.println(method.getName());
				// e.printStackTrace();
				continue;
			}
			json.put(fieldName, value);
		}
		return json;
	}

	/**
	 * 从Map中装载数据 <br/>
	 * 作者：wallimn　时间：2009-2-5　下午04:05:04<br/>
	 * 邮件：wallimn@sohu.com<br/>
	 * 博客：http://blog.csdn.net/wallimn<br/>
	 * 参数：<br/>
	 * 
	 * @param map
	 * @return
	 */
	public static JsonUtil fromObject(Map<String, Object> map) {
		JsonUtil json = new JsonUtil();
		if (map == null)
			return json;
		json.getMap().putAll(map);
		return json;
	}

	private static String getGetter(String property) {
		return "get" + property.substring(0, 1).toUpperCase()
				+ property.substring(1, property.length());
	}

	public Map<String, Object> getMap() {
		return this.jsonMap;
	}

}
