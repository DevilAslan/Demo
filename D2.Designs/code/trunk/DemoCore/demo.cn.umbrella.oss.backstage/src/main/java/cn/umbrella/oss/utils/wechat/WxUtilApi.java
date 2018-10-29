package cn.umbrella.oss.utils.wechat;

import java.util.List;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cn.umbrella.oss.enums.MediaType;
import cn.umbrella.oss.wechat.bean.TwxcmsMsgNews;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信 API、微信基本接口
 * 
 */
@Component
public class WxUtilApi {
	
	public static String accessToken = "accessToken";
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private MemcachedClient memcachedClient;

	/**
	 * 获取有效的access_token
	 * access_toke微信官方定义有效期为7200s
	 * @return
	 */
	public String getAccessToken(String appid,String secret){ 
		String access_token = (String) memcachedClient.get(accessToken);
		if(access_token== null){
			JSONObject object = getAccessTokens(appid, secret);
			Boolean bool = (Boolean) object.get("access");
			if(object!= null && bool){
				memcachedClient.delete(accessToken);
				memcachedClient.add(accessToken, 7000, object.get("access_token"));
			}
			access_token = (String) object.get("access_token");
		}
		return access_token;
	}

	//获取接口访问凭证
	public JSONObject getAccessTokens(String appId, String appSecret) {
		RestTemplate restTemplate = new RestTemplate();
		String tockenUrl = WxURIApi.getTokenUrl(appId, appSecret);
		JSONObject object = restTemplate.getForObject(tockenUrl, JSONObject.class);
		JSONObject jsonObj = new JSONObject(); 
		try {
			jsonObj.put("access", true);
			jsonObj.put("access_token", object.get("access_token"));
			jsonObj.put("expires_in", object.get("expires_in"));
		} catch (Exception e) {
			jsonObj.put("access", false);
		}
		return jsonObj;
	}
	
	//发布菜单
	public JSONObject publishMenus(String menus){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.getMenuCreateUrl(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.POST.toString(), menus);
		//RestTemplate restTemplate = new RestTemplate();
		//return restTemplate.getForObject(url, JSONObject.class,menus);
	}
	
	//上传图文消息
	public static JSONObject uploadNews(List<TwxcmsMsgNews> msgNewsList,String accessToken){
		JSONObject rstObj = new JSONObject();
		try{
			JSONArray jsonArr = new JSONArray();
			for(TwxcmsMsgNews news : msgNewsList){
				JSONObject jsonObj = new JSONObject();
				//上传图片素材
				String mediaId = WxURIApi.uploadMedia(accessToken,MediaType.Image.toString(),news.getPicpath());
				jsonObj.put("thumb_media_id", mediaId);
				if(news.getAuthor() != null){
					jsonObj.put("author", news.getAuthor());
				}else{
					jsonObj.put("author", "");
				}
				if(news.getTitle() != null){
					jsonObj.put("title", news.getTitle());
				}else{
					jsonObj.put("title", "");
				}
				if(news.getFromurl() != null){
					jsonObj.put("content_source_url", news.getFromurl());
				}else{
					jsonObj.put("content_source_url", "");
				}
				if(news.getBrief() != null){
					jsonObj.put("digest", news.getBrief());
				}else{
					jsonObj.put("digest", "");
				}
				if(news.getShowpic() != null){
					jsonObj.put("show_cover_pic", news.getShowpic());
				}else{
					jsonObj.put("show_cover_pic", "1");
				}
				jsonObj.put("content", news.getDescription());
				jsonArr.add(jsonObj);
			}
			JSONObject postObj = new JSONObject();
			postObj.put("articles", jsonArr);
			rstObj = WxURIApi.httpsRequest(WxURIApi.getUploadNewsUrl(accessToken), HttpMethod.POST.toString(), postObj.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return rstObj;
	}

	
	//获取用户列表
	public JSONObject getFansData(String nextOpenId){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.getFansListUrl(access_token, nextOpenId);
		return WxURIApi.httpsRequest(url,HttpMethod.GET.toString(),null);
		//RestTemplate restTemplate = new RestTemplate();
		//return restTemplate.getForObject(url, JSONObject.class,menus);
	}

	//同步用户
	public JSONObject getopenIdData(String openid){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.getFansInfoUrl(access_token, openid);
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, JSONObject.class);
	}
	
	//发送信息
	public JSONObject sendMsgData(String strMsg){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.getSendCustomMessageUrl(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.GET.toString(),strMsg);
	}
	

	//创建分组
	public  JSONObject createGroup(String groupStr){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.getCreateGroup(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.POST.toString(),groupStr);
	}
	
	//获取分组列表
	public JSONObject getGroupList(){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.getGroupList(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.GET.toString(),null);
	}
	
	//修改分组
	public JSONObject updateGroup(String groupStr){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.updateGroup(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.POST.toString(),groupStr);
	}
	
	//删除分组
	public JSONObject deleteGroup(String groupStr){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.deleteGroup(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.POST.toString(),groupStr);
	}
	
	//移动分组
	public JSONObject membersUpdateGroup(String groupStr){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.membersUpdateGroup(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.POST.toString(),groupStr);
	}
	
	//批量移动分组
	public JSONObject membersBatchUpdateGroup(String groupStr){
		String access_token = tokenUtils.getAccessToken();
		String url = WxURIApi.membersBatchUpdateGroup(access_token);
		return WxURIApi.httpsRequest(url,HttpMethod.POST.toString(),groupStr);
	}
}