package cn.umbrella.oss.wechat.form;

import java.util.Date;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author wangs
 * @Since 2010-2017
 * @create 2017-02-14 16:29:11
 * @history
 */
public class WxMenuForm {

	//columns START
	
	/**
	 * @Fields menu_id:菜单ID
	 */
	@NotNull(message = "请填写菜单ID")
	@Range(message = "数值范围不正确")
	private Integer menuId;
	
	/**
	 * @Fields menu_Name:菜单名称
	 */
	@Length(max = 255, message = "菜单名称的长度不能超过{1}")
	private String menuName;
	
	/**
	 * @Fields menu_leve:菜单等级
	 */
	@Range(message = "数值范围不正确")
	private Integer menuLeve;
	
	/**
	 * @Fields menu_Pid:Pid
	 */
	@Length(max = 32, message = "Pid的长度不能超过{1}")
	private String menuPid;
	
	/**
	 * @Fields enable:启用
	 */
	@Range(message = "数值范围不正确")
	private Integer enable;
	
	/**
	 * @Fields sort:排序
	 */
	@Range(message = "数值范围不正确")
	private Integer sort;
	
	/**
	 * @Fields menu_type:菜单类别
	 */
	@Length(max = 50, message = "菜单类别的长度不能超过{1}")
	private String menuType;
	
	/**
	 * @Fields url:url
	 */
	@Length(max = 255, message = "url的长度不能超过{1}")
	private String url;
	
	/**
	 * @Fields eventType:消息类型
	 */
	@Length(max = 50, message = "消息类型的长度不能超过{1}")
	private String eventType;
	
	/**
	 * @Fields msgId:消息Id
	 */
	@Length(max = 100, message = "消息Id的长度不能超过{1}")
	private String msgId;
	
	/**
	 * @Fields inputCode:关键字
	 */
	@Length(max = 255, message = "关键字的长度不能超过{1}")
	private String inputCode;
	
	/**
	 * @Fields ctime:创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date ctime;
	
	/**
	 * @Fields creater_id:创建人Id
	 */
	@Range(message = "数值范围不正确")
	private Integer createrId;
	
	/**
	 * @Fields creater:创建人
	 */
	@NotEmpty(message = "请填写创建人")
	@Length(max = 128, message = "创建人的长度不能超过{1}")
	private String creater;
	
	/**
	 * @Fields etime:修改时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date etime;
	
	/**
	 * @Fields editor_id:修改人
	 */
	@Range(message = "数值范围不正确")
	private Integer editorId;
	
	/**
	 * @Fields editor:修改人
	 */
	@Length(max = 128, message = "修改人的长度不能超过{1}")
	private String editor;
	
	/**
	 * @Fields remark:备注
	 */
	@Length(max = 1024, message = "备注的长度不能超过{1}")
	private String remark;
	//columns END
	private String token;// 验证表单重复提交
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}


	public WxMenuForm(){
	}

	public WxMenuForm(Integer menuId){
		this.menuId = menuId;
	}

	
	public void setMenuId(Integer menuId){
		this.menuId = menuId;
	}
	public Integer getMenuId(){
		return menuId;
	}
	
	public void setMenuName(String menuName){
			if(null != menuName){
				menuName = menuName.trim();
			}
		this.menuName = menuName;
	}
	public String getMenuName(){
		return menuName;
	}
	
	public void setMenuLeve(Integer menuLeve){
		this.menuLeve = menuLeve;
	}
	public Integer getMenuLeve(){
		return menuLeve;
	}
	
	public void setMenuPid(String menuPid){
			if(null != menuPid){
				menuPid = menuPid.trim();
			}
		this.menuPid = menuPid;
	}
	public String getMenuPid(){
		return menuPid;
	}
	
	public void setEnable(Integer enable){
		this.enable = enable;
	}
	public Integer getEnable(){
		return enable;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return sort;
	}
	
	public void setMenuType(String menuType){
			if(null != menuType){
				menuType = menuType.trim();
			}
		this.menuType = menuType;
	}
	public String getMenuType(){
		return menuType;
	}
	
	public void setUrl(String url){
			if(null != url){
				url = url.trim();
			}
		this.url = url;
	}
	public String getUrl(){
		return url;
	}
	
	public void setEventType(String eventType){
			if(null != eventType){
				eventType = eventType.trim();
			}
		this.eventType = eventType;
	}
	public String getEventType(){
		return eventType;
	}
	
	public void setMsgId(String msgId){
			if(null != msgId){
				msgId = msgId.trim();
			}
		this.msgId = msgId;
	}
	public String getMsgId(){
		return msgId;
	}
	
	public void setInputCode(String inputCode){
			if(null != inputCode){
				inputCode = inputCode.trim();
			}
		this.inputCode = inputCode;
	}
	public String getInputCode(){
		return inputCode;
	}
	
	public void setCtime(Date ctime){
		this.ctime = ctime;
	}
	public Date getCtime(){
		return ctime;
	}
	
	public void setCreaterId(Integer createrId){
		this.createrId = createrId;
	}
	public Integer getCreaterId(){
		return createrId;
	}
	
	public void setCreater(String creater){
			if(null != creater){
				creater = creater.trim();
			}
		this.creater = creater;
	}
	public String getCreater(){
		return creater;
	}
	
	public void setEtime(Date etime){
		this.etime = etime;
	}
	public Date getEtime(){
		return etime;
	}
	
	public void setEditorId(Integer editorId){
		this.editorId = editorId;
	}
	public Integer getEditorId(){
		return editorId;
	}
	
	public void setEditor(String editor){
			if(null != editor){
				editor = editor.trim();
			}
		this.editor = editor;
	}
	public String getEditor(){
		return editor;
	}
	
	public void setRemark(String remark){
			if(null != remark){
				remark = remark.trim();
			}
		this.remark = remark;
	}
	public String getRemark(){
		return remark;
	}

}