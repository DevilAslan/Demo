package cn.umbrella.po;

import java.util.Date;

/**
 * @author wangs
 * @Since 2010-2015
 * @create 2015-12-17 11:32:23
 * @history
 */
public class SysGroup {
	
	//alias
	public static final String TABLE_ALIAS = "SysGroup";
	
	//columns START
	/**
	 * @Fields groupId:group_id
	 */
	private Integer groupId;
	
	/**
	 * @Fields sysId:对应系统id
	 */
	private String sysId;
	
	/**
	 * @Fields groupPid:上级组
	 */
	private Integer groupPid;
	
	/**
	 * @Fields groupType:组类型：0.其他、1.生产企业、2.经营企业、3. 生产企业和经营企业，4.医疗机构，5.药械中心，6.监管机构
	 */
	private Integer groupType;
	
	/**
	 * @Fields groupName:组名称
	 */
	private String groupName;
	
	/**
	 * @Fields groupAlias:别名
	 */
	private String groupAlias;
	
	/**
	 * @Fields status:状态：0.未提交，1.未审核，2.审核不通过，3.审核通过，4.禁用
	 */
	private Integer status;
	
	/**
	 * @Fields isEnable:是否启用：0.禁用，1.启用
	 */
	private Integer isEnable;
	
	/**
	 * @Fields groupPath:组所在路径
	 */
	private String groupPath;
	
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
	
	/**
	 * @Fields regionId:所在区域
	 */
	private String regionId;
	
	//columns END

	/**
	 * @Fields checked:选中
	 */
	private String checked;
	
	private String relationPerson;
	
	private String relationPhone;
	
	
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
    //区域名
	private String regionName;
	
	public SysGroup(){
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public SysGroup(Integer groupId){
		this.groupId = groupId;
	}

	public void setGroupId(Integer groupId){
		this.groupId = groupId;
	}
	
	public Integer getGroupId(){
		return groupId;
	}
	
	public void setSysId(String sysId){
		this.sysId = sysId;
	}
	
	public String getSysId(){
		return sysId;
	}
	
	public void setGroupPid(Integer groupPid){
		this.groupPid = groupPid;
	}
	
	public Integer getGroupPid(){
		return groupPid;
	}
	
	public void setGroupType(Integer groupType){
		this.groupType = groupType;
	}
	
	public Integer getGroupType(){
		return groupType;
	}
	
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
	public String getGroupName(){
		return groupName;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setIsEnable(Integer isEnable){
		this.isEnable = isEnable;
	}
	
	public Integer getIsEnable(){
		return isEnable;
	}
	
	public void setGroupPath(String groupPath){
		this.groupPath = groupPath;
	}
	
	public String getGroupPath(){
		return groupPath;
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

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRelationPerson() {
		return relationPerson;
	}

	public void setRelationPerson(String relationPerson) {
		this.relationPerson = relationPerson;
	}

	public String getRelationPhone() {
		return relationPhone;
	}

	public void setRelationPhone(String relationPhone) {
		this.relationPhone = relationPhone;
	}

	public String getGroupAlias() {
		return groupAlias;
	}

	public void setGroupAlias(String groupAlias) {
		this.groupAlias = groupAlias;
	}
	
}