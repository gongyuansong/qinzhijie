package com.qzj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzj.dto.PageRequest;
import com.qzj.dto.WeiXinUser;

public interface WeiXinUserDao {

	public List<WeiXinUser> getList(@Param(value="page") PageRequest<?> page);
	
	public long getTotal(@Param(value="page") PageRequest<?> page);
	
	public long add(@Param(value="user") WeiXinUser user);
	
	public long update(@Param(value="user") WeiXinUser user);
	
	public long delete(@Param(value="list") List<String> list);
	
	public List<WeiXinUser> getByOpenId(@Param(value="openId") String openId);
}
