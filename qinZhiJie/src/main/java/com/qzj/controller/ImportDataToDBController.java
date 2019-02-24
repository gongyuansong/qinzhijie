package com.qzj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.PageResult;
import com.qzj.service.ImportService;

@RestController
@RequestMapping("/ImportDataTo")
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
		result.setCode("200");
		result.setMessage("success");
		return result;
	}
	
}