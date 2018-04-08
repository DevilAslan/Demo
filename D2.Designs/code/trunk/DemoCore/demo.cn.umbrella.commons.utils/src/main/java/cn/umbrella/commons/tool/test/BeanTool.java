package cn.umbrella.commons.tool.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.umbrella.commons.enums.ICommonEnum;
import cn.umbrella.commons.utils.base.StringTool;

public class BeanTool {

	/**
	 * 遍历对象的属性，将非空属性的名称和值加入map中
	 * 
	 * @param object
	 * @param map
	 */
	public static Map<String, Object> pojoToMap(Object object) {
		Map<String, Object> tmp = new HashMap<String, Object>();
		if (object == null) {
			return tmp;
		}
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
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (Objects.equals("TABLE_ALIAS", key) || Objects.equals("serialVersionUID", key)) {
				continue;
			}
			String methodName = "get" + StringTool.toUpperCaseFirstOne(key);
			try {
				Method m = object.getClass().getMethod(methodName);
				// 调用getter方法获取属性值
				Object value = (Object) m.invoke(object);
				if (value != null) {
					tmp.put(key, value);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return tmp;
	}
	
	public static Map<String, String> enumToJSONString(Class<?> enumClass){
		Map<String, String> res = new HashMap<String, String>();
		for (Object o : enumClass.getEnumConstants()) {
			ICommonEnum item=(ICommonEnum) o;
			res.put(item.getKey()+"", item.getValue());
		}
		return res;
	}
	
	/**
	 * 获取对象属性值为null的属性 
	 *
	 * @Title: getNullPropertyNames 
	 * @param source
	 * @return 
	 * @return String[]
	 * @throws
	 */
	public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	/**
	 * 集合去重
	 *
	 * @Title: removeDuplicateWithOrder 
	 * @param list 
	 * @return void
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		if(list != null && list.size() > 0) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (set.add(element))
					newList.add(element);
			}
			list.clear();
			list.addAll(newList);
		}
	 }
}
