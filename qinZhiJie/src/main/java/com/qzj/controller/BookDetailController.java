package com.qzj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.BookDetail;
import com.qzj.dto.PageRequest;
import com.qzj.dto.PageResult;
import com.qzj.service.BookDetailService;

@CrossOrigin
@RestController
@RequestMapping("/bookDetail")
public class BookDetailController extends BaseTgController {
	
	@Autowired
	private BookDetailService bookDetailService;

	/**
	 * 目录模糊查询，bookid 查询
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public ResponseData<PageResult<BookDetail>> getList(@RequestBody PageRequest<BookDetail> page, HttpServletRequest request) {
		ResponseData<PageResult<BookDetail>> result = new ResponseData<PageResult<BookDetail>>();
		List<BookDetail> list = bookDetailService.getList(page).getDataList();
		for(BookDetail b : list) {
			b.setAllPath("");
			b.setDirPath("");
		}
		PageResult<BookDetail> res = new PageResult<>();
		res.setDataList(list);
		result.setData(res);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
	
	@RequestMapping(value = "/getListSpec", method = RequestMethod.POST)
	public ResponseData<PageResult<Object>> getListSpec(@RequestBody PageRequest<BookDetail> page, HttpServletRequest request) {
		ResponseData<PageResult<Object>> result = new ResponseData<PageResult<Object>>();
		if(StringUtils.isBlank(page.getQueryObj().getPartName())) {
			result.setCode("200");
			result.setMessage("Success");
			return result;
		}
		List<Object> returnList = new ArrayList<>();
		List<BookDetail> list = bookDetailService.getList(page).getDataList();
		Set<Integer> bookNameSet = new HashSet<>(); 
		for(BookDetail b : list) {
			bookNameSet.add(b.getBookId());
			b.setAllPath("");
			b.setDirPath("");
		}
		for(Integer bookId : bookNameSet) {
			Map<String, Object> map = new HashMap<>();
			List<BookDetail> detailList = new ArrayList<>();
			for(BookDetail b : list) {
				if(b.getBookId() == bookId) {
					detailList.add(b);
				}
			}
			map.put("bookName", detailList.get(0).getBookName());
			map.put("bookId", detailList.get(0).getBookId());
			map.put("detailList", detailList);
			returnList.add(map);
		}
		PageResult<Object> res = new PageResult<>();
		res.setDataList(returnList);
		result.setData(res);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
	
	@RequestMapping(value = "/getPicList", method = RequestMethod.POST)
	public ResponseData<PageResult<BookDetail>> getPicList(@RequestBody PageRequest<BookDetail> page, HttpServletRequest request) {
		ResponseData<PageResult<BookDetail>> result = new ResponseData<PageResult<BookDetail>>();
		List<BookDetail> list = bookDetailService.getList(page).getDataList();
		for(BookDetail b : list) {
			b.setDirPath("");
		}
		PageResult<BookDetail> res = new PageResult<>();
		res.setDataList(list);
		result.setData(res);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
}
