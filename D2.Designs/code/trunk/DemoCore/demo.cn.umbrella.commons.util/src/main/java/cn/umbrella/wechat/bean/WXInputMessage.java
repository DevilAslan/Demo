package cn.umbrella.wechat.bean;

public class WXInputMessage extends BaseMessage {
	// 文本消息
	private String Content;
	// 图片消息
	private String PicUrl;
	// 位置消息
	private String Location_X;
	private String Location_Y;
	private Long Scale;
	private String Label;
	// 链接消息
	private String Title;
	private String Description;
	private String Url;
	// 语音信息
	private String MediaId;
	private String Format;
	private String Recognition;

	// 视频信息
	private String ThumbMediaId;

	// 事件
	// 事件类型
	private String Event;
	// 事件KEY值
	private String EventKey;
	// 二维码的ticket
	private String Ticket;

	// 上报地理位置事件
	// 地理位置纬度
	private String Latitude;
	// 地理位置经度
	private String Longitude;
	// 地理位置精度
	private String Precision;

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		Content = content;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return PicUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	/**
	 * @return the location_X
	 */
	public String getLocation_X() {
		return Location_X;
	}

	/**
	 * @param location_X
	 *            the location_X to set
	 */
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	/**
	 * @return the location_Y
	 */
	public String getLocation_Y() {
		return Location_Y;
	}

	/**
	 * @param location_Y
	 *            the location_Y to set
	 */
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	/**
	 * @return the scale
	 */
	public Long getScale() {
		return Scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(Long scale) {
		Scale = scale;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return Label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		Label = label;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return Url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		Url = url;
	}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return MediaId;
	}

	/**
	 * @param mediaId
	 *            the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return Format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		Format = format;
	}

	/**
	 * @return the recognition
	 */
	public String getRecognition() {
		return Recognition;
	}

	/**
	 * @param recognition
	 *            the recognition to set
	 */
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return Event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(String event) {
		Event = event;
	}

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return EventKey;
	}

	/**
	 * @param eventKey
	 *            the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return Ticket;
	}

	/**
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return Latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return Longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	/**
	 * @return the precision
	 */
	public String getPrecision() {
		return Precision;
	}

	/**
	 * @param precision
	 *            the precision to set
	 */
	public void setPrecision(String precision) {
		Precision = precision;
	}

}