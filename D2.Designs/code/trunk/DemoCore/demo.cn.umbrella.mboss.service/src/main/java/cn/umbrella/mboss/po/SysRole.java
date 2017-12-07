package cn.umbrella.mboss.po;

import java.util.Date;

/**
 * @author wangs
 * @Since 2010-2015
 * @create 2015-12-17 11:32:26
 * @history
 */
public class SysRole {

	// alias
	public static final String TABLE_ALIAS = "SysRole";

	// columns START
	/**
	 * @Fields roleId:role_id
	 */
	private Integer roleId;

	/**
	 * @Fields sysId:对应系统id
	 */
	private String sysId;

	/**
	 * @Fields roleName:角色名称
	 */
	private String roleName;

	/**
	 * @Fields resourceId:默认主页url
	 */
	private Integer resourceId;

	/**
	 * @Fields status:是否启用：0、未启用，1、启用
	 */
	private Integer status;

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
	 * @Fields openLevel;
	 */
	private Integer openLevel;

	/**
	 * @Fields comment:备注
	 */
	private String comment;

	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;

	// columns END

	/**
	 * @Fields checked:选中
	 */
	private String checked;

	public SysRole() {
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public SysRole(Integer roleId) {
		this.roleId = roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}

	public Integer getCreaterId() {
		return createrId;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreater() {
		return creater;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}

	public Integer getEditorId() {
		return editorId;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getEditor() {
		return editor;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public Integer getOpenLevel() {
		return openLevel;
	}

	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}

}