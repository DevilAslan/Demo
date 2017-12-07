package cn.umbrella.oss.wechat.bean;

/**
 * 微信api获取的用户属性
 * @author wmin
 */
public class UserInfo {
	
	//alias
	public static final String TABLE_ALIAS = "UserInfo";
	/**
	 * @Fields openid:用户的标识
	 */
	private String openid;
	
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
	private Long subscribe_time;
	
	/**
	 * @Fields unionid:unionid
	 */
	private String unionid;
	
	/**
	 * @Fields groupid:分组ID
	 */
	private Integer groupid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Long getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
}