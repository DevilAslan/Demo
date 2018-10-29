package cn.umbrella.oss.controller.wechat;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.commons.validate.ValidateUtil;
import cn.umbrella.conmmons.genarator.IDGenarator;
import cn.umbrella.mboss.po.SysUser;
import cn.umbrella.oss.config.Constant;
import cn.umbrella.oss.model.wechat.TwxcmsMsgBaseForm;
import cn.umbrella.oss.service.wechat.IWxMediaService;
import cn.umbrella.oss.utils.wechat.TokenUtils;
import cn.umbrella.oss.utils.wechat.WxMessageBuilder;
import cn.umbrella.oss.vo.MySessionInfo;
import cn.umbrella.oss.wechat.bean.TwxcmsMsgBase;
import cn.umbrella.oss.wechat.bean.TwxcmsMsgText;
import cn.umbrella.oss.wechat.bean.WxMedia;
import cn.umbrella.oss.wechat.form.MaterialArticle;
import cn.umbrella.oss.wechat.form.MaterialType;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(WXMsgTextController.ACTION_PATH)
@SessionAttributes(Constant.MY_SESSION_INFO)
public class WXMsgTextController {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected static final String ACTION_PATH = "/msgText/";
	protected static final String MODEL_PATH = "";
	
	@Autowired
	private TokenUtils tokenUtils;
	
//	@Autowired
//	private ITwxcmsMsgTextService iTwxcmsMsgTextService;
	
	@Autowired
	private IWxMediaService wxMediaService;
	
