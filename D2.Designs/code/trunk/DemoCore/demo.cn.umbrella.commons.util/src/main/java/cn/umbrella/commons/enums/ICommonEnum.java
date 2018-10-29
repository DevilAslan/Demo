package cn.umbrella.commons.enums;

/**
 * 
 * 一般键值型枚举接口，规范 获取键和值的方式  
 *
 * @ClassName: IGDCommonEnum  
 * @date
 *  
 * @param <T>
 */
public interface ICommonEnum<T> {
	public T getKey();
	public String getValue();
}
