package test;

/**
 * 
 * 一般键值型枚举接口，规范 获取键和值的方式  
 *
 * @ClassName: IGDCommonEnum  
 * @author zhou.xy
 * @date 2016年6月29日 上午9:50:38  
 *  
 * @param <T>
 */
public interface IGDCommonEnum<T> {
	public T getValue();
	public String getName();
}
