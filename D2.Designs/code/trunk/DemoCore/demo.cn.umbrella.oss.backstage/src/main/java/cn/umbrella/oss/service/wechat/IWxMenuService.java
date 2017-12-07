package cn.umbrella.oss.service.wechat;

import java.util.List;
import java.util.Map;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.oss.wechat.bean.WxMenu;

import com.github.pagehelper.PageInfo;

public interface IWxMenuService {
	public int add(WxMenu wxMenu);

	public int add(List<WxMenu> list);

	public int update(WxMenu wxMenu);

	public int update(List<WxMenu> list);

	public int delete(java.lang.Integer id);

	public int delete(List<java.lang.Integer> ids);

	public WxMenu queryById(Integer id);

	public List<WxMenu> query(Map<String, Object> map);

	public PageInfo<WxMenu> queryPage(BaseQuery query);
}