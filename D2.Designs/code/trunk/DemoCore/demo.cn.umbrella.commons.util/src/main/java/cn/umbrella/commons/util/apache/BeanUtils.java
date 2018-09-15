package cn.umbrella.commons.util.apache;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * 扩展Apache Commons BeanUtils, 提供一些反射方面缺失功能的封装.
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	protected static final Log logger = LogFactory.getLog(BeanUtils.class);

	private BeanUtils() {
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Field getDeclaredField(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Field getDeclaredField(Class<?> clazz, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);

		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}

		throw new NoSuchFieldException("No such field: " + clazz.getName()
				+ '.' + propertyName);
	}

	/**
	 * 暴力获取对象变量值,忽略private,protected修饰符的限制.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Object forceGetProperty(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = getDeclaredField(object, propertyName);

		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		Object result = null;

		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.info("error wont' happen");
		}

		field.setAccessible(accessible);

		return result;
	}

	/**
	 * 暴力设置对象变量值,忽略private,protected修饰符的限制.
	 * 
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static void forceSetProperty(Object object, String propertyName,
			Object newValue) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			logger.info("Error won't happen");
		}

		field.setAccessible(accessible);
	}

	/**
	 * 暴力调用对象函数,忽略private,protected修饰符的限制.
	 * 
	 * @throws NoSuchMethodException
	 *             如果没有该Method时抛出.
	 */
	public static Object invokePrivateMethod(Object object, String methodName,
			Object... params) throws NoSuchMethodException {
		Assert.notNull(object);
		Assert.hasText(methodName);

		Class<?>[] types = new Class[params.length];

		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}

		Class<?> clazz = object.getClass();
		Method method = null;

		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);

				break;
			} catch (NoSuchMethodException e) {
				// 方法不在当前类定义,继续向上转型
			}
		}

		if (method == null) {
			throw new NoSuchMethodException("No Such Method:"
					+ clazz.getSimpleName() + methodName);
		}

		boolean accessible = method.isAccessible();
		method.setAccessible(true);

		Object result = null;

		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

		method.setAccessible(accessible);

		return result;
	}

	/**
	 * 按Filed的类型取得Field列表.
	 */
	public static List<Field> getFieldsByType(Object object, Class<?> type) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(type)) {
				list.add(field);
			}
		}
		return list;
	}

	/**
	 * 按FiledName获得Field的类型.
	 */
	public static Class<?> getPropertyType(Class<?> type, String name)
			throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	/**
	 * 获得field的getter函数名称.
	 */
	public static String getGetterName(Class<?> type, String fieldName) {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");

		if (type.getName().equals("boolean")) {
			return "is" + StringUtils.capitalize(fieldName);
		} else {
			return "get" + StringUtils.capitalize(fieldName);
		}
	}

	/**
	 * 获得field的getter函数,如果找不到该方法,返回null.
	 */
	public static Method getGetterMethod(Class<?> type, String fieldName) {
		try {
			return type.getMethod(getGetterName(type, fieldName));
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(Object object, String fieldName)
			throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}");
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(Object object, String fieldName,
			Object value) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}");
		}
	}

	/**
	 * 拷贝对象
	 * 
	 * @param src
	 * @param dest
	 * @param excludePropertyName
	 * @throws BusinessException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void copyProperties(Object src, Object dest,
			String[] excludePropertyName) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Object[] valArray = null;
		if (excludePropertyName != null && excludePropertyName.length > 0) {
			valArray = new Object[excludePropertyName.length];
			for (int i = 0; i < excludePropertyName.length; i++) {
				valArray[i] = PropertyUtils.getProperty(dest,
						excludePropertyName[i]);
			}
		}
		PropertyUtils.copyProperties(dest, src);

		if (excludePropertyName != null && excludePropertyName.length > 0) {
			for (int i = 0; i < excludePropertyName.length; i++) {
				PropertyUtils.setProperty(dest, excludePropertyName[i],
						valArray[i]);
			}
		}
	}

	/**
	 * 克隆对象
	 * 
	 * @param dest
	 * @param src
	 */
	public static void cloneProperties(Object dest, Object src) {
		PropertyDescriptor origDescriptors[] = PropertyUtils
				.getPropertyDescriptors(src);
		for (int i = 0; i < origDescriptors.length; i++) {
			String name = origDescriptors[i].getName();
			if (name.indexOf("AsString") == -1) {
				try {
					Object value = PropertyUtils.getProperty(src, name);
					PropertyUtils.setProperty(dest, name, value);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 拷贝对象(属性值不为空)
	 * 
	 * @param orig
	 * @param dest
	 * @throws Exception
	 */
	public synchronized static void copyNotNullObj(Object orig, Object dest)
			throws Exception {
		if (orig != null && dest != null
				&& orig.getClass().getName().equals(dest.getClass().getName())) {
			Map<?, ?> mapobj = PropertyUtils.describe(orig);
			for (Iterator<?> iterator = mapobj.keySet().iterator(); iterator
					.hasNext();) {
				String proname = (String) iterator.next();
				if (!"class".equals(proname)) {
					Object o = PropertyUtils.getProperty(orig, proname);
					if (o != null) {
						PropertyUtils.setProperty(dest, proname, o);
					}
				}
			}
		} else {
			System.err.println("目标对象、源对象为null或对象类型不一致");
		}
	}

}
