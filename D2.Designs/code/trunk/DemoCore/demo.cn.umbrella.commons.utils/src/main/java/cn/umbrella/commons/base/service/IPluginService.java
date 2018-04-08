package cn.umbrella.commons.base.service;

import cn.umbrella.commons.base.model.BaseModel;

public interface IPluginService {
	@SuppressWarnings("rawtypes")
	void process(BaseModel baseModel);
}
