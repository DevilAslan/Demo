package cn.umbrella.po;

import java.util.Date;
import java.util.List;

public class SysMenu {
	
	//alias
	public static final String TABLE_ALIAS = "SysMenu";
	
	//columns START
	/**
	 * @Fields menuId:id
	 */
	private Integer menuId;
	
	/**
	 * @Fields sysId:对应系统id
	 */
	private String sysId;
	
	/**
	 * @Fields menuPid:menuPid
	 */
	private Integer menuPid;
	
	/**
	 * @Fields menuName:菜单名称
	 */
	private String menuName;
	
	/**
	 * @Fields sysResourceId:resource_id
	 */
	private Integer sysResourceId;
	
	/**
	 * @Fields url:url
	 */
	private String url;
	
	/**
	 * @Fields style:style
	 */
	private String style;
	
	/**
	 * @Fields level:级别
	 */
	private Integer level;
	
	/**
	 * @Fields isLeaf:是否叶子
	 */
	private Integer isLeaf;
	
	/**
	 * @Fields orderValue:排序
	 */
	private Integer orderValue;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields createrId:createrId
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
	 * @Fields comment:备注
	 */
	private String comment;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

	
	private List<SysMenu> chaildMenu;
	private String module;

	public SysMenu(){
	}

	
	public List<SysMenu> getChaildMenu() {
		return chaildMenu;
	}

	public void setChaildMenu(List<SysMenu> chaildMenu) {
		this.chaildMenu = chaildMenu;
	}

	public SysMenu(Integer menuId){
		this.menuId = menuId;
	}
	
	public void setMenuId(Integer menuId){
		this.menuId = menuId;
	}
	
	public Integer getMenuId(){
		return menuId;
	}
	
	public void setSysId(String sysId){
		this.sysId = sysId;
	}
	
	public String getSysId(){
		return sysId;
	}
	
	public void setMenuPid(Integer menuPid){
		this.menuPid = menuPid;
	}
	
	public Integer getMenuPid(){
		return menuPid;
	}
	
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	
	public String getMenuName(){
		return menuName;
	}
	
	public void setSysResourceId(Integer sysResourceId){
		this.sysResourceId = sysResourceId;
	}
	
	public Integer getSysResourceId(){
		return sysResourceId;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setStyle(String style){
		this.style = style;
	}
	
	public String getStyle(){
		return style;
	}
	
	public void setLevel(Integer level){
		this.level = level;
	}
	
	public Integer getLevel(){
		return level;
	}
	
	public void setIsLeaf(Integer isLeaf){
		this.isLeaf = isLeaf;
	}
	
	public Integer getIsLeaf(){
		return isLeaf;
	}
	
	public void setOrderValue(Integer orderValue){
		this.orderValue = orderValue;
	}
	
	public Integer getOrderValue(){
		return orderValue;
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
		this.editor = editor;
	}
	
	public String getEditor(){
		return editor;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	
	public Integer getDeleted(){
		return deleted;
	}
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
}