package cn.umbrella.oss.model.wechat;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * @author zhang.xiaolei 
 * @Since 2010-2017
 * @create 2017-02-17 14:55:46
 * @history
 */
public class TwxcmsMsgBaseForm {

	//columns START
	
	/**
	 * @Fields id:id
	 */
	private String id;
	
	/**
	 * @Fields msg_type:消息类型
	 */
	@Length(max = 32, message = "消息类型的长度不能超过{1}")
	private String msgType;
	
	/**
	 * @Fields input_code:关注者发送的消息
	 */
	@Length(max = 32, message = "关注者发送的消息的长度不能超过{1}")
	private String inputCode;
	
	/**
	 * @Fields rule:规则
	 */
	@Length(max = 32, message = "规则的长度不能超过{1}")
	private String rule;
	
	/**
	 * @Fields enable:是否可用
	 */
	private Boolean enable;
	
	/**
	 * @Fields read_count:消息阅读数
	 */
	@Range(message = "数值范围不正确")
	private Integer readCount;
	
	/**
	 * @Fields media_id:素材ID
	 */
	@Length(max = 255, message = "素材ID的长度不能超过{1}")
	private String mediaId;
	//columns END
	
	private String content;
	
    private String type;
    private String baseUuid;
    private String mediaUuid;
    private String mediaContent;
    
	private String token;// 验证表单重复提交
	
	public void setUuid(String uuid) {
		this.id = uuid;
	}

	public TwxcmsMsgBaseForm(){
	}

	public TwxcmsMsgBaseForm(String id){
		this.id = id;
	}

	
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getBaseUuid() {
		return baseUuid;
	}

	public void setBaseUuid(String baseUuid) {
		this.baseUuid = baseUuid;
	}
	
}