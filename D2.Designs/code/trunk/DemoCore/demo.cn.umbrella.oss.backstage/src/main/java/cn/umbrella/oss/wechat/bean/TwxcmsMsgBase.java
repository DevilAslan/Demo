package cn.umbrella.oss.wechat.bean;


/**
 * @author zhang.xiaolei 
 * @Since 2010-2017
 * @create 2017-02-13 13:32:11
 * @history
 */
public class TwxcmsMsgBase {
	
	//alias
	public static final String TABLE_ALIAS = "TwxcmsMsgBase";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields msgType:消息类型
	 */
	private String msgType;
	
	/**
	 * @Fields inputCode:关注者发送的消息
	 */
	private String inputCode;
	
	/**
	 * @Fields rule:规则
	 */
	private String rule;
	
	/**
	 * @Fields enable:是否可用
	 */
	private Boolean enable;
	
	/**
	 * @Fields readCount:消息阅读数
	 */
	private Integer readCount;
	
	/**
	 * @Fields mediaId:素材ID
	 */
	private String mediaId;
	
	//columns END
	
    private String type;
    private String baseUuid;
    private String mediaUuid;
    private String mediaContent;

	public TwxcmsMsgBase(){
	}

	public TwxcmsMsgBase(Integer id){
		this.id = id;
	}

	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setMsgType(String msgType){
		this.msgType = msgType;
	}
	
	public String getMsgType(){
		return msgType;
	}
	
	public void setInputCode(String inputCode){
		this.inputCode = inputCode;
	}
	
	public String getInputCode(){
		return inputCode;
	}
	
	public void setRule(String rule){
		this.rule = rule;
	}
	
	public String getRule(){
		return rule;
	}
	
	public void setEnable(Boolean enable){
		this.enable = enable;
	}
	
	public Boolean getEnable(){
		return enable;
	}
	
	public void setReadCount(Integer readCount){
		this.readCount = readCount;
	}
	
	public Integer getReadCount(){
		return readCount;
	}
	
	public void setMediaId(String mediaId){
		this.mediaId = mediaId;
	}
	
	public String getMediaId(){
		return mediaId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBaseUuid() {
		return baseUuid;
	}

	public void setBaseUuid(String baseUuid) {
		this.baseUuid = baseUuid;
	}

	public String getMediaUuid() {
		return mediaUuid;
	}

	public void setMediaUuid(String mediaUuid) {
		this.mediaUuid = mediaUuid;
	}

	public String getMediaContent() {
		return mediaContent;
	}

	public void setMediaContent(String mediaContent) {
		this.mediaContent = mediaContent;
	}
	
}