package com.qzj.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzj.commos.exception.BizException;
import com.qzj.dao.WeiXinUserDao;
import com.qzj.dto.WeiXinUser;

@Service
public class LoginService extends BaseTgService {

	@Autowired
	WeiXinUserDao dao;
	
	public String add(String openId) {
		if (StringUtils.isBlank(openId)) {
			throw new BizException("openId is null", null, new HashMap<>());
		}
		WeiXinUser user = new WeiXinUser();
		Date d = new Date();
		user.setCreateTime(d);
		user.setUpdateTime(d);
		user.setCreateBy("SYS");
		user.setUpdateBy("SYS");
		user.setLastLoginTime(d);
		user.setOpenId(openId);
		dao.add(user);
		return "success";
	}
	
	public List<WeiXinUser> getByOpenId(String openId) {
		if (StringUtils.isBlank(openId)) {
			throw new BizException("openId is null", null, new HashMap<>());
		}
		return dao.getByOpenId(openId);
	}
	
	public long update(WeiXinUser user) {
		user.setLastLoginTime(new Date());
		return dao.update(user);
	}
	
}
