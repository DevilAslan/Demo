package cn.umbrella.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ContentForm {
	
	/**
	 * @Fields contentId:contentId
	 */
	private String contentId;
	
	/**
	 * @Fields sysId:对应系统id
	 */
	private String sysId;
	
	/**
	 * @Fields channelId:栏目ID
	 */
	private String channelId;
	
	/**
	 * @Fields channelId:主题ID
	 */
	private String topicId;
	
	/**
	 * @Fields userId:用户ID
	 */
	private Integer userId;
	
	/**
	 * @Fields title:标题
	 */
	@NotNull(message="*标题不能为空")
	private String title;
	
	/**
	 * @Fields shortTitle:简短标题
	 */
	private String shortTitle;
	
	/**
	 * @Fields isAppDisplay:是否在移动端显示，默认不显示（0）
	 */
	private Integer isAppDisplay;
	
	/**
	 * @Fields author:作者
	 */
	private String author;
	
	/**
	 * @Fields origin:来源
	 */
	@NotNull(message="*来源不能为空")
	private String origin;
	
	/**
	 * @Fields originUrl:来源链接
	 */
	private String originUrl;
	
	/**
	 * @Fields description:描述
	 */
	private String description;
	
	private Integer hasTitleImg;
	
	private Integer isRecommend;
	/**
	 * @Fields releaseDate:发布日期
	 */
	@NotNull(message="*发布日期不能为空")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date releaseDate;
	
	/**
	 * @Fields topLevel:固顶级别
	 */
	private Integer topLevel;
	
	/**
	 * @Fields sortDate:排序日期
	 */
	@NotNull(message="*排序日期不能为空")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date sortDate;
	
	
//	private String titleImg;
	
	/**
	 * @Fields txt:文章内容（html）
	 */
	private String txt;
	
	/**
	 * @Fields plainTxt:文章带格式纯文本
	 */
	private String plainTxt;
	
	private String link;
	
	/**
	 * @Fields titleImg:标题图片
	 */
	private String titleImg;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getIsAppDisplay() {
		return isAppDisplay;
	}

	public void setIsAppDisplay(Integer isAppDisplay) {
		this.isAppDisplay = isAppDisplay;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getTopLevel() {
		return topLevel;
	}

	public void setTopLevel(Integer topLevel) {
		this.topLevel = topLevel;
	}

	public Date getSortDate() {
		return sortDate;
	}

	public void setSortDate(Date sortDate) {
		this.sortDate = sortDate;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getPlainTxt() {
		return plainTxt;
	}

	public void setPlainTxt(String plainTxt) {
		this.plainTxt = plainTxt;
	}

	public Integer getHasTitleImg() {
		return hasTitleImg;
	}

	public void setHasTitleImg(Integer hasTitleImg) {
		this.hasTitleImg = hasTitleImg;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
}
