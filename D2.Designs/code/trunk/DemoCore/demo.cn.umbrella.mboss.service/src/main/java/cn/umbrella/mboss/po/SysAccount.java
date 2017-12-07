package cn.umbrella.mboss.po;

import java.util.Date;

/**
 * @author wangs
 * @Since 2010-2015
 * @create 2015-12-17 11:32:22
 * @history
 */
public class SysAccount {
	
	//alias
	public static final String TABLE_ALIAS = "SysAccount";
	
	//columns START
	/**
	 * @Fields accountId:account_id
	 */
	private Integer accountId;
	
	/**
	 * @Fields userId:userId
	 */
	private Integer userId;
	
	/**
	 * @Fields sysId:对应系统id
	 */
	private String sysId;
	
	/**
	 * @Fields pid:pid
	 */
	private Integer pid;
	
	/**
	 * @Fields account:账号
	 */
	private String account;
	
	/**
	 * @Fields password:密码
	 */
	private String password;
	
	/**
	 * @Fields oldPassword:旧密码
	 */
	private String oldPassword;
	/**
	 * @Fields status:账户状态：0.未审核，1.审核不通过，2.审核通过，3.禁用
	 */
	private Integer status;
	
	/**
	 * @Fields isEnable:是否启用：0.禁用，1.启用
	 */
	private Integer isEnable;
	
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
	 * @Fields accountType:账号类型
	 */
	private Integer accountType;
	
	/**
	 * @Fields deviceId:移动设备编号
	 */
	private String deviceId;
	
	/**
	 * @Fields osType:移动设备操作系统
	 */
	private String osType ;
	
	/**
	 * @Fields osVersion:移动设备操作系统版本
	 */
	private String osVersion;
	
	//columns END

	public SysAccount(){
	}

	public SysAccount(Integer accountId){
		this.accountId = accountId;
	}

	
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}
	
	public Integer getAccountId(){
		return accountId;
	}
	
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
	public Integer getUserId(){
		return userId;
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
	
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getAccount(){
		return account;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
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

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

}