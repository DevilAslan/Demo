package cn.umbrella.oss.controller.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.commons.validate.ValidateUtil;
import cn.umbrella.conmmons.genarator.IDGenarator;
import cn.umbrella.oss.config.Constant;
import cn.umbrella.oss.service.wechat.IWxGroupService;
import cn.umbrella.oss.service.wechat.IWxMediaService;
import cn.umbrella.oss.service.wechat.IWxMsgInputService;
import cn.umbrella.oss.service.wechat.IWxUserService;
import cn.umbrella.oss.utils.wechat.RecursiveUtil;
import cn.umbrella.oss.utils.wechat.TokenUtils;
import cn.umbrella.oss.utils.wechat.WxURIApi;
import cn.umbrella.oss.utils.wechat.WxUtilApi;
import cn.umbrella.oss.vo.MySessionInfo;
import cn.umbrella.oss.wechat.bean.WxGroup;
import cn.umbrella.oss.wechat.bean.WxMedia;
import cn.umbrella.oss.wechat.bean.WxMsgInput;
import cn.umbrella.oss.wechat.bean.WxUser;
import cn.umbrella.oss.wechat.form.MaterialArticle;
import cn.umbrella.oss.wechat.form.MaterialType;
import cn.umbrella.wechat.config.Constants;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(WxUserController.ACTION_PATH)
@SessionAttributes({Constant.MY_SESSION_INFO})
public class WxUserController {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH="/wxUser/";
	protected static final String MEDTH_PATH="/wxcms/wxUser/";
	
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private IWxUserService wxUserService; 
	@Autowired
	private IWxGroupService wxGroupService;
	@Autowired
	private WxUtilApi wxUtilApi;
	@Autowired
	private IWxMediaService wxMediaService;
	@Autowired
	private IWxMsgInputService wxMsgInputService;
	@Autowired
	private RecursiveUtil recursiveUtil;
	@Value("#{properties['wx_api_url']}")
	private String wxApiUrl;
	
	/**
	 * 跳转数据表列表页面 
	 *
	 * @Title: list 
	 * @return String
	 */
	@RequestMapping(value="list")
	public String list(Model model,@ModelAttribute("msg")String result){
		
		model.addAttribute("msg", result);
		return MEDTH_PATH + "list";
	}
	
	/**
	 * 获取页面列表展示数据
	 *
	 * @Title: getListData 
	 * @param request
	 * @param sessionInfo
	 * @return JSONObject
	 */
	@RequestMapping(value = "getListData.json", method = {RequestMethod.POST})
	@ResponseBody
	public JSONObject getListData(WebRequest request, @ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo) {
		BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
		PageInfo<WxUser> list = wxUserService.queryPage(query);
		JSONObject datas = new JSONObject();
		datas.put(Constants.DATAGRID_ROWS, list == null ? new ArrayList<WxUser>() : list.getList());
		datas.put(Constants.DATAGRID_TOTAL, list == null ? 0 : list.getTotal());
		return datas;
	}
	
	/**
	 * 同步 
	 *
	 * @Title: doSynchro
	 * @param id
	 * @return ResponseData
	 */
	@RequestMapping(value = "doSynchro.json")
	@ResponseBody
	public JSONObject doSynchro(WxUser wxUser) {
		JSONObject datas = new JSONObject();
		JSONObject jsonObject = wxUtilApi.getopenIdData(wxUser.getOpenid());
		if(jsonObject.get("errcode")== null){
			WxUser wxUsers = JSONObject.toJavaObject(jsonObject, WxUser.class);
			if(wxUsers.getSubscribe()){
				wxUsers.setId(wxUser.getId());
				wxUserService.update(wxUsers);
				datas.put("msg", "同步成功！");
			}
		}else{
			datas.put("msgs", jsonObject);
			datas.put("msg", "同步失败！");
		}
		return datas;
	}
	
	
	
	
	/**
	 * 
	 * @param model
	 * @param sessionInfo
	 * @param openId
	 * @return
	 */
	@RequestMapping(value="toGroupList")
	public String toGroupList(){
		return MEDTH_PATH + "groupDialog";
	}
	
	
	/*****************************************发送消息 Begin *****************************************/
	
	/**
	 * 到用户列表
	 *
	 * @Title: list 
	 * @return String
	 */
	@RequestMapping(value="userList")
	public String userList(Model model,@ModelAttribute("msg")String result){
		
		model.addAttribute("msg", result);
		return MEDTH_PATH + "userList";
	}
	
