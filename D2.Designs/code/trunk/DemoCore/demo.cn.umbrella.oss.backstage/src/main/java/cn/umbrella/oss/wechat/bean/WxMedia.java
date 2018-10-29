package cn.umbrella.oss.wechat.bean;

import java.util.Date;
import java.util.List;

import cn.umbrella.oss.wechat.form.MaterialArticle;

/**
 * @author zhang.xiaolei 
 * @Since 2010-2017
 * @create 2017-02-13 16:33:38
 * @history
 */
public class WxMedia {
	
	//alias
	public static final String TABLE_ALIAS = "WxMedia";
	
	//columns START
	/**
	 * @Fields id:id，uuid
	 */
	private String id;
	
	/**
	 * @Fields mediaId:素材id，通过素材管理接口上传多媒体文件，得到的id
	 */
	private String mediaId;
	
	/**
	 * @Fields type:素材类型（素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news））
	 */
	private String type;
	
	/**
	 * @Fields title:标题
	 */
	private String title;
	
	/**
	 * @Fields thumbMediaId:图文消息的封面图片素材id（必须是永久mediaID）
	 */
	private String thumbMediaId;
	
	/**
	 * @Fields author:作者
	 */
	private String author;
	
	/**
	 * @Fields digest:图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 */
	private String digest;
	
	/**
	 * @Fields showCoverPic:是否显示封面：0不显示，1显示
	 */
	private Boolean showCoverPic;
	
	/**
	 * @Fields content:内容
	 */
	private String content;
	
	/**
	 * @Fields contentSourceUrl:图文消息的原文地址
	 */
	private String contentSourceUrl;
	
	/**
	 * @Fields url:图文页的URL
	 */
	private String url;
	
	/**
	 * @Fields downUrl:视频的url
	 */
	private String downUrl;
	
	/**
	 * @Fields description:描述
	 */
	private String description;
	
	/**
	 * @Fields filePath:文件本地磁盘路径
	 */
	private String filePath;
	
	/**
	 * @Fields isEver:是否永久（1是，0否）
	 */
	private Boolean isEver;
	
	/**
	 * @Fields mediaStatus:消息状态（0，未发送，1已发送）
	 */
	private Boolean mediaStatus;
	
	/**
	 * @Fields name:文件名称
	 */
	private String name;
	
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
	
	private List<MaterialArticle> newsItems;
	
	//columns END

	public WxMedia(){
	}

	public WxMedia(String id){
		this.id = id;
	}

	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
	public void setMediaId(String mediaId){
		this.mediaId = mediaId;
	}
	
	public String getMediaId(){
		return mediaId;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setThumbMediaId(String thumbMediaId){
		this.thumbMediaId = thumbMediaId;
	}
	
	public String getThumbMediaId(){
		return thumbMediaId;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setDigest(String digest){
		this.digest = digest;
	}
	
	public String getDigest(){
		return digest;
	}
	
	public void setShowCoverPic(Boolean showCoverPic){
		this.showCoverPic = showCoverPic;
	}
	
	public Boolean getShowCoverPic(){
		return showCoverPic;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setContentSourceUrl(String contentSourceUrl){
		this.contentSourceUrl = contentSourceUrl;
	}
	
	public String getContentSourceUrl(){
		return contentSourceUrl;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setDownUrl(String downUrl){
		this.downUrl = downUrl;
	}
	
	public String getDownUrl(){
		return downUrl;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setIsEver(Boolean isEver){
		this.isEver = isEver;
	}
	
	public Boolean getIsEver(){
		return isEver;
	}
	
	public void setMediaStatus(Boolean mediaStatus){
		this.mediaStatus = mediaStatus;
	}
	
	public Boolean getMediaStatus(){
		return mediaStatus;
	}
	
	public void setName(String name){
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
	
	public void setRemark(String remark){
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

	public List<MaterialArticle> getNewsItems() {
		return newsItems;
	}

	public void setNewsItems(List<MaterialArticle> newsItems) {
		this.newsItems = newsItems;
	}

}