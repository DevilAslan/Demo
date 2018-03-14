package cn.umbrella.oss.model;

import java.util.Date;

/**
 * @author wangs
 * @Since 2010-2015
 * @create 2015-09-18 13:22:30
 * @history
 */
public class SysAttachment {
	
	//alias
	public static final String TABLE_ALIAS = "SysAttachment";
	
	//columns START
	
	private String sysId;
	
	/**
	 * @Fields id:附件id
	 */
	private String id;
	
	/**
	 * @Fields targetTable:目标表
	 */
	private String targetTable;
	
	/**
	 * @Fields targetField:目标字段
	 */
	private String targetField;
	
	/**
	 * @Fields targetId:目标记录ID
	 */
	private String targetId;
	
	/**
	 * @Fields type:类型:text/html,image/jpeg等
	 */
	private String type;
	
	/**
	 * @Fields url:http访问的相对或绝对url
	 */
	private String url;
	
	/**
	 * @Fields physicalPath:存储在磁盘上的绝对路径
	 */
	private String physicalPath;
	
	/**
	 * @Fields originName:文件原始名称
	 */
	private String originName;
	
	/**
	 * @Fields suffix:后缀
	 */
	private String suffix;
	
	/**
	 * @Fields size:文件大小
	 */
	private Long size;
	
	/**
	 * @Fields data:数据
	 */
	private byte[] data;
	
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
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

	public SysAttachment(){
	}
	

	public String getSysId() {
		return sysId;
	}


	public void setSysId(String sysId) {
		this.sysId = sysId;
	}


	public SysAttachment(String id){
		this.id = id;
	}

	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
	public void setTargetTable(String targetTable){
		this.targetTable = targetTable;
	}
	
	public String getTargetTable(){
		return targetTable;
	}
	
	public void setTargetField(String targetField){
		this.targetField = targetField;
	}
	
	public String getTargetField(){
		return targetField;
	}
	
	public void setTargetId(String targetId){
		this.targetId = targetId;
	}
	
	public String getTargetId(){
		return targetId;
	}
	
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return url;
	}
	
	public void setPhysicalPath(String physicalPath){
		this.physicalPath = physicalPath;
	}
	public String getPhysicalPath(){
		return physicalPath;
	}
	
	public void setOriginName(String originName){
		this.originName = originName;
	}
	public String getOriginName(){
		return originName;
	}
	
	public void setSuffix(String suffix){
		this.suffix = suffix;
	}
	public String getSuffix(){
		return suffix;
	}
	
	public void setSize(Long size){
		this.size = size;
	}
	public Long getSize(){
		return size;
	}
	
	public void setData(byte[] data){
		this.data = data;
	}
	public byte[] getData(){
		return data;
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
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	public Integer getDeleted(){
		return deleted;
	}

}
