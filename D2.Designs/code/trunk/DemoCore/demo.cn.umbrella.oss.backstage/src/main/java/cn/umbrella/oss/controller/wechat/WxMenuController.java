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

import cn.umbrella.commons.bean.BaseQuery;
import cn.umbrella.conmmons.genarator.IDGenarator;
import cn.umbrella.oss.config.Constant;
import cn.umbrella.oss.service.wechat.IWxMenuService;
import cn.umbrella.oss.vo.MySessionInfo;
import cn.umbrella.oss.wechat.bean.WxMenu;
import cn.umbrella.oss.wechat.form.WxMenuForm;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(WxMenuController.ACTION_PATH)
@SessionAttributes(Constant.MY_SESSION_INFO)
public class WxMenuController {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH="/wxMenu/";
	
	@Autowired
	private IWxMenuService wxMenuService; 
	
	/**
	 * 跳转数据表列表页面 
	 *
	 * @Title: list 
	 * @return String
	 */
	@RequestMapping(value="list")
	public String list(){
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
		query.getQueryData().put("deleted", 0);
		query.getQueryData().put("queryOrderInfo", "sort asc");
	    List<WxMenu> list = wxMenuService.query(query.getQueryData());
		JSONObject datas = new JSONObject();
		datas.put("menuList", list);
		return datas;
	}
	
	/**
	 * 删除 
	 *
	 * @Title: doDelete 
	 * @param menuId
	 * @return ResponseData
	 */
	@RequestMapping(value = "doDelete.json")
	@ResponseBody
	public JSONObject doDelete(Integer menuId) {
		wxMenuService.delete(menuId);
		JSONObject datas = new JSONObject();
		datas.put("msg", "成功删除！");
		return datas;
	}
	
	/**
	 * 详情查看
	 *
	 * @Title: detail 
	 * @param model
	 * @param menuId
	 * @return String
	 */
	@RequestMapping(value="detail")
	public String detail(Model model, java.lang.Integer menuId){
		WxMenu wxMenu = wxMenuService.queryById(menuId);
		WxMenuForm wxMenuForm = new WxMenuForm();
		
		BeanUtils.copyProperties(wxMenu, wxMenuForm);
		model.addAttribute("wxMenuForm", wxMenuForm);
		return  "wxcms" + ACTION_PATH + "detail";
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
	public String add(Model model,Integer menuId){
		WxMenuForm wxMenuForm = new WxMenuForm();
		wxMenuForm.setMenuPid(menuId.toString());
		String token = IDGenarator.getUUID32();
		wxMenuForm.setToken(token);
		
		model.addAttribute("wxMenuForm", wxMenuForm);
		model.addAttribute("validate", ValidateUtil.getValidate(WxMenuForm.class));
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
	@ResponseBody
	public JSONObject doAdd( @ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,Model model, @Valid WxMenuForm wxMenuForm, BindingResult result){
		JSONObject datas = new JSONObject();
	/*	if(result.hasErrors()) {		
			List<Object> errorInfoList = new  ArrayList<>(); 
			for(FieldError errors : result.getFieldErrors()){
				Map<String, Object> map = new HashMap<>();
				map.put("field", errors.getField());
				map.put("msg",errors.getDefaultMessage());
				errorInfoList.add(map);
			}
			datas.put("errorStart", false);
			datas.put("errorCode", JSON.toJSONString(errorInfoList));
			return  datas;
		}*/
		
		WxMenu wxMenu = new WxMenu();
		BeanUtils.copyProperties(wxMenuForm, wxMenu);
		wxMenu.setCreater(sessionInfo.myUser.getName());
		wxMenuService.add(wxMenu);
		
		datas.put("msg", "创建成功");
		return datas;
	}
	
	@RequestMapping(value="doEdit", method = {RequestMethod.POST})
	@ResponseBody
	public JSONObject doEdit( @ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,Model model, @Valid WxMenuForm wxMenuForm, BindingResult result){
		JSONObject datas = new JSONObject();
	/*	if(result.hasErrors()) {		
			List<Object> errorInfoList = new  ArrayList<>(); 
			for(FieldError errors : result.getFieldErrors()){
				Map<String, Object> map = new HashMap<>();
				map.put("field", errors.getField());
				map.put("msg",errors.getDefaultMessage());
				errorInfoList.add(map);
			}
			datas.put("errorStart", false);
			datas.put("errorCode", JSON.toJSONString(errorInfoList));
			return  datas;
		}*/
		
		WxMenu wxMenu = new WxMenu();
		BeanUtils.copyProperties(wxMenuForm, wxMenu);
		wxMenu.setEditor(sessionInfo.myUser.getName());
		wxMenuService.update(wxMenu);
		
		datas.put("msg", "修改成功");
		return datas;
	}
}