package cn.umbrella.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.umbrella.api.config.Constant;
import cn.umbrella.api.config.PageConfig;
import cn.umbrella.api.enums.AccessCode;
import cn.umbrella.commons.bean.BaseBean;
import cn.umbrella.commons.bean.BaseQuery;

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
	public String doContentUpdateSave(
			@Valid BaseBean baseBean,
			,@RequestParam(value = "fileId", required = false) MultipartFile file
			){
		JSONObject response = new JSONObject();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			if(!"".equals(fileName)){
				int k = fileName.lastIndexOf("."); 
				String str = fileName.substring(k+1, fileName.length());
				if("gif".equals(str) || "jpg".equals(str) || "png".equals(str) || "bmp".equals(str)){
					
				}else{
					response.put("msg", "上传图片格式错误");
					response.put("contentForm", contentForm);
					return response.toJSONString();
				}
			}
			
		}
		if(result.hasErrors()) {
			List<ChannelExt> channelList = iChannelExtService.query(new HashMap<String,Object>());
			modelAndView.addObject("channelList", channelList);
			contentForm.setReleaseDate(new Date());
			response.put("contentForm", contentForm);
			response.put("validate", ValidateUtil.getValidate(contentForm.getClass()));
			return response.toJSONString();
		}
		//头条必须有标题图判断
		if(contentForm.getIsRecommend()!=null&&1==contentForm.getIsRecommend()){
			if(contentForm.getHasTitleImg()==null||0==contentForm.getHasTitleImg()){
				response.put("msg", "头条必须有标题图片");
				response.put("contentForm", contentForm);
				return response.toJSONString();
			}else if(file.isEmpty()){
				Content content = iContentService.queryById(contentForm.getContentId());
				if(content.getHasTitleImg()==null||content.getHasTitleImg()==0){
					response.put("msg", "头条必须有标题图片");
					response.put("contentForm", contentForm);
					return response.toJSONString();
				}
			}
		}
		//v信用，截取视频url，覆盖txt字段
		if("vxy".equals(contentForm.getChannelId())){
			String txt = contentForm.getTxt().trim();
			if(txt!=null&&txt.trim().length()>0){
				org.jsoup.nodes.Document doc = Jsoup.parse(txt);
				Elements ele = doc.select(".edui-upload-video");
				if(ele.size()>0){
					txt = ele.get(0).attr("src");
				}
			}
			contentForm.setTxt(txt);
		}
		//Begin Get User Top 1 Group
		List<SysGroup> groups = sessionInfo.getMyGroups();
		//End Get User Top 1 Group
		Content content = new Content();
		content.setSiteId(groups.get(0).getGroupId().toString());
		BeanUtils.copyProperties(contentForm,content);
		content.setEtime(new Date());
		content.setEditorId(sessionInfo.getMyUser().getUserId());
		content.setEditor(sessionInfo.getMyUser().getName());
		ContentExt contentExt = new ContentExt();
		BeanUtils.copyProperties(contentForm,contentExt);
		if(!"0".equals(contentExt.getLink().trim())){
			contentExt.setLink(contentExt.getOriginUrl());
		}else{
			contentExt.setLink("");
		}
		contentExt.setEtime(new Date());
		contentExt.setEditorId(sessionInfo.getMyUser().getUserId());
		contentExt.setEditor(sessionInfo.getMyUser().getName());
		iContentService.update(content);
		String separator = System.getProperty("file.separator");
		if(!file.isEmpty()){
			String name = file.getOriginalFilename();
			int index = name.lastIndexOf(".");
			String type = name.substring(index, name.length());
			String imgName = UUID.randomUUID().toString().replace("-", "");
			File img = new File(titleImgStore+separator+imgName+type);
			try {
				if(img.createNewFile()){
					file.transferTo(img);
//					contentExt.setTitleImg(titleImgGet+contentExt.getContentId()+type);
					contentExt.setTitleImg(imgName+type);
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		iContentExtService.update(contentExt);
		updateIndex(content, contentExt);
		redirectAttributes.addFlashAttribute("msg","修改成功!");
		redirectAttributes.addFlashAttribute("opFlag","true");
		
		response.put("status", "101");
		response.put("msg", "修改成功!");
		response.put("opFlag", true);
		return response.toJSONString();
	}
}
