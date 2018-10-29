package cn.umbrella.mboss.po;

import java.util.Date;

/**
 * @author wangs
 * @Since 2010-2015
 * @create 2015-12-17 11:32:28
 * @history
 */
public class SysUser {
	
	//alias
	public static final String TABLE_ALIAS = "SysUser";
	
	//columns START
	/**
	 * @Fields userId:userId
	 */
	private Integer userId;
	
	/**
	 * @Fields sysId:对应系统id
	 */
	private String sysId;
	
	/**
	 * @Fields name:姓名
	 */
	private String name;
	
	/**
	 * @Fields name:account
	 */
	private String account;
	
	/**
	 * @Fields is_enable:是否启用：0.禁用，1.启用
	 */
	private Integer isEnable;
	
	/**
	 * @Fields sex:性别：0，女；1，男
	 */
	private Integer sex;
	
	/**
	 * @Fields age:年龄
	 */
	private Integer age;
	
	/**
	 * @Fields telephone:联系电话
	 */
	private String telephone;
	
	/**
	 * @Fields mobilephone:移动电话
	 */
	private String mobilephone;
	
	/**
	 * @Fields job:职务
	 */
	private String job;
	
	/**
	 * @Fields qq:qq
	 */
	private String qq;
	
	/**
	 * @Fields weixin:微信
	 */
	private String weixin;
	
	/**
	 * @Fields weibo:微博
	 */
	private String weibo;
	
	/**
	 * @Fields email:电子邮箱
	 */
	private String email;
	
	/**
	 * @Fields officeAddr:办公地址
	 */
	private String officeAddr;
	
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

	private String groupName;
	/** 
	 * @Fields idCard:身份证号码
	 */
	private String idcard;
	/** 
	 * @Fields education:学历
	 */
	private Integer education;
	/** 
	 * @Fields isChecking:是否考勤，枚举（0否，1是）
	 */
	private Integer isChecking;
	/** 
	 * @Fields checkingStartTime:考勤开始日期
	 */
	private Date checkingStartTime;
	/** 
	 * @Fields dcardImg:身份证照片(附件id)
	 */
	private String idcardImg;
	/** 
	 * @Fields faceImg:人脸图片信息(附件id)
	 */
	private String faceImg;
	/** 
	 * @Fields fingerprint:指纹信息
	 */
	private String fingerprint;
	/** 
	 * @Fields hireStatus:雇佣状态（枚举：1在职，2离职）
	 */
	private Integer hireStatus;
		
	public SysUser(){
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public SysUser(Integer userId){
		this.userId = userId;
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
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
	public Integer getSex(){
		return sex;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}
	
	public Integer getAge(){
		return age;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public String getTelephone(){
		return telephone;
	}
	
	public void setMobilephone(String mobilephone){
		this.mobilephone = mobilephone;
	}
	
	public String getMobilephone(){
		return mobilephone;
	}
	
	public void setJob(String job){
		this.job = job;
	}
	
	public String getJob(){
		return job;
	}
	
	public void setQq(String qq){
		this.qq = qq;
	}
	
	public String getQq(){
		return qq;
	}
	
	public void setWeixin(String weixin){
		this.weixin = weixin;
	}
	
	public String getWeixin(){
		return weixin;
	}
	
	public void setWeibo(String weibo){
		this.weibo = weibo;
	}
	
	public String getWeibo(){
		return weibo;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setOfficeAddr(String officeAddr){
		this.officeAddr = officeAddr;
	}
	
	public String getOfficeAddr(){
		return officeAddr;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Integer getIsChecking() {
		return isChecking;
	}

	public void setIsChecking(Integer isChecking) {
		this.isChecking = isChecking;
	}

	public Date getCheckingStartTime() {
		return checkingStartTime;
	}

	public void setCheckingStartTime(Date checkingStartTime) {
		this.checkingStartTime = checkingStartTime;
	}

	public String getIdcardImg() {
		return idcardImg;
	}

	public void setIdcardImg(String idcardImg) {
		this.idcardImg = idcardImg;
	}

	public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public Integer getHireStatus() {
		return hireStatus;
	}

	public void setHireStatus(Integer hireStatus) {
		this.hireStatus = hireStatus;
	}
	

}