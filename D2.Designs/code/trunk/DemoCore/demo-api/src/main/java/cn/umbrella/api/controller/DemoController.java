package cn.umbrella.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.lang.model.util.Elements;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.umbrella.api.config.Constant;
import cn.umbrella.api.config.PageConfig;
import cn.umbrella.api.enums.AccessCode;
import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.commons.util.apache.BeanUtils;
import cn.umbrella.commons.validate.ValidateUtil;
import cn.umbrella.form.ContentForm;
import cn.umbrella.po.ContentNeed;
import cn.umbrella.po.SysSessionInfo;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping(DemoController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class DemoController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH = "/demo";
	protected static final String PAGE_PATH = "/page/demo/";
	
	/**
	 * 获取栏目内容 
	 *
	 * @Title: getChannelContents 
	 * @param channelId
	 * @param keyword
	 * @param pageNumber
	 * @param pageSize
	 * @return JSONObject
	 */
	@RequestMapping(value = "getChannelContents.json", method = {RequestMethod.POST})
	@ResponseBody
	public JSONObject getChannelContents(String channelId, String keyword, Integer pageNumber, Integer pageSize) {
		JSONObject returnJsonObj = new JSONObject();
		
		if (StringUtils.isBlank(channelId)) {
			returnJsonObj.put(PageConfig.RETURNCODE, AccessCode.PARAMETER_ERROR.getValue());
			returnJsonObj.put(PageConfig.RETURNMSG, AccessCode.PARAMETER_ERROR.getName());
			returnJsonObj.put(PageConfig.DATA, new JSONObject());
			return returnJsonObj; 
		}
		
		BaseQuery query = new BaseQuery();
		if (null != pageNumber) {
			query.setPageNum(pageNumber);
		} else {
			pageNumber = query.getPageNum();
		}
		if (null != pageSize) {
			query.setPageSize(pageSize);
		} else {
			pageSize = query.getPageSize();
		}
//		query.addParam("sysId", sysId);
		query.addParam("keywordForFront", keyword);
		query.addParam("channelIdForFront", channelId);
		query.addParam("queryOrderInfo", "a.top_level DESC, b.release_date DESC");
		
		JSONObject data = new JSONObject();
		PageInfo<?> list = null;//contentService.queryForFront(query);
		List<?> contents = new ArrayList<?>();
		long total = 0;
		if (null != list) {
			contents = list.getList();
			total = list.getTotal();
		}
		List<JSONObject> objs = new ArrayList<JSONObject>();
		if (null != contents && contents.size() > 0) {
			for (ContentNeed content : contents) {
				JSONObject obj = new JSONObject();
				obj.put("contentId", content.getContentId());
				obj.put("title", content.getTitle());
				obj.put("viewsCount", content.getViewsCount());
				obj.put("releaseDate", content.getReleaseDate());
				if (Objects.equals("vxy", channelId)) {
					obj.put("vedioUrl", content.getTxt());
				}
				
				Boolean hasTitleImg = content.getHasTitleImg();
				String titleImg = content.getTitleImg();
				if (null == hasTitleImg || hasTitleImg == false) {
					titleImg = Objects.toString(titleImg, "");
				} else {
					titleImg = title_img_download_path + titleImg;
				}
				obj.put("titleImg", titleImg);
				objs.add(obj);
			}
		}
		
		data.put(PageConfig.ROWS, objs);
		data.put(PageConfig.TOTAL, total);
		data.put(PageConfig.PAGENUMBER, pageNumber);
		data.put(PageConfig.PAGESIZE, pageSize);
		
		returnJsonObj.put(PageConfig.RETURNCODE, AccessCode.SUCCESS.getValue());
		returnJsonObj.put(PageConfig.RETURNMSG, AccessCode.SUCCESS.getName());
		returnJsonObj.put(PageConfig.DATA, data);
		return returnJsonObj; 
	}
	
	@RequestMapping(value="doAddWithAttach.json", method={RequestMethod.POST})
	@ResponseBody
	public String doAddWithAttach(@ModelAttribute(Constant.CREDIT_SESSION) SysSessionInfo sessionInfo,
			@Valid ContentForm contentForm, BindingResult result,RedirectAttributes redirectAttributes
			,@RequestParam(value = "titleImgFile", required = false) MultipartFile file){
		JSONObject response = new JSONObject();
		if(!file.isEmpty()){
			contentForm.setHasTitleImg(1);
			String fileName = file.getOriginalFilename();
			if(!"".equals(fileName)){
				int k = fileName.lastIndexOf("."); 
				String str = fileName.substring(k+1, fileName.length());
				if("gif".equals(str) || "jpg".equals(str) || "png".equals(str) || "bmp".equals(str)){
					
				}else{
					ModelAndView modelAndView = new ModelAndView(adminFtlPath+"/content/add",result.getModel());
					modelAndView.addObject("msg", "上传图片格式错误");
					Map<String, Object> map_ = new HashMap<String, Object>();
					map_.put("channelId", contentForm.getChannelId());
					map_.put("deleted", 0);
					List<Channel> channelList = iChannelService.query(map_);
					if(channelList.size()>0){
						modelAndView.addObject("channelName", channelList.get(0).getChannelName());
					}
					contentForm.setReleaseDate(new Date());
					response.put("contentForm", contentForm);
					response.put("validate", ValidateUtil.getValidate(contentForm.getClass()));
					
					return response.toJSONString();
				}
			}
			
		}
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(adminFtlPath+"/content/add",result.getModel());
			Map<String, Object> map_ = new HashMap<String, Object>();
			map_.put("channelId", contentForm.getChannelId());
			map_.put("deleted", 0);
			List<Channel> channelList = iChannelService.query(map_);
			if(channelList.size()>0)
				modelAndView.addObject("channelName", channelList.get(0).getChannelName());
			contentForm.setReleaseDate(new Date());
			response.put("contentForm", contentForm);
			response.put("validate", ValidateUtil.getValidate(contentForm.getClass()));
			
			return response.toJSONString();
		}
		//头条必须有标题图判断
		if(contentForm.getIsRecommend()!=null&&1==contentForm.getIsRecommend()){
			if(contentForm.getHasTitleImg()==null||0==contentForm.getHasTitleImg()||file.isEmpty()){
				response.put("msg", "头条必须有标题图片");
				response.put("contentForm", contentForm);
				
				return response.toJSONString();
			}
		}
		//有标题图，但是没有选择上传图片
		if(contentForm.getHasTitleImg()!=null&&1==contentForm.getHasTitleImg()&&file.isEmpty()){
			response.put("msg", "请选择标题图");
			response.put("contentForm", contentForm);
			
			return response.toJSONString();
		}
		String separator = System.getProperty("file.separator");
		String contentid = UUID.randomUUID().toString().replace("-", "");
		contentForm.setContentId(contentid);
		//v信用，截取视频url，覆盖txt字段
		if("vxy".equals(contentForm.getChannelId())){
			String txt = contentForm.getTxt();
			if(txt!=null&&txt.trim().length()>0){
				org.jsoup.nodes.Document doc = Jsoup.parse(txt);
				Elements ele = doc.select(".edui-upload-video");
				if(ele.size()>0){
					String src = ele.get(0).attr("src");
					contentForm.setTxt(src);
				}
			}
			
		}
		
		//Begin Get User Top 1 Group
		List<SysGroup> groups = sessionInfo.getMyGroups();
		String siteId = groups.get(0).getGroupId().toString();
		//End Get User Top 1 Group
		Content content = new Content();
		//默认不显示0
		if(null==contentForm.getIsAppDisplay())contentForm.setIsAppDisplay(0);
		BeanUtils.copyProperties(contentForm,content);
		content.setSysId(sysId);
		content.setSiteId(siteId);
		content.setUserId(sessionInfo.getMyUser().getUserId());
		if(null!=content.getTxt()){
		String tempStr = content.getTxt().replaceAll(fromUeditorHomeUrl, targetUeditorHomeUrl);
		content.setTxt(tempStr);
		}
		//设置状态
		content.setStatus(ContentStatus.REVIEWING.getValue());
		content.setCtime(new Date());
		content.setCreaterId(sessionInfo.getMyUser().getUserId());
		content.setCreater(sessionInfo.getMyUser().getName());
		content.setViewsDay(new Integer(0));
		content.setCommentsDay(new Integer(0));
		content.setDownloadsDay(new Integer(0));
		content.setUpsDay(new Integer(0));
		content.setScore(new Integer(0));
		content.setSiteId(siteId);
		if(!"0".equals(content.getLink().trim())){
			content.setLink(content.getOriginUrl());
		}else{
			content.setLink(null);
		}
		content.setSysId(sysId);
		content.setIsBold(false);
		content.setNeedRegenerate(false);
		content.setDeleted(0);
		if(!file.isEmpty()){
			String name = file.getOriginalFilename();
			int index = name.lastIndexOf(".");
			String type = name.substring(index, name.length());
			String imgName = UUID.randomUUID().toString().replace("-", "");
			
			File path = new File(titleImgStore);
			if (!path.exists()) {
				path.mkdirs();
			}
			
			File img = new File(titleImgStore+separator+imgName+type);
			try {
				if(img.createNewFile()){
					file.transferTo(img);
//					content.setTitleImg(titleImgGet+contentid+type);
					content.setTitleImg(imgName+type);
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		iContentService.add(content);
		addIndex(content);
		redirectAttributes.addFlashAttribute("msg","添加成功！");
		redirectAttributes.addFlashAttribute("opFlag","true");
		
		response.put("status", "101");
		response.put("msg", "添加成功!");
		response.put("opFlag", true);
		return response.toJSONString();
	}
}