	/**
	 * 跳转数据表列表页面 
	 *
	 * @Title: list 
	 * @return String
	 */
	@RequestMapping(value="list")
	public String list(Model model,@ModelAttribute("msg")String result){
		
		model.addAttribute("msg", result);
		return "wxcms" + ACTION_PATH + "list";
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
	public JSONObject getListData(WebRequest request) {
		BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
//		PageInfo<TwxcmsMsgText> list = iTwxcmsMsgTextService.queryPage(query);
		PageInfo<TwxcmsMsgText> list = null;
		JSONObject datas = new JSONObject();
		datas.put("rows", list.getList());
		datas.put("total", list.getTotal());
		return datas;
	}
	
	/**
	 * 新增
	 *
	 * @Title: add 
	 * @param model
	 * @param sessionInfo
	 * @return String
	 */
	@RequestMapping(value="add")
	public String add(Model model,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo){
		TwxcmsMsgBaseForm twxcmsMsgBaseForm = new TwxcmsMsgBaseForm();
		String token = IDGenarator.getUUID32();
		twxcmsMsgBaseForm.setToken(token);
		sessionInfo.setToken(token);
		model.addAttribute("twxcmsMsgBaseForm", twxcmsMsgBaseForm);
		model.addAttribute("validate", ValidateUtil.getValidate(TwxcmsMsgBaseForm.class));
		return  "wxcms" + ACTION_PATH + "add";
	}
	
	/**
	 * 提交新增
	 *
	 * @Title: add 
	 * @param model
	 * @param wxMenuForm
	 * @param result
	 * @param sessionInfo
	 * @return String
	 */
	@RequestMapping(value="doAdd", method = {RequestMethod.POST})
	public String doAdd(Model model, @Valid TwxcmsMsgBaseForm twxcmsMsgBaseForm,  BindingResult results,RedirectAttributes result,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo){
		boolean flag = ValidateUtil.isRepeatSubmit(twxcmsMsgBaseForm.getToken(), sessionInfo.getToken());
		if(!flag){
			TwxcmsMsgBase twxcmsMsgBase = new TwxcmsMsgBase();
			BeanUtils.copyProperties(twxcmsMsgBaseForm, twxcmsMsgBase);
			String content = twxcmsMsgBaseForm.getContent();
			SysUser sysUser = sessionInfo.getMyUser();
			WxMedia wxMedia = new WxMedia();
			wxMedia.setContent(content);
			wxMedia.setCreaterId(sysUser.getUserId());
			wxMedia.setCreater(sysUser.getName());
//			iTwxcmsMsgTextService.add(twxcmsMsgBase,wxMedia);
			result.addFlashAttribute("msg", "保存成功！");
			sessionInfo.setToken(null);
			return  "redirect:" + ACTION_PATH + "list.htm";
		}else{
			result.addFlashAttribute("msg","您点的太快啦，等一会儿吧！(*^__^*)");
			return  "redirect:" + ACTION_PATH + "list.htm";
		}
		
	}
	
	/**
	 * 新增
	 *
	 * @Title: add 
	 * @param model
	 * @param sessionInfo
	 * @return String
	 */
	@RequestMapping(value="addNews")
	public String addNews(Model model,WebRequest request){
		//String  access_token = tokenUtils.getAccessToken();
		
//		TwxcmsMsgNewsForm twxcmsMsgNewsForm = new TwxcmsMsgNewsForm();
		String token = IDGenarator.getUUID32();
//		twxcmsMsgNewsForm.setToken(token);
	
		
		//JSONObject bodyObj = new JSONObject();
		//bodyObj.put("type", MediaType.News.toString());
		//bodyObj.put("offset", 0);
		//bodyObj.put("count", 20);
		//String body = bodyObj.toString();
		
		//JSONObject newJsonObject= WxURIApi.httpsRequest(WxURIApi.getBatchMaterialUrl(access_token),HttpMethod.POST.toString(), body);
		//String str = "{\"item\":[{\"content\":{\"news_item\":[{\"content\":\"正文\",\"author\":\"何帆\",\"title\":\"我的标题啊!\",\"thumb_media_id\":\"WM3HG3egQBFL1tWP1PgL3NOhz1zCqlC9xZI1ymn62pA\",\"thumb_url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/hEZoZE6VPrDmbsPrHCQLzJxk122iajWZ3up1rNdxhc9SLUp0RxX56bkdJbOAjenibpOjIEMvrUeOg96KxicWQ2a5w/0?wx_fmt=jpeg\",\"show_cover_pic\":1,\"content_source_url\":\"http://www.baidu.com\",\"digest\":\"\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzIzMzQxNDQ4Nw==&mid=100000006&idx=1&sn=adae5e601be1d5d67be964b5c25e3523&chksm=68874b3e5ff0c2283ff7a969b5b9cd0f3af9197d3ec79aa567307e8463e1e53380a3afb8998d#rd\"},{\"content\":\"正文2\",\"author\":\"何帆2\",\"title\":\"我的标题啊2!\",\"thumb_media_id\":\"WM3HG3egQBFL1tWP1PgL3J6luiLsx4PGzvZbKWDekOs\",\"thumb_url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/hEZoZE6VPrDmbsPrHCQLzJxk122iajWZ31T41ERIUGE56KO2mwgI80SsyL6u4WaufEYickJwDTl006bN7flekjFw/0?wx_fmt=jpeg\",\"show_cover_pic\":1,\"content_source_url\":\"http://www.baidu.com\",\"digest\":\"\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzIzMzQxNDQ4Nw==&mid=100000006&idx=2&sn=acdfae7acd80b6e61ea03a1fc16145de&chksm=68874b3e5ff0c2283823a469d796eddcfaebe0e9738e447cce2831bf58213f0eca87d18094e0#rd\"}],\"update_time\":1488341485,\"create_time\":1488341485},\"update_time\":1488341485,\"media_id\":\"WM3HG3egQBFL1tWP1PgL3FSBdFXZCvRNSRgApjPfJu8\"},{\"content\":{\"news_item\":[{\"content\":\"正文\",\"author\":\"何帆\",\"title\":\"我的标题啊!\",\"thumb_media_id\":\"WM3HG3egQBFL1tWP1PgL3NOhz1zCqlC9xZI1ymn62pA\",\"thumb_url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/hEZoZE6VPrDmbsPrHCQLzJxk122iajWZ3up1rNdxhc9SLUp0RxX56bkdJbOAjenibpOjIEMvrUeOg96KxicWQ2a5w/0?wx_fmt=jpeg\",\"show_cover_pic\":1,\"content_source_url\":\"http://www.baidu.com\",\"digest\":\"\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzIzMzQxNDQ4Nw==&mid=100000004&idx=1&sn=ea2fc4040d75046708a9c4647e1b3edd&chksm=68874b3c5ff0c22a68f15bbbdb8107825a49639fc8775d34acc240f5139657d0840daf4316a8#rd\"},{\"content\":\"正文2\",\"author\":\"何帆2\",\"title\":\"我的标题啊2!\",\"thumb_media_id\":\"WM3HG3egQBFL1tWP1PgL3J6luiLsx4PGzvZbKWDekOs\",\"thumb_url\":\"http://mmbiz.qpic.cn/mmbiz_jpg/hEZoZE6VPrDmbsPrHCQLzJxk122iajWZ31T41ERIUGE56KO2mwgI80SsyL6u4WaufEYickJwDTl006bN7flekjFw/0?wx_fmt=jpeg\",\"show_cover_pic\":1,\"content_source_url\":\"http://www.baidu.com\",\"digest\":\"\",\"url\":\"http://mp.weixin.qq.com/s?__biz=MzIzMzQxNDQ4Nw==&mid=100000004&idx=2&sn=ed10ebe459399d293dd79e49cf725b26&chksm=68874b3c5ff0c22a249f1ad85a6587df7772b135a40456bc8e16675c527c67176c422f53bf53#rd\"}],\"update_time\":1488341029,\"create_time\":1488341029},\"update_time\":1488341029,\"media_id\":\"WM3HG3egQBFL1tWP1PgL3O3TkPGBFMiHzP8BIAOkZ1I\"}],\"item_count\":2,\"total_count\":2}";
		BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
		PageInfo<WxMedia> page = wxMediaService.queryPage(query,MaterialType.NEWS);
		List<WxMedia> wxMediaList = page.getList();
		//获取多图文素材对应关系
		JSONObject imageSrcMap =  wxMediaService.queryImageMap(BaseQuery.encapsulateQueryCondition(request));
		for (int i = 0; i < wxMediaList.size(); i++) {
			String content = wxMediaList.get(i).getContent();
			List<MaterialArticle> items = WxMessageBuilder.getMsgResponseNewsToList(content,imageSrcMap);
			wxMediaList.get(i).setNewsItems(items);
		}
		JSONObject newJsonObject =  new JSONObject();
		
		if (newJsonObject.containsKey("errcode")) {//获取素材失败
			logger.info("获取素材失败!");
			return null;
		}else{
			logger.info("获取素材成功!");
			//解析
			//Material material = WxMessageBuilder.getMsgResponseNews(newJsonObject.parse(str).toString());
			//Material material = WxMessageBuilder.getMsgResponseNews(newJsonObject.toJSONString());
			
//			model.addAttribute("twxcmsMsgNewsForm", twxcmsMsgNewsForm);
//			model.addAttribute("validate", ValidateUtil.getValidate(TwxcmsMsgNewsForm.class));
			model.addAttribute("twxcmsMsgNewsList", wxMediaList);
			model.addAttribute("imageSrcMap", imageSrcMap);
			return  "wxcms" + ACTION_PATH + "addNews";
		}
	}
	
	@RequestMapping(value="doNewAdd", method = {RequestMethod.POST})
	public String doNewAdd(Model model, @Valid TwxcmsMsgBaseForm twxcmsMsgBaseForm, BindingResult results,RedirectAttributes result,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo){
		boolean flag = ValidateUtil.isRepeatSubmit(twxcmsMsgBaseForm.getToken(), sessionInfo.getToken());
		if(!flag){
			//根据永久素材中mediaId为当前选中的这条素材,更新关键字
			String newId = twxcmsMsgBaseForm.getId();
			TwxcmsMsgBase twxcmsMsgBase = new TwxcmsMsgBase();
			BeanUtils.copyProperties(twxcmsMsgBaseForm, twxcmsMsgBase);
			twxcmsMsgBase.setMsgType("news");
//			iTwxcmsMsgTextService.insertByMediaId(twxcmsMsgBase,newId);
			result.addFlashAttribute("msg", "保存成功！");
			sessionInfo.setToken(null);
			return  "redirect:" + ACTION_PATH + "list.htm";
		}else{
			result.addFlashAttribute("msg","您点的太快啦，等一会儿吧！(*^__^*)");
			return  "redirect:" + ACTION_PATH + "list.htm";
		}
	}
	
	
	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="toEdit")
	public String toEdit(Model model,WebRequest request,String baseId,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo){
//		TwxcmsMsgBase twxcmsMsgBase = iTwxcmsMsgTextService.getMediaByBaseId(baseId);
		TwxcmsMsgBaseForm twxcmsMsgBaseForm = new TwxcmsMsgBaseForm();
//		BeanUtils.copyProperties(twxcmsMsgBase, twxcmsMsgBaseForm);
		
		String token = IDGenarator.getUUID32();
		twxcmsMsgBaseForm.setToken(token);
		sessionInfo.setToken(token);
		
		model.addAttribute("twxcmsMsgBaseForm", twxcmsMsgBaseForm);
//		if(twxcmsMsgBase.getMsgType().equals("text")){
//			return "wxcms" + ACTION_PATH + "updateText";
//		}else if(twxcmsMsgBase.getMsgType().equals("news")){
			
			BaseQuery query = BaseQuery.encapsulateQueryCondition(request);
			PageInfo<WxMedia> page = wxMediaService.queryPage(query,MaterialType.NEWS);
			List<WxMedia> wxMediaList = page.getList();
			//获取多图文素材对应关系
			JSONObject imageSrcMap =  wxMediaService.queryImageMap(BaseQuery.encapsulateQueryCondition(request));
			for (int i = 0; i < wxMediaList.size(); i++) {
				String content = wxMediaList.get(i).getContent();
				List<MaterialArticle> items = WxMessageBuilder.getMsgResponseNewsToList(content,imageSrcMap);
				wxMediaList.get(i).setNewsItems(items);
			}
			model.addAttribute("twxcmsMsgNewsList", wxMediaList);
			return "wxcms" + ACTION_PATH + "updateNews";
//		}
//		return null;
	}
	