	/**
	 * 获取关注用户列表展示数据
	 *
	 * @Title: getListData 
	 * @param request
	 * @param sessionInfo
	 * @return JSONObject
	 */
	@RequestMapping(value = "getUserListData.json", method = {RequestMethod.POST})
	@ResponseBody
	public JSONObject getUserListData(WebRequest request, @ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo) {
		BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
		PageInfo<WxUser> list = wxUserService.queryPage(query);
		JSONObject datas = new JSONObject();
		datas.put(Constants.DATAGRID_ROWS, list == null ? new ArrayList<WxUser>() : list.getList());
		datas.put(Constants.DATAGRID_TOTAL, list == null ? 0 : list.getTotal());
		return datas;
	}
	
	/**
	 *  到向用户发送数据的页面
	 * @param model
	 * @param request
	 * @param openid
	 * @param sessionInfo
	 * @param id
	 * @return
	 */
	@RequestMapping(value="toUserInfoTmp")
	public String toUserInfoTmp(Model model,HttpServletRequest request,String openid,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,String inputId,String pageNum,String pageSize,String indexTemp){
		String token = IDGenarator.getUUID32();
		sessionInfo.setToken(token);
		BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
		if(pageNum !=null&&pageSize != null){
			query.setPageNum(Integer.valueOf(pageNum));
			query.setPageSize(Integer.valueOf(pageSize));
		}else{
			query.setPageNum(1);
			query.setPageSize(12);
		}
		PageInfo<WxMedia> page = wxMediaService.queryPage(query,MaterialType.NEWS,MaterialType.MPNEWS);
		List<WxMedia> wxMediaList = page.getList();
		//获取多图文素材对应关系
		JSONObject imageSrcMap =  wxMediaService.queryImageMap(BaseQuery.encapsulateQueryCondition(request));
		for (int i = 0; i < wxMediaList.size(); i++) {
			String content = wxMediaList.get(i).getContent();
			List<MaterialArticle> items = WxMessageBuilder.getMsgResponseNewsToList(content,imageSrcMap);
			wxMediaList.get(i).setNewsItems(items);
		}
		model.addAttribute("twxcmsMsgNewsList", wxMediaList);
		model.addAttribute("openid", openid);
		model.addAttribute("token", token);
		model.addAttribute("inputId", inputId);
		model.addAttribute("total",page.getTotal());
		model.addAttribute("pageNum",page.getPageNum());
		if(indexTemp!=null){
			model.addAttribute("indexTemp",indexTemp);
		}
		return MEDTH_PATH + "userInfoTmp";
	}
	
	/**
	 *  向用户发送数据
	 * @param model
	 * @param request
	 * @param sessionInfo
	 * @param msgtype
	 * @param openid
	 * @param inputId
	 * @param medias
	 * @param result
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="doUserInfoTmp", method = {RequestMethod.POST})
	public String doUserInfoTmp(Model model,HttpServletRequest request, @ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,
			String msgtype,String openid,String inputId,@RequestParam("medias") MultipartFile[] medias,RedirectAttributes result){	
		boolean flag = ValidateUtil.isRepeatSubmit(request.getParameter("token"), sessionInfo.getToken());
		if(!flag){
			BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
			JSONObject jsonObject = new JSONObject();
			String body = "";
			String  access_token = tokenUtils.getAccessToken();
			JSONObject bodyObj = new JSONObject();
			bodyObj.put("touser", openid);
			bodyObj.put("msgtype", msgtype);
			if(msgtype.equals("text")){//纯文本
				JSONObject  content = new JSONObject();
				String text = query.getQueryData().get("body").toString();
				content.put("content", text);
				bodyObj.put("text", content);
				body = bodyObj.toString();
			}else if(msgtype.equals("news")){
				JSONObject articles = new JSONObject();
				String article = query.getQueryData().get("articles").toString();
				//等待处理的对象
				List<News> waitProcess = new ArrayList<News>();	
				waitProcess = JSON.parseArray("["+article+"]", News.class);
				logger.info("******** waitProcess：" + JSON.toJSONString(waitProcess));
				List<String> thumbMediaIds = wxMediaService.insertTmpMaterial(medias, wxApiUrl, access_token,false);
				logger.info("******** thumbMediaIds：" + thumbMediaIds);
				if(thumbMediaIds.size()==waitProcess.size()){
					JSONObject news = new JSONObject();
					System.out.println(waitProcess.size());
					for (int i = 0; i < thumbMediaIds.size(); i++) {
					  waitProcess.get(i).setThumb_media_id(thumbMediaIds.get(i));
					}
					/****************************** ******************************/
					//首先生成多图文消息,这里比较特殊,生成的多图文消息有格式要求,要重新构造
					RestTemplate restTemplate = new RestTemplate(); 
					JSONObject uploadObj = new JSONObject();
					logger.info("******** " + articles.parse(JSON.toJSONString(waitProcess).toString()));
					uploadObj.put("articles", articles.parse(JSON.toJSONString(waitProcess).toString()));
					String uploadUrl = wxApiUrl + "cgi-bin/material/add_news?access_token="+access_token;
					String responesBody = restTemplate.postForObject(uploadUrl, uploadObj, String.class);
					logger.info("微信返回信息："+responesBody);
					JSONObject results = JSONObject.parseObject(responesBody);
					if(results.containsKey("errcode")){
						logger.error(result);
						logger.info("发送消息：生成在线的多图文永久素材失败,错误编码：" + results.getString("errcode"));
						result.addFlashAttribute("msg","发送消息：生成在线的多图文永久素材失败,错误编码："+results.getString("errcode"));
						return  "redirect:" + ACTION_PATH + "toUserInfoTmp.htm";
					}else{
						WxMedia  wxMedia = new WxMedia();
						wxMedia.setMediaId(results.getString("media_id"));
						wxMedia.setType("mpnews");
					    wxMedia.setContent(uploadObj.toJSONString());
					    wxMediaService.insert(wxMedia);
					    
					    //将转换好的结果重新返回
					    news.put("media_id", wxMedia.getMediaId());
					    bodyObj.put("msgtype", "mpnews");
					    bodyObj.put("mpnews",news);
					}
					/****************************** ******************************/
					body = bodyObj.toString();
				}else{
					model.addAttribute("msg", "发送失败！");
					logger.info("发送消息：上传在线编辑多图片素材失败,错误码："+jsonObject.getString("errcode"));
					result.addFlashAttribute("msg","发送消息：上传在线编辑多图片素材失败,错误码："+jsonObject.getString("errcode"));
					return  "redirect:" + ACTION_PATH + "toUserInfoTmp.htm";
				}
			}else if(msgtype.equals("mpnews")){//从素材库选择的多图文
				JSONObject  mediaId = new JSONObject();
				String media_id = query.getQueryData().get("mediaId").toString();
				mediaId.put("media_id",media_id);
				bodyObj.put("mpnews", mediaId);
				body = bodyObj.toString();
			}
			jsonObject = WxURIApi.httpsRequest(WxURIApi.getSendCustomMessageUrl(access_token),HttpMethod.POST.toString(), body);
			if(jsonObject.getString("errmsg").equals("ok") || jsonObject.getString("errcode").equals("0")){
				if(inputId!=null){
					WxMsgInput wxMsgInput = new WxMsgInput();
					wxMsgInput.setId(inputId);
					wxMsgInput.setIsReply(1);
					wxMsgInputService.update(wxMsgInput);
				}
				result.addFlashAttribute("msg","发送成功！");
			}else{
				logger.info("发送消息：给："+openid+"发送消息失败,错误码："+jsonObject.getString("errcode"));
				result.addFlashAttribute("msg","发送消息：给："+openid+"发送消息失败,错误码："+jsonObject.getString("errcode"));
			}
			sessionInfo.setToken(null);
			return  "redirect:" + ACTION_PATH + "userList.htm";
		}else{
			result.addFlashAttribute("msg","您点的太快啦，等一会儿吧！(*^__^*)");			
			return  "redirect:" + ACTION_PATH + "userList.htm";
		}
		
	}
	
	/*****************************************发送消息 End *****************************************/
	
	/*****************************************群发 Begin *****************************************/
	
	/**
	 * 到微信用户群发页面
	 *
	 * @Title: list 
	 * @return String
	 */
	@RequestMapping(value="toInfoList")
	public String toInfoList(Model model,@ModelAttribute("msg")String result,HttpServletRequest request,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,String pageNum,String pageSize,String indexTemp){
		String token = IDGenarator.getUUID32();
		sessionInfo.setToken(token);
		BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
		if(pageNum !=null&&pageSize != null){
			query.setPageNum(Integer.valueOf(pageNum));
			query.setPageSize(Integer.valueOf(pageSize));
		}else{
			query.setPageNum(1);
			query.setPageSize(12);
		}
		PageInfo<WxMedia> page = wxMediaService.queryPage(query,MaterialType.NEWS,MaterialType.MPNEWS);
		List<WxMedia> wxMediaList = page.getList();
		//获取多图文素材对应关系
		JSONObject imageSrcMap =  wxMediaService.queryImageMap(BaseQuery.encapsulateQueryCondition(request));
		for (int i = 0; i < wxMediaList.size(); i++) {
			String content = wxMediaList.get(i).getContent();
			List<MaterialArticle> items = WxMessageBuilder.getMsgResponseNewsToList(content,imageSrcMap);
			wxMediaList.get(i).setNewsItems(items);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("deleted", 0);
		List<WxGroup> wxGroupList = wxGroupService.query(map);
		model.addAttribute("wxGroupList", wxGroupList);
		model.addAttribute("twxcmsMsgNewsList", wxMediaList);
		model.addAttribute("token", token);
		model.addAttribute("msg", result);
		model.addAttribute("total",page.getTotal());
		model.addAttribute("pageNum",page.getPageNum());
		if(indexTemp!=null){
			model.addAttribute("indexTemp",indexTemp);
		}
		return MEDTH_PATH + "infoList";
	}
	
	/**
	 * 群发消息
	 * @param request
	 * @param msgtype
	 * @param groupId 群发用户组
	 * @return
	 */
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="doInfos", method = {RequestMethod.POST})
	public String doInfos(Model model,HttpServletRequest request,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,String msgtype,String groupId,@RequestParam("medias") MultipartFile[] medias,RedirectAttributes result){
		boolean flag = ValidateUtil.isRepeatSubmit(request.getParameter("token"), sessionInfo.getToken());
		if(!flag){
			BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
			JSONObject jsonObject = new JSONObject();
			String body = "";
			String  access_token = tokenUtils.getAccessToken();
			JSONObject bodyObj = new JSONObject();
			JSONObject groupIdObj = new JSONObject();
			if(groupId.equals("all")){
				groupIdObj.put("is_to_all", true);//获取用户组
				bodyObj.put("filter", groupIdObj);
			}else{
				groupIdObj.put("group_id", groupId);//获取用户组
				bodyObj.put("filter", groupIdObj);
			}
			bodyObj.put("msgtype", msgtype);
			if(msgtype.equals("text")){//纯文本
				JSONObject  content = new JSONObject();
				String text = query.getQueryData().get("body").toString();
				content.put("content", text);
				bodyObj.put("text", content);
				body = bodyObj.toString();
			}else if(msgtype.equals("news")){//微信群发没有提供此功能,伪造一个
				JSONObject articles = new JSONObject();
				String article = query.getQueryData().get("articles").toString();
				//等待处理的对象
				List<News> waitProcess = new ArrayList<News>();	
				waitProcess = JSON.parseArray("["+article+"]", News.class);
				List<String> thumbMediaIds = wxMediaService.insertTmpMaterial(medias, wxApiUrl, access_token,false);
				if(thumbMediaIds.size()==waitProcess.size()){
					JSONObject news = new JSONObject();
					System.out.println(waitProcess.size());
					for (int i = 0; i < thumbMediaIds.size(); i++) {
					  waitProcess.get(i).setThumb_media_id(thumbMediaIds.get(i));
					}
					System.out.println(JSON.toJSONString(waitProcess).toString());
					/****************************** ******************************/
					//首先生成多图文消息,这里比较特殊,生成的多图文消息有格式要求,要重新构造
					RestTemplate restTemplate = new RestTemplate(); 
					JSONObject uploadObj = new JSONObject();
					System.out.println(articles.parse(JSON.toJSONString(waitProcess).toString()));
					uploadObj.put("articles", articles.parse(JSON.toJSONString(waitProcess).toString()));
					String uploadUrl = wxApiUrl + "cgi-bin/material/add_news?access_token="+access_token;
					String responesBody = restTemplate.postForObject(uploadUrl, uploadObj, String.class);
					logger.info("微信返回信息："+responesBody);
					JSONObject results = JSONObject.parseObject(responesBody);
					if(results.containsKey("errcode")){
						logger.error(result);
						logger.info("群发消息：生成在线的多图文永久素材失败,错误编码："+results.getString("errcode")+",错误信息："+Constants.ERRCODE.get(results.getString("errcode")));
						result.addFlashAttribute("msg","发送失败！错误编码："+results.getString("errcode")+",错误信息："+Constants.ERRCODE.get(results.getString("errcode")));
						return  "redirect:" + ACTION_PATH + "toInfoList.htm";
					} else {
						WxMedia  wxMedia = new WxMedia();
						wxMedia.setMediaId(results.getString("media_id"));
						wxMedia.setType("mpnews");
					    wxMedia.setContent(uploadObj.toJSONString());
					    wxMediaService.insert(wxMedia);
					    
					    //将转换好的结果重新返回
					    news.put("media_id", wxMedia.getMediaId());
					    bodyObj.put("msgtype", "mpnews");
					    bodyObj.put("mpnews",news);
					}
					/****************************** ******************************/
					body = bodyObj.toString();
				}else{
					model.addAttribute("msg", "发送失败！");
					logger.info("群发消息：上传在线编辑多图片素材失败,错误码："+jsonObject.getString("errcode"));
					result.addFlashAttribute("msg","群发消息：上传在线编辑多图片素材失败,错误码："+jsonObject.getString("errcode"));
					return  "redirect:" + ACTION_PATH + "toInfoList.htm";
				}
			}else if(msgtype.equals("mpnews")){//从素材库选择的多图文
				JSONObject  mediaId = new JSONObject();
				String media_id = query.getQueryData().get("mediaId").toString();
				mediaId.put("media_id",media_id);
				bodyObj.put("mpnews", mediaId);
				body = bodyObj.toString();
				//45028 表示测试号没有此功能
			}
			jsonObject = WxURIApi.httpsRequest(WxURIApi.getMassSendUrl(access_token),HttpMethod.POST.toString(), body);
			logger.info("微信返回信息："+jsonObject);
			if(jsonObject.getString("errmsg").equals("ok") || jsonObject.getString("errcode").equals("0")){
				result.addFlashAttribute("msg","发送成功！");
			}else{
				if(jsonObject.get("errcode").toString().equals("45028")){
					result.addFlashAttribute("msg","没有群发的配额 ");
					logger.info("群发消息：发送消息失败,错误码："+jsonObject.getString("errcode"));
					return  "redirect:" + ACTION_PATH + "toInfoList.htm";
				}
				result.addFlashAttribute("msg","发送失败！");
				logger.info("群发消息：发送消息失败,错误码："+jsonObject.getString("errcode"));
			}
			sessionInfo.setToken(null);
			return  "redirect:" + ACTION_PATH + "toInfoList.htm";
		}else{
			result.addFlashAttribute("msg","您点的太快啦，等一会儿吧！(*^__^*)");
			return  "redirect:" + ACTION_PATH + "toInfoList.htm";
		}
	}
	
	/*****************************************群发 End *****************************************/
	
	/**
	 * 同步用户
	 * @param request
	 * @return JSONObject
	 */
	@RequestMapping(value="doAllSynchro.json", method={RequestMethod.POST})
	@ResponseBody
	public JSONObject doSynchro(HttpServletRequest request){
		//调用微信api查询用户列表
		RestTemplate restTemplate = new RestTemplate();
		String access_token = tokenUtils.getAccessToken();
		String next_openid = "";

		List<WxUser> wxUser = new ArrayList<>();
		List<WxUser> addUser = new ArrayList<>();
		List<WxUser> updateUser = new ArrayList<>();
		
		JSONObject json = recursiveUtil.sendRequest(access_token,next_openid,restTemplate);
		int total = (int)json.get("total");
		if(total > 0){
			wxUser = recursiveUtil.getUserList(json);
			for(WxUser u:wxUser){
				//根据openid查询用户是否存在
				if(wxUserService.queryByOpenId(u.getOpenid()) != null) {
					updateUser.add(u);
				}else {
					addUser.add(u);
				}
			}
			if(updateUser.size() != 0) {
				wxUserService.update(updateUser);
			}
			if(addUser.size() != 0) {
				wxUserService.add(addUser);
			}
			int i = ((int) Math.ceil(((double)total)/10000))-1;//需要请求的次数
			for(int m = 0; m < i; m++){
				//初始化需要更新的用户列表
				addUser = new ArrayList<>();
				updateUser = new ArrayList<>();
				next_openid = Objects.toString(json.get("next_openid"));
				json = recursiveUtil.sendRequest(access_token,next_openid,restTemplate);
				wxUser = recursiveUtil.getUserList(json);
				for(WxUser u:wxUser){
					//根据openid查询用户是否存在
					if(wxUserService.queryByOpenId(u.getOpenid()) != null) {
						updateUser.add(u);
					}else {
						addUser.add(u);
					}
				}
				if(updateUser.size() != 0) {
					wxUserService.update(updateUser);
				}
				if(addUser.size() != 0) {
					wxUserService.add(addUser);
				}
			}
		}
		JSONObject result = new JSONObject();
		result.put("msg", "同步成功!");
		return result;
	}
	
}