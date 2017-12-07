package cn.umbrella.oss.wechat.bean;

import java.util.Date;


/**
 * @author wangs
 * @Since 2010-2017
 * @create 2017-02-14 16:29:11
 * @history
 */
public class WxMenu {
	
	//alias
	public static final String TABLE_ALIAS = "WxMenu";
	
	//columns START
	/**
	 * @Fields menuId:菜单ID
	 */
	private Integer menuId;
	
	/**
	 * @Fields menuName:菜单名称
	 */
	private String menuName;
	
	/**
	 * @Fields menuLeve:菜单等级
	 */
	private Integer menuLeve;
	
	/**
	 * @Fields menuPid:Pid
	 */
	private String menuPid;
	
	/**
	 * @Fields enable:启用
	 */
	private Integer enable;
	
	/**
	 * @Fields sort:排序
	 */
	private Integer sort;
	
	/**
	 * @Fields menuType:菜单类别
	 */
	private String menuType;
	
	/**
	 * @Fields url:url
	 */
	private String url;
	
	/**
	 * @Fields eventType:消息类型
	 */
	private String eventType;
	
	/**
	 * @Fields msgId:消息Id
	 */
	private String msgId;
	
	/**
	 * @Fields inputCode:关键字
	 */
	private String inputCode;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields createrId:创建人Id
	 */
	private Integer createrId;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields editorId:修改人
	 */
	private Integer editorId;
	
	/**
	 * @Fields editor:修改人
	 */
	private String editor;
	
	/**
	 * @Fields remark:备注
	 */
	private String remark;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

	public WxMenu(){
	}

	public WxMenu(Integer menuId){
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
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	
	public Integer getDeleted(){
		return deleted;
	}
}