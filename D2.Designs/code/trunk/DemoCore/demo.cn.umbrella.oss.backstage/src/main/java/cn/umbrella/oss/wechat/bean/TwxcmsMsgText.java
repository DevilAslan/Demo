package cn.umbrella.oss.wechat.bean;



/**
 * @author zhang.xiaolei 
 * @Since 2010-2017
 * @create 2017-02-10 15:08:53
 * @history
 */
public class TwxcmsMsgText extends TwxcmsMsgBase {
	
	//alias
	public static final String TABLE_ALIAS = "TwxcmsMsgText";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields content:content
	 */
	private String content;
	
	/**
	 * @Fields baseId:baseId
	 */
	private Integer baseId;
	
	//columns END

	public TwxcmsMsgText(){
	}

	public TwxcmsMsgText(Integer id){
		this.id = id;
	}

	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setBaseId(Integer baseId){
		this.baseId = baseId;
	}
	
	public Integer getBaseId(){
		return baseId;
	}

}