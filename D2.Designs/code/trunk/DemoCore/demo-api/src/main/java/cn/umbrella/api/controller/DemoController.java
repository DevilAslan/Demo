package cn.umbrella.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.umbrella.api.bean.SessionInfo;
import cn.umbrella.api.config.Constant;
import cn.umbrella.api.config.PageConfig;
import cn.umbrella.api.enums.AccessCode;
import cn.umbrella.commons.bean.BaseQuery;
@Controller
@RequestMapping(DemoController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class DemoController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH = "/demo";
	protected static final String PAGE_PATH = "/excavateData/creditAssessment/";
	
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
}