	/**
	 * 保存编辑
	 * @param model
	 * @param twxcmsMsgBaseForm
	 * @param result
	 * @param sessionInfo
	 * @return
	 */
	@RequestMapping(value="doEdit", method = {RequestMethod.POST})
	public String doEdit(Model model, @Valid TwxcmsMsgBaseForm twxcmsMsgBaseForm,RedirectAttributes result, BindingResult results,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo){
		boolean flag = ValidateUtil.isRepeatSubmit(twxcmsMsgBaseForm.getToken(), sessionInfo.getToken());
		if(!flag){
			TwxcmsMsgBase twxcmsMsgBase = new TwxcmsMsgBase();
			BeanUtils.copyProperties(twxcmsMsgBaseForm, twxcmsMsgBase);
			int row = 0;
//			int row = iTwxcmsMsgTextService.updateByMediaId(twxcmsMsgBase);
			if(row>0){
				result.addFlashAttribute("msg", "更新成功！");
			}else{
				result.addFlashAttribute("msg", "更新失败！");
			}
			sessionInfo.setToken(null);
			return  "redirect:" + ACTION_PATH + "list.htm";
		}else{
			result.addFlashAttribute("msg","您点的太快啦，等一会儿吧！(*^__^*)");
			return  "redirect:" + ACTION_PATH + "list.htm";
		}
		
	}
	
	@RequestMapping(value="doDeleted", method = {RequestMethod.POST})
	@ResponseBody
	public JSONObject doDeleted(Model model, @ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,String[] ids){
		JSONObject datas = new JSONObject();
		int row = 0;
//		int row = iTwxcmsMsgTextService.deleteBatch(ids);
		if(row>0){
			datas.put("msg", "删除成功！");
		}else{
			datas.put("msg", "删除失败！");
		}
		return datas;
	}
	
}
