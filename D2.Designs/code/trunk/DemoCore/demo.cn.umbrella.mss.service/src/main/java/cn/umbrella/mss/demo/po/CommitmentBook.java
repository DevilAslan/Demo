package cn.umbrella.mss.demo.po;

import java.util.Date;


/**
 * @author TODO
 * @Since 2010-2016
 * @create 2016-11-02 10:26:57
 * @history
 */
public class CommitmentBook {
	
	//alias
	public static final String TABLE_ALIAS = "CommitmentBook";
	
	//columns START
	/**
	 * @Fields uid:ID,UUID
	 */
	private String uid;
	
	/**
	 * @Fields creditEntityName:企业名称
	 */
	private String creditEntityName;
	
	/**
	 * @Fields creditEntityCode:组织机构代码
	 */
	private String creditEntityCode;
	
	/**
	 * @Fields unifySocietyCode:统一社会信用代码
	 */
	private String unifySocietyCode;
	
	/**
	 * @Fields businessLicense:工商营业执照注册号
	 */
	private String businessLicense;
	
	/**
	 * @Fields taxRegisterNumber:税务登记号
	 */
	private String taxRegisterNumber;
	
	/**
	 * @Fields legalPerson:法定代表人（负责人）
	 */
	private String legalPerson;
	
	/**
	 * @Fields tableOwnerId:所属部门id，存group_path
	 */
	private String tableOwnerId;
	
	/**
	 * @Fields tableOwnerName:所属部门名称
	 */
	private String tableOwnerName;
	
	/**
	 * @Fields tableOwnerName:所属部门别名
	 */
	private String groupAlias;
	
	/**
	 * @Fields filePath:文件路径
	 */
	private String filePath;
	
	/**
	 * @Fields imgFile:图片文件
	 */
	private String imgFile;
	
	/**
	 * @Fields isPublish:是否发布：1未发布，2已发布，3已撤回
	 */
	private Integer isPublish;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields createrId:创建人id
	 */
	private Integer createrId;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields etime:处理时间
	 */
	private Date etime;
	
	/**
	 * @Fields editorId:处理人id
	 */
	private Integer editorId;
	
	/**
	 * @Fields editor:处理人
	 */
	private String editor;
	
	/**
	 * @Fields remarks:备注
	 */
	private String remarks;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Boolean deleted;
	
	private String bookName;
	
	private String domainType;
	
	private Integer orderValue;
	
	private String domainName;
	
	public String getGroupAlias() {
		return groupAlias;
	}

	public void setGroupAlias(String groupAlias) {
		this.groupAlias = groupAlias;
	}

	/**
	 * 承诺书内容
	 */
	private String content;
	
	//承若时间
	private String commTime;
	//columns END
	
	public String getCommTime() {
		return commTime;
	}

	public void setCommTime(String commTime) {
		this.commTime = commTime;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommitmentBook(){
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public CommitmentBook(String uid){
		this.uid = uid;
	}

	
	public void setUid(String uid){
			if(null != uid){
				uid = uid.trim();
			}
		this.uid = uid;
	}
	
	public String getUid(){
		return uid;
	}
	
	public void setCreditEntityName(String creditEntityName){
			if(null != creditEntityName){
				creditEntityName = creditEntityName.trim();
			}
		this.creditEntityName = creditEntityName;
	}
	
	public String getCreditEntityName(){
		return creditEntityName;
	}
	
	public void setCreditEntityCode(String creditEntityCode){
			if(null != creditEntityCode){
				creditEntityCode = creditEntityCode.trim();
			}
		this.creditEntityCode = creditEntityCode;
	}
	
	public String getCreditEntityCode(){
		return creditEntityCode;
	}
	
	public void setUnifySocietyCode(String unifySocietyCode){
			if(null != unifySocietyCode){
				unifySocietyCode = unifySocietyCode.trim();
			}
		this.unifySocietyCode = unifySocietyCode;
	}
	
	public String getUnifySocietyCode(){
		return unifySocietyCode;
	}
	
	public void setBusinessLicense(String businessLicense){
			if(null != businessLicense){
				businessLicense = businessLicense.trim();
			}
		this.businessLicense = businessLicense;
	}
	
	public String getBusinessLicense(){
		return businessLicense;
	}
	
	public void setTaxRegisterNumber(String taxRegisterNumber){
			if(null != taxRegisterNumber){
				taxRegisterNumber = taxRegisterNumber.trim();
			}
		this.taxRegisterNumber = taxRegisterNumber;
	}
	
	public String getTaxRegisterNumber(){
		return taxRegisterNumber;
	}
	
	public void setLegalPerson(String legalPerson){
			if(null != legalPerson){
				legalPerson = legalPerson.trim();
			}
		this.legalPerson = legalPerson;
	}
	
	public String getLegalPerson(){
		return legalPerson;
	}
	
	public void setTableOwnerId(String tableOwnerId){
			if(null != tableOwnerId){
				tableOwnerId = tableOwnerId.trim();
			}
		this.tableOwnerId = tableOwnerId;
	}
	
	public String getTableOwnerId(){
		return tableOwnerId;
	}
	
	public void setTableOwnerName(String tableOwnerName){
			if(null != tableOwnerName){
				tableOwnerName = tableOwnerName.trim();
			}
		this.tableOwnerName = tableOwnerName;
	}
	
	public String getTableOwnerName(){
		return tableOwnerName;
	}
	
	public void setFilePath(String filePath){
			if(null != filePath){
				filePath = filePath.trim();
			}
		this.filePath = filePath;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setImgFile(String imgFile){
			if(null != imgFile){
				imgFile = imgFile.trim();
			}
		this.imgFile = imgFile;
	}
	
	public String getImgFile(){
		return imgFile;
	}
	
	public void setIsPublish(Integer isPublish){
		this.isPublish = isPublish;
	}
	
	public Integer getIsPublish(){
		return isPublish;
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
	
	public void setRemarks(String remarks){
			if(null != remarks){
				remarks = remarks.trim();
			}
		this.remarks = remarks;
	}
	
	public String getRemarks(){
		return remarks;
	}
	
	public void setDeleted(Boolean deleted){
		this.deleted = deleted;
	}
	
	public Boolean getDeleted(){
		return deleted;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
}