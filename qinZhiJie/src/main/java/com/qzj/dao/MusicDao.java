package com.qzj.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qzj.dto.Music;
import com.qzj.dto.PageRequest;

public interface MusicDao {

	List<Music> selectList(HashMap<String, Object> map);
	
	public List<Music> getList(@Param(value="page") PageRequest<Music> page);
	
	public long getTotal(@Param(value="page") PageRequest<Music> page);
	
	public long add(@Param(value="music") Music music);
}
