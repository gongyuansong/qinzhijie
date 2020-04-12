package com.qzj.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.PageResult;
import com.qzj.service.ImportService;

@RestController
@RequestMapping("/ImportDataTo")
/**
 * 这个controller是导入数据到数据库使用的
 * @author YuanSongGong
 *
 */
public class ImportDataToDBController {

	@Autowired
	private ImportService importService;

	@RequestMapping(value = "/musician", method = RequestMethod.POST)
	public ResponseData<PageResult<String>> musician(){
		ResponseData<PageResult<String>> result = new ResponseData<PageResult<String>>();
		importService.importMusican();
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	
	//导入图片
	@RequestMapping(value = "/ipmortMusicianPic", method = RequestMethod.POST)
	public ResponseData<PageResult<String>> ipmortMusicianPic(){
		ResponseData<PageResult<String>> result = new ResponseData<PageResult<String>>();
		importService.ipmortMusicianPic1();
		importService.ipmortMusicianPic2();
		importService.ipmortMusicianPic3();
		importService.ipmortMusicianPic4();
		importService.ipmortMusicianPic5();
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	
	//导入历代琴谱到数据库
	@RequestMapping(value = "/ipmortBook", method = RequestMethod.POST)
	public ResponseData<PageResult<String>> ipmortBook(){
		ResponseData<PageResult<String>> result = new ResponseData<PageResult<String>>();
		importService.importBookAll();
		importService.importBookAll1();
		importService.updateTimeTOBook();
		importService.importAllPath();
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	
	//导入歌词到数据库
	@RequestMapping(value = "/ipmortBookLyric", method = RequestMethod.POST)
	public ResponseData<PageResult<String>> ipmortBookLyric() throws IOException{
		ResponseData<PageResult<String>> result = new ResponseData<PageResult<String>>();
		importService.ipmortBookLyric();
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	
	//导入指法到数据库
	@RequestMapping(value = "/ipmortFingering", method = RequestMethod.POST)
	public ResponseData<PageResult<String>> ipmortFingering() throws IOException{
		ResponseData<PageResult<String>> result = new ResponseData<PageResult<String>>();
		importService.ipmortFingering();
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	
	
	
	//最新的bookdetail 导入  20200408
	@RequestMapping(value = "/insertBookDetail", method = RequestMethod.GET)
	public ResponseData<PageResult<String>> insertBookDetail(){
		ResponseData<PageResult<String>> result = new ResponseData<PageResult<String>>();
		importService.insertBookDetail();
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	
}
