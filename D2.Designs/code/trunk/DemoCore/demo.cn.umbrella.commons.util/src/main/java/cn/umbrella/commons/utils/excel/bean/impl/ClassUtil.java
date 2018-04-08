package cn.umbrella.commons.utils.excel.bean.impl;

public class ClassUtil {
	/**
	 * 判断一个类是否是一个接口的实现
	 * @param implementClass  实现接口的类
	 * @param interfaceClass		接口类
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isImplement(Class implementClass, Class interfaceClass){
		Class[] clazzs = implementClass.getInterfaces();
		for (Class clazz : clazzs) {
			if(clazz ==interfaceClass ){
				return true;
			}
		}
		return false;
	}
	
}
