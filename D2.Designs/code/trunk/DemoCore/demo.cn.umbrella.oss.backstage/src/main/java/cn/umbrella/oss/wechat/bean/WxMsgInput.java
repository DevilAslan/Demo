package cn.umbrella.oss.wechat.bean;


/**
 * @author zhang.xiaolei 
 * @Since 2010-2017
 * @create 2017-02-24 13:19:24
 * @history
 */
public class WxMsgInput {
	
	//alias
	public static final String TABLE_ALIAS = "WxMsgInput";
	
	//columns START
	/**
	 * @Fields id:id,uuid
	 */
	private String id;
	
	/**
	 * @Fields toUserName:接收方帐号（一个OpenID）
	 */
	private String toUserName;
	
	/**
	 * @Fields fromUserName:发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	
	/**
	 * @Fields createTime:消息创建时间 （整型）
	 */
	private Long createTime;
	
	/**
	 * @Fields msgType:消息类型（text/image/voice/event/shortvoice/link/location）
	 */
	private String msgType;
	
	/**
	 * @Fields msgid:消息id，64位整型
	 */
	private Long msgid;
	
	/**
	 * @Fields content:消息内容
	 */
	private String content;
	
	/**
	 * @Fields picUrl:图片消息
	 */
	private String picUrl;
	
	/**
	 * @Fields mediaId:素材id，通过素材管理接口上传多媒体文件，得到的id
	 */
	private String mediaId;
	
	/**
	 * @Fields thumbMediaId:图文消息的封面图片素材id（必须是永久mediaID）
	 */
	private String thumbMediaId;
	
	/**
	 * @Fields format:语音格式，如amr，speex等
	 */
	private String format;
	
	/**
	 * @Fields locationX:地理位置维度
	 */
	private String locationX;
	
	/**
	 * @Fields locationY:地理位置经度
	 */
	private String locationY;
	
	/**
	 * @Fields scale:地图缩放大小
	 */
	private String scale;
	
	/**
	 * @Fields locationLabel:地理位置信息
	 */
	private String locationLabel;
	
	/**
	 * @Fields title:消息标题
	 */
	private String title;
	
	/**
	 * @Fields description:消息描述
	 */
	private String description;
	
	/**
	 * @Fields url:消息链接
	 */
	private String url;
	
	/**
	 * @Fields event:事件类型，subscribe(订阅)、unsubscribe(取消订阅)、SCAN、LOCATION、CLICK、VIEW
	 */
	private String event;
	
	/**
	 * @Fields eventKey:事件KEY值
	 */
	private String eventKey;
	
	/**
	 * @Fields ticket:二维码的ticket
	 */
	private String ticket;
	
	/**
	 * @Fields latitude:纬度
	 */
	private String latitude;
	
	/**
	 * @Fields longitude:经度
	 */
	private String longitude;
	
	/**
	 * @Fields locationPrecision:精度
	 */
	private String locationPrecision;
	
	/**
	 * @Fields is_reply:是否回复:0未回复1已回复
	 */
	private Integer isReply;
	
	//columns END

	public WxMsgInput(){
	}

	public WxMsgInput(String id){
		this.id = id;
	}

	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
	public void setToUserName(String toUserName){
		this.toUserName = toUserName;
	}
	
	public String getToUserName(){
		return toUserName;
	}
	
	public void setFromUserName(String fromUserName){
		this.fromUserName = fromUserName;
	}
	
	public String getFromUserName(){
		return fromUserName;
	}
	
	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}
	
	public Long getCreateTime(){
		return createTime;
	}
	
	public void setMsgType(String msgType){
		this.msgType = msgType;
	}
	
	public String getMsgType(){
		return msgType;
	}
	
	public void setMsgid(Long msgid){
		this.msgid = msgid;
	}
	
	public Long getMsgid(){
		return msgid;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}
	
	public String getPicUrl(){
		return picUrl;
	}
	
	public void setMediaId(String mediaId){
		this.mediaId = mediaId;
	}
	
	public String getMediaId(){
		return mediaId;
	}
	
	public void setThumbMediaId(String thumbMediaId){
		this.thumbMediaId = thumbMediaId;
	}
	
	public String getThumbMediaId(){
		return thumbMediaId;
	}
	
	public void setFormat(String format){
		this.format = format;
	}
	
	public String getFormat(){
		return format;
	}
	
	public void setLocationX(String locationX){
		this.locationX = locationX;
	}
	
	public String getLocationX(){
		return locationX;
	}
	
	public void setLocationY(String locationY){
		this.locationY = locationY;
	}
	
	public String getLocationY(){
		return locationY;
	}
	
	public void setScale(String scale){
		this.scale = scale;
	}
	
	public String getScale(){
		return scale;
	}
	
	public void setLocationLabel(String locationLabel){
		this.locationLabel = locationLabel;
	}
	
	public String getLocationLabel(){
		return locationLabel;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setEvent(String event){
		this.event = event;
	}
	
	public String getEvent(){
		return event;
	}
	
	public void setEventKey(String eventKey){
		this.eventKey = eventKey;
	}
	
	public String getEventKey(){
		return eventKey;
	}
	
	public void setTicket(String ticket){
		this.ticket = ticket;
	}
	
	public String getTicket(){
		return ticket;
	}
	
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
	
	public String getLatitude(){
		return latitude;
	}
	
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
	
	public String getLongitude(){
		return longitude;
	}
	
	public void setLocationPrecision(String locationPrecision){
		this.locationPrecision = locationPrecision;
	}
	
	public String getLocationPrecision(){
		return locationPrecision;
	}

	public Integer getIsReply() {
		return isReply;
	}

	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}

}