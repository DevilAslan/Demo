package cn.umbrella.oss.utils.wechat;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@Component
public class TokenUtils {
	@Value("#{properties['wx_api_url']}")
	private String weChatUrl;
	
	@Value("#{properties['wx_appid']}")
	private String appid;
	
	@Value("#{properties['wx_secret']}")
	private String secret;
	
	private String accessTokenKey = "wx_access_token";
	
	private String jsapiTicket  = "wx_jsapi_ticket";
	
	@Autowired
	private MemcachedClient memcachedClient;

	/**
	 * 获取有效的access_token
	 * access_toke微信官方定义有效期为7200s
	 * @return
	 */
	public String getAccessToken(){ 
		Object access_token = memcachedClient.get(accessTokenKey);
		if(access_token != null){
			return (String)access_token;
		}
		try {
			return getNewAccessToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * access_token过期重新获取
	 * @return
	 * @throws Exception 
	 */
	private String getNewAccessToken() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		String url = weChatUrl+"cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		JSONObject object = restTemplate.getForObject(url, JSONObject.class);
		if(object.containsKey("errcode")){
			if(object.getIntValue("errcode")==45009){
				throw new Exception("微信获取接口达到次数限制！");
			} else {
				throw new Exception(object.getString("errmsg"));
			}
		}else if(object.containsKey("access_token")){
			String token = object.getString("access_token");
			memcachedClient.delete(accessTokenKey);
			memcachedClient.add(accessTokenKey, 7000, token);
			return token;
		}
		return null;
	}
	
	/**
	 * 获取有效的js ticket
	 * access_toke微信官方定义有效期为7200s
	 * @return
	 */
	public String getJsapiTicket(){ 
		Object jsapi_ticket = memcachedClient.get(jsapiTicket);
		if(jsapi_ticket != null){
			return (String)jsapi_ticket;
		}
		try {
			return getNewJsapiTicket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * access_token过期重新获取
	 * @return
	 * @throws Exception 
	 */
	private String getNewJsapiTicket() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		String access_token = getAccessToken();
		String url = WxURIApi.getJsApiTicketUrl(access_token);
		JSONObject object = restTemplate.getForObject(url, JSONObject.class);
		
		Integer errcode = object.getIntValue("errcode");
		if(errcode.equals(0)){
			String ticket = object.getString("ticket");
			memcachedClient.delete(jsapiTicket);
			memcachedClient.add(jsapiTicket, 7000, ticket);
			return ticket;
		}else{
			if(object.getIntValue("errcode")==45009){
				throw new Exception("微信获取接口达到次数限制！");
			} else {
				throw new Exception(object.getString("errmsg"));
			}
		}
	}
}
