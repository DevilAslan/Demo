package cn.umbrella.mboss.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wangs
 * @Since 2010-2015
 * @create 2015-12-17 11:32:26
 * @history
 */
public class SysResource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//alias
	public static final String TABLE_ALIAS = "SysResource";
	
	//columns START
	/**
	 * @Fields resourceId:resource_id
	 */
	private Integer resourceId;
	
	/**
	 * @Fields sysId:对应系统id
	 */
	private String sysId;
	
	/**
	 * @Fields pid:pid
	 */
	private Integer pid;
	
	/**
	 * @Fields module:模块
	 */
	@NotBlank(message="模块名不能为空")
	private String module;
	
	/**
	 * @Fields resourceName:资源名称
	 */
	private String resourceName;
	
	/**
	 * @Fields url:url
	 */
	@NotBlank(message="url不能为空")
	private String url;
	
	/**
	 * @Fields operation:操作：增加、删除、修改（修改可以细分称针对某个属性的调整，如审核、调价等）
	 */
	private Integer operation;
	
	/**
	 * @Fields type:type
	 */
	private Integer type;
	
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
	
	private String checked;
	
	/**
	 *  true 有子节点 false 没有子节点
	 */
	private Boolean childNode;
	
	/**
	 * 一共有多少个子节点
	 */
	private int  levelSize;
	
	/**
	 * 有几个层级
	 */
	private int  haslevel;

	/**
	 * 共有多少个功能点
	 */
	private int  funSize;
	
	private List<SysResource> childList;
	
	
	

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getFunSize() {
		return funSize;
	}
	public void setFunSize(int funSize) {
		this.funSize = funSize;
	}
	public int getHaslevel() {
		return haslevel;
	}
	public void setHaslevel(int haslevel) {
		this.haslevel = haslevel;
	}
	public Boolean getChildNode() {
		return childNode;
	}
	public void setChildNode(Boolean childNode) {
		this.childNode = childNode;
	}
	public int getLevelSize() {
		return levelSize;
	}
	public void setLevelSize(int levelSize) {
		this.levelSize = levelSize;
	}
	public List<SysResource> getChildList() {
		return childList;
	}
	public void setChildList(List<SysResource> childList) {
		this.childList = childList;
	}

	public SysResource(){
	}
	
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public SysResource(Integer resourceId){
		this.resourceId = resourceId;
	}
	
	public void setResourceId(Integer resourceId){
		this.resourceId = resourceId;
	}
	
	public Integer getResourceId(){
		return resourceId;
	}
	
	public void setSysId(String sysId){
		this.sysId = sysId;
	}
	
	public String getSysId(){
		return sysId;
	}
	
	public void setPid(Integer pid){
		this.pid = pid;
	}
	
	public Integer getPid(){
		return pid;
	}
	
	public void setResourceName(String resourceName){
		this.resourceName = resourceName;
	}
	
	public String getResourceName(){
		return resourceName;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setOperation(Integer operation){
		this.operation = operation;
	}
	
	public Integer getOperation(){
		return operation;
	}
	
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return type;
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

}