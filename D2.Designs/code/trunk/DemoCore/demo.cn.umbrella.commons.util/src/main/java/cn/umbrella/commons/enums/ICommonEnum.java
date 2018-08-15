package cn.umbrella.commons.enums;

/**
 * 
 * 一般键值型枚举接口，规范 获取键和值的方式  
 *
 * @ClassName: IGDCommonEnum  
 * @author zhou.xy
 * @date
 *  
 * @param <T>
 */
public interface IGDCommonEnum<T> {
	public T getValue();
	public String getName();
}
