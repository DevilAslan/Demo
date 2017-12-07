package cn.umbrella.oss.utils.wechat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cn.umbrella.oss.service.wechat.IWxUserService;
import cn.umbrella.oss.wechat.bean.UserInfo;
import cn.umbrella.oss.wechat.bean.WxUser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 请求微信api获取关注公众号的用户列表
 * @author wumin
 * @create 2017-08-24 13:14:51
 * @history
 */

@Component
public class RecursiveUtil {

	@Value("#{properties['wx_api_url']}")
	private String weChatUrl;
	
//	@Autowired
//	private TokenUtils tokenUtils;
	
	@Autowired
	private IWxUserService iWxUserService;
	
	
	/**
	 * 根据openId列表获取用户详细信息
	 * @param
	 * @return List<WxUser>
	 */
	public List<WxUser> getUserList(JSONObject json) {
		List<WxUser> user = new ArrayList<>();
		JSONObject data = json.getJSONObject("data");
		JSONArray openIds = data.getJSONArray("openid");
		List<String> openIdList = JSONArray.parseArray(Objects.toString(openIds),String.class);
		
		for(String s:openIdList){
			UserInfo userInfo = JSONObject.parseObject(iWxUserService.getUserInfo(s).toString(), UserInfo.class);
			WxUser wxUser = new WxUser();
			BeanUtils.copyProperties(userInfo, wxUser);
//			wxUser.setOpenid(userInfo.getString("openid"));
//			wxUser.setSubscribe(userInfo.getBooleanValue("subscribe"));
//			wxUser.setNickname(userInfo.getString("nickname"));
//			wxUser.setSex(userInfo.getIntValue("sex"));
//			wxUser.setCity(userInfo.getString("city"));
//			wxUser.setCountry(userInfo.getString("country"));
//			wxUser.setProvince(userInfo.getString("province"));
//			wxUser.setLanguage(userInfo.getString("language"));
//			wxUser.setHeadimgurl(userInfo.getString("headimgurl"));
//			wxUser.setSubscribeTime(userInfo.getLongValue("subscribe_time"));
//			wxUser.setUnionid(userInfo.getString("unionid"));
//			wxUser.setGroupid(userInfo.getInteger("groupid"));
			wxUser.setSubscribeTime(userInfo.getSubscribe_time());
			wxUser.setDeleted(0);
			user.add(wxUser);
		}
		return user;
	}
	
//	/**
//	 * 获取用户openid列表
//	 * @param
//	 * @return List<String>
//	 */
//	public List<String> getWxUser(int i,List<String> openIdList,RestTemplate restTemplate,String access_token,String next_openid){
//		JSONObject json = sendRequest(access_token,next_openid,restTemplate);
//		int count = (int) json.get("count");
//		if(count > 0){//列表中有数据
//			JSONObject data = json.getJSONObject("data");
//			JSONArray openIds = data.getJSONArray("openid");
//			List<String> list = JSONArray.parseArray(openIds.toString(),String.class);
//			openIdList.addAll(list);
//			i--;	
//		}
//		if(i > 0){
//			next_openid = (String) json.get("next_openid");
//			return getWxUser(i,openIdList,restTemplate,access_token,next_openid);
//		}else{
//			return openIdList;
//		}
//	}
	
	/**
	 * 请求微信获取微信列表
	 * @param access_token,next_openid,restTemplate
	 * @return JSONObject
	 */
	public JSONObject sendRequest(String access_token,String next_openid,RestTemplate restTemplate){
		String url = weChatUrl+"cgi-bin/user/get?access_token="+access_token+"&next_openid="+next_openid;
		return restTemplate.getForObject(url, JSONObject.class);
	}
	
}
