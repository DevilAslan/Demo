package cn.umbrella.oss.wechat.bean;


/**
 * @author zhang.xiaolei 
 * @Since 2010-2017
 * @create 2017-02-10 15:08:53
 * @history
 */
public class TwxcmsMsgNews {
	
	//alias
	public static final String TABLE_ALIAS = "TwxcmsMsgNews";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields title:title
	 */
	private String title;
	
	/**
	 * @Fields author:author
	 */
	private String author;
	
	/**
	 * @Fields brief:brief
	 */
	private String brief;
	
	/**
	 * @Fields description:description
	 */
	private String description;
	
	/**
	 * @Fields picpath:picpath
	 */
	private String picpath;
	
	private String filePath;
	
	/**
	 * @Fields picdir:picdir
	 */
	private String picdir;
	
	/**
	 * @Fields showpic:showpic
	 */
	private Boolean showpic;
	
	/**
	 * @Fields url:url
	 */
	private String url;
	
	/**
	 * @Fields fromurl:fromurl
	 */
	private String fromurl;
	
	/**
	 * @Fields baseId:baseId
	 */
	private Integer baseId;
	
	//columns END
	
	private String type;

	public TwxcmsMsgNews(){
	}

	public TwxcmsMsgNews(Integer id){
		this.id = id;
	}

	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setBrief(String brief){
		this.brief = brief;
	}
	
	public String getBrief(){
		return brief;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setPicpath(String picpath){
		this.picpath = picpath;
	}
	
	public String getPicpath(){
		return picpath;
	}
	
	public void setPicdir(String picdir){
		this.picdir = picdir;
	}
	
	public String getPicdir(){
		return picdir;
	}
	
	public void setShowpic(Boolean showpic){
		this.showpic = showpic;
	}
	
	public Boolean getShowpic(){
		return showpic;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setFromurl(String fromurl){
		this.fromurl = fromurl;
	}
	
	public String getFromurl(){
		return fromurl;
	}
	
	public void setBaseId(Integer baseId){
		this.baseId = baseId;
	}
	
	public Integer getBaseId(){
		return baseId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}