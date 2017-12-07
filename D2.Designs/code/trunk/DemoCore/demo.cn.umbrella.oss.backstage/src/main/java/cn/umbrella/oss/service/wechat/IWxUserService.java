package cn.umbrella.oss.service.wechat;

import java.util.List;
import java.util.Map;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.oss.wechat.bean.WxUser;
import cn.umbrella.wechat.bean.WXInputMessage;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

public interface IWxUserService {
	
	public int add(WxUser wxUser);

	public int add(List<WxUser> list);

	public int update(WxUser wxUser);

	public int update(List<WxUser> list);

	public int delete(String id);

	public int delete(List<String> ids);

	public WxUser queryById(Long id);
	
	public WxUser queryByOpenId(String openId);

	public List<WxUser> query(Map<String, Object> map);

	public PageInfo<WxUser> queryPage(BaseQuery query);
	
	/**
	 * 根据openid获取用户信息
	 * @param openid
	 * @param weChatUrl
	 * @param access_token
	 * @return
	 */
	public JSONObject getUserInfo(String openid);
	
	public int add(WXInputMessage message) throws Exception;
	
	public int delete(WXInputMessage message);
	
	public int setGroup(Map<String, Object> map);
	
}