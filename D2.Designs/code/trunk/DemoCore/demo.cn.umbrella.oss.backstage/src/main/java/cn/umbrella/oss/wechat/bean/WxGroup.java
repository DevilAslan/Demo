package cn.umbrella.oss.wechat.bean;

import java.util.Date;


/**
 * @author wangs
 * @Since 2010-2017
 * @create 2017-03-08 11:38:07
 * @history
 */
public class WxGroup {
	
	//alias
	public static final String TABLE_ALIAS = "WxGroup";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Long id;
	
	/**
	 * @Fields groupid:分组ID
	 */
	private Integer groupid;
	
	/**
	 * @Fields name:分组名称
	 */
	private String name;
	
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
	 * @Fields remark:备注
	 */
	private String remark;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

	public WxGroup(){
	}

	public WxGroup(Long id){
		this.id = id;
	}

	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setGroupid(Integer groupid){
		this.groupid = groupid;
	}
	
	public Integer getGroupid(){
		return groupid;
	}
	
	public void setName(String name){
			if(null != name){
				name = name.trim();
			}
		this.name = name;
	}
	
	public String getName(){
		return name;
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