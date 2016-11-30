package module.entity;

public class WxCustomer {
	private Integer subscribe;
	private String openid;
	private String nickname;
	private Integer sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private Long subscribe_time;
	private String remark;
	private Integer groupid;
	private String unionid;
	private String[] tagid_list;

	public WxCustomer() {

	}

	public WxCustomer(String city, String country, Integer groupid, String headimgurl, String language, String nickname, String openid, String province, String remark, Integer sex, Integer subscribe, Long subscribe_time, String unionid) {
		this.city = city;
		this.country = country;
		this.groupid = groupid;
		this.headimgurl = headimgurl;
		this.language = language;
		this.nickname = nickname;
		this.openid = openid;
		this.province = province;
		this.remark = remark;
		this.sex = sex;
		this.subscribe = subscribe;
		this.subscribe_time = subscribe_time;
		this.unionid = unionid;
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

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
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

	public String[] getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(String[] tagid_list) {
		this.tagid_list = tagid_list;
	}

}
