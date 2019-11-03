package com.qzj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.Book;
import com.qzj.dto.PageRequest;
import com.qzj.dto.PageResult;
import com.qzj.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController extends BaseTgController {
	
	@Autowired
	private BookService bookService;

	/**
	 * 获取book列表，可以通过名称查找， 支持模糊查找和分页
	 * @param page
	 * @param request
	 * @return
	 * 
	 * 查询的body参数
	 {
    "currentPage": "1",
    "pageSize": "10",
    "queryObj": {"bookName": "风"}
}
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public ResponseData<PageResult<Book>> getList(@RequestBody PageRequest<Book> page, HttpServletRequest request) {
		ResponseData<PageResult<Book>> result = new ResponseData<PageResult<Book>>();
		result.setData(bookService.getList(page));
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
}
