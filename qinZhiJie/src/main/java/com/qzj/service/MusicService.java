package com.qzj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qzj.dao.MusicDao;
import com.qzj.dto.Music;
import com.qzj.dto.PageRequest;
import com.qzj.dto.PageResult;

@Service
public class MusicService {

	@Autowired
	private MusicDao musicDao;
	
	public PageResult<Music> getList(PageRequest<Music> page){
		PageResult<Music> pageResult = new PageResult<>();
		pageResult.setPageSize(page.getPageSize());
		pageResult.setDataList(musicDao.getList(page));
		pageResult.setTotal(musicDao.getTotal(page));
		return pageResult;
	}
	
	public void insert(Music music){
		musicDao.add(music);
	}
}
