package com.qzj.dto;

import java.util.Date;

import com.qzj.entity.BaseTgEntity;

public class WeiXinUser extends BaseTgEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * t_weixin_user， 登陆用户
	 */
	private String city;
	
	private String openId;
	
	private String nikeName;
	
	private String province;
	
	private String avatarUrl;
	
	private Date lastLoginTime;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
