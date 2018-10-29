package cn.umbrella.oss.utils.wechat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONObject;
/**
 * 寰俊 API銆佸井淇″熀鏈帴鍙�
 * 
 */

public class WxURIApi {

	//token 接口
	private static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	//创建菜单
	private static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	
	//创建个性化菜单
	private static final String MENU_ADDCONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=%s";
	
	//删除菜单
	private static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
	
	//获取账号粉丝信息
	private static final String GET_FANS_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	
	//获取账号粉丝列表
	private static final String GET_FANS_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s";
	
	//获取批量素材
	private static final String GET_BATCH_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";
	
	//上传多媒体资料接口
	private static final String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";
	
	//上传永久素材：图文
	private static final String UPLOAD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";
	
	//群发接口
	private static final String MASS_SEND = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";
	
	//网页授权OAuth2.0获取code
	private static final String GET_OAUTH_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=%s&scope=%s&state=%s#wechat_redirect";
	
	//网页授权OAuth2.0获取token
	private static final String GET_OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		
	//网页授权OAuth2.0获取用户信息
	private static final String GET_OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
	
	//生成二维码
	private static final String CREATE_QRCODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";
	
	//根据ticket获取二维码图片
	private static final String SHOW_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
	
	//js ticket
	private static final String GET_JSAPI_TICKET="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
	
	//发送客服消息
	private static final String SEND_CUSTOM_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
	
	//模板消息接口
	private static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
	
	//获取临时图片素材
	private static final String GET_TEMP_IMG_INFO = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s"; 
	
	//创建分组
	private static final String CREATE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%s";
	
	//获得分组列表
	private static final String GET_GROUP_LIST = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%s";
	
	//修改分组
	private static final String UPDATE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=%s";

	//删除分组
	private static final String DELETE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=%s";

	//移动用户分组
	private static final String MEMBERS_UPDATE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=%s";
	
	//批量移动用户分组
	private static final String MEMBERS_BATCHUPDATE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=%s";
	
	
	
	//获取token接口
	public static String getTokenUrl(String appId,String appSecret){
		return String.format(TOKEN, appId, appSecret);
	}
	
	//获取上传Media接口
	public static String getUploadMediaUrl(String token,String type){
		return String.format(UPLOAD_MEDIA, token, type);
	}
	
	//获取菜单创建接口
	public static String getMenuCreateUrl(String token){
		return String.format(MENU_CREATE, token);
	}
	
	//获取个性化菜单创建接口
	public static String getMenuAddconditionalUrl(String token){
		return String.format(MENU_ADDCONDITIONAL, token);
	}
	
	//获取菜单删除接口
	public static String getMenuDeleteUrl(String token){
		return String.format(MENU_DELETE, token);
	}
	
	//获取粉丝信息接口
	public static String getFansInfoUrl(String token,String openid){
		return String.format(GET_FANS_INFO, token, openid);
	}
	
	//获取粉丝列表接口
	public static String getFansListUrl(String token,String nextOpenId){
		if(nextOpenId == null){
			return String.format(GET_FANS_LIST, token);
		}else{
			return String.format(GET_FANS_LIST + "&next_openid=%s", token, nextOpenId);
		}
	}
	
	//获取素材列表接口
	public static String getBatchMaterialUrl(String token){
		return String.format(GET_BATCH_MATERIAL, token);
	}
	
	//获取上传图文消息接口
	public static String getUploadNewsUrl(String token){
		return String.format(UPLOAD_NEWS, token);
	}
	
	//群发接口
	public static String getMassSendUrl(String token){
		return String.format(MASS_SEND, token);
	}
	
	//网页授权OAuth2.0获取code
	public static String getOAuthCodeUrl(String appId ,String redirectUrl ,String scope ,String state){
		return String.format(GET_OAUTH_CODE, appId, urlEnodeUTF8(redirectUrl), "code", scope, state);
	}
	
	//网页授权OAuth2.0获取token
	public static String getOAuthTokenUrl(String appId ,String appSecret ,String code ){
		return String.format(GET_OAUTH_TOKEN, appId, appSecret, code);
	}
	
	//网页授权OAuth2.0获取用户信息
	public static String getOAuthUserinfoUrl(String token ,String openid){
		return String.format(GET_OAUTH_USERINFO, token, openid);
	}
	
	//获取创建二维码接口url
	public static String getCreateQrcodeUrl(String token){
		return String.format(CREATE_QRCODE, token);
	}
	
	//获取显示二维码接口
	public static String getShowQrcodeUrl(String ticket){
		return String.format(SHOW_QRCODE, ticket);
	}
	
	//获取js ticket url
	public static String getJsApiTicketUrl(String token){
		return String.format(GET_JSAPI_TICKET, token);
	}
	
	//获取发送客服消息 url
	public static String getSendCustomMessageUrl(String token){
		return String.format(SEND_CUSTOM_MESSAGE, token);
	}
	
	//获取发送模板消息 url
	public static String getSendTemplateMessageUrl(String token){
		return String.format(SEND_TEMPLATE_MESSAGE, token);
	}
	
	//创建分组 url
	public static String getCreateGroup(String token){
		return String.format(CREATE_GROUP, token);
	}
	
	//获取分组列表 url
	public static String getGroupList(String token){
		return String.format(GET_GROUP_LIST, token);
	}
	
	//修改分组 url
	public static String updateGroup(String token){
		return String.format(UPDATE_GROUP, token);
	}
	
	//删除分组 url
	public static String deleteGroup(String token){
		return String.format(DELETE_GROUP, token);
	}
	
	
	//移动用户分组  url
	public static String membersUpdateGroup(String token){
		return String.format(MEMBERS_UPDATE_GROUP, token);
	}
	
	
	//批量移动用户分组 url
	public static String membersBatchUpdateGroup(String token){
		return String.format(MEMBERS_BATCHUPDATE_GROUP, token);
	}
	

	//获取临时图片素材接口
	public static String getTmpImgInfo(String token,String mediaId){
		return String.format(GET_TEMP_IMG_INFO, token, mediaId);
	}
	
	public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			TrustManager[] tm = { new JEEWeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * 上传多媒体文件
	 * 返回media_id
	 */
	public static String uploadMedia(String accessToken, String mediaType, String mediaUri) {
		String uploadMediaUrl = String.format(UPLOAD_MEDIA, accessToken, mediaType);
		String boundary = "----------" + System.currentTimeMillis();// 设置边界  
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaUri);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");
			// 根据内容类型判断文件扩展名
			String fileExt = ".jpg";
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",fileExt).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n",contentType).getBytes());
			
			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();
			// 使用JSON-lib解析返回结果
			JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
			if(jsonObject.containsKey("media_id"))
				return jsonObject.getString("media_id");
			return null;
		} catch (Exception e) {
			String error = String.format("上传媒体文件失败：%s", e);
			System.out.println(error);
		}
		return null;
	}
}

class JEEWeiXinX509TrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}