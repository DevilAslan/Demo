package cn.umbrella.commons.base.model;

/**
 * 
 * 定义Model的一些基础信息
 * 
 * @ClassName: BaseModel
 * @author zhou.xy
 * @date 2016年1月4日 下午1:32:14
 * 
 * @param <KEY_TYPE>
 */
public abstract class BaseModel<KEY_TYPE> {
	private KEY_TYPE id;

	public abstract KEY_TYPE getId();

}
