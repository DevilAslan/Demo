package cn.umbrella.commons.base;

import java.io.Serializable;

/**
 * 
 * @Description: 定义Form的一些基础信息
 * @ClassName: BaseForm
 * @author zhou.xy
 * @date 2016年1月4日 下午1:32:43
 * 
 * @param <KEY_TYPE>
 *            表主键类型
 */
public class BaseForm<KEY_TYPE extends Serializable> {
	private KEY_TYPE uuid;// 生成唯一标识

	public KEY_TYPE getUuid() {
		return uuid;
	}

	public void setUuid(KEY_TYPE uuid) {
		this.uuid = uuid;
	}

}
