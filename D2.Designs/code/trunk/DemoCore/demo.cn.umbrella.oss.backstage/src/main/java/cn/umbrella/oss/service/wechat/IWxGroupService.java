package cn.umbrella.oss.service.wechat;

import java.util.List;
import java.util.Map;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.oss.wechat.bean.WxGroup;

import com.github.pagehelper.PageInfo;

public interface IWxGroupService {
	public int add(WxGroup wxGroup);

	public int add(List<WxGroup> list);

	public int update(WxGroup wxGroup);

	public int update(List<WxGroup> list);

	public int delete(java.lang.Long id);

	public int delete(List<java.lang.Long> ids);

	public WxGroup queryById(java.lang.Long id);

	public List<WxGroup> query(Map<String, Object> map);

	public PageInfo<WxGroup> queryPage(BaseQuery query);
	
	public Map<String, Object> updateSynchro(Map<String, Object> map);
	
}