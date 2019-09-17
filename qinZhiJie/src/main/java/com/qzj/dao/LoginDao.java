package com.qzj.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qzj.dto.Book;
import com.qzj.dto.PageRequest;

@Mapper
public interface LoginDao {

	public long add(@Param(value="book") Book book);
	
	List<Book> selectList(HashMap<String, Object> map);
	
	public List<Book> getList(@Param(value="page") PageRequest<Book> page);
	
	public long getTotal(@Param(value="page") PageRequest<Book> page);
	
	public Integer selectMaxId();
	
	public long update(@Param(value="book") Book book);
}
