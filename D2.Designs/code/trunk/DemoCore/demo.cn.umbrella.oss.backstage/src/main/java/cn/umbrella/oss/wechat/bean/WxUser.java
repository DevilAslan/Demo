package cn.umbrella.oss.wechat.bean;
import java.util.Date;


/**
 * @author guagnduo.tech
 * @Since 2013-2016
 * @create 2016-06-12 10:41:51
 * @history
 */
public class WxUser {
	
	//alias
	public static final String TABLE_ALIAS = "WxUser";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Long id;
	
	/**
	 * @Fields openid:用户的标识
	 */
	private String openid;
	
	/**
	 * @Fields userId:用户id
	 */
	private Integer userId;
	
	/**
	 * @Fields subscribe:用户是否订阅该公众号标识：0是,1否
	 */
	private Boolean subscribe;
	
	/**
	 * @Fields nickname:昵称
	 */
	private String nickname;
	
	/**
	 * @Fields sex:性别(0:未知1:男2:女)
	 */
	private Integer sex;
	
	/**
	 * @Fields city:所在城市
	 */
	private String city;
	
	/**
	 * @Fields country:所在国家
	 */
	private String country;
	
	/**
	 * @Fields province:所在省份
	 */
	private String province;
	
	/**
	 * @Fields language:语言
	 */
	private String language;
	
	/**
	 * @Fields headimgurl:用户头像
	 */
	private String headimgurl;
	
	/**
	 * @Fields subscribeTime:用户关注时间
	 */
	private Long subscribeTime;
	
	/**
	 * @Fields remark:备注
	 */
	private String remark;
	
	/**
	 * @Fields groupid:分组ID
	 */
	private Integer groupid;
	
	/**
	 * @Fields unionid:unionid
	 */
	private String unionid;
	
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
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	/**
	 * @Fields name GroupName组名
	 */
	private String name;
	
	//columns END

	public WxUser(){
	}

	public WxUser(Long id){
		this.id = id;
	}

	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	
	public String getOpenid(){
		return openid;
	}
	
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
	public Integer getUserId(){
		return userId;
	}
	
	public void setSubscribe(Boolean subscribe){
		this.subscribe = subscribe;
	}
	
	public Boolean getSubscribe(){
		return subscribe;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public String getNickname(){
		return nickname;
	}
	
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
	public Integer getSex(){
		return sex;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getCountry(){
		return country;
	}
	
	public void setProvince(String province){
		this.province = province;
	}
	
	public String getProvince(){
		return province;
	}
	
	public void setLanguage(String language){
		this.language = language;
	}
	
	public String getLanguage(){
		return language;
	}
	
	public void setHeadimgurl(String headimgurl){
		this.headimgurl = headimgurl;
	}
	
	public String getHeadimgurl(){
		return headimgurl;
	}
	
	public void setSubscribeTime(Long subscribeTime){
		this.subscribeTime = subscribeTime;
	}
	
	public Long getSubscribeTime(){
		return subscribeTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setGroupid(Integer groupid){
		this.groupid = groupid;
	}
	
	public Integer getGroupid(){
		return groupid;
	}
	
	public void setUnionid(String unionid){
		this.unionid = unionid;
	}
	
	public String getUnionid(){
		return unionid;
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
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	
	public Integer getDeleted(){
		return deleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}