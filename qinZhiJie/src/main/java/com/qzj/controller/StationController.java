package com.qzj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.Book;
import com.qzj.dto.BookLyric;
import com.qzj.dto.PageRequest;
import com.qzj.dto.Station;
import com.qzj.service.BookLyricService;
import com.qzj.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/station")
public class StationController extends BaseTgController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookLyricService bookLyricService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseData<Station> getList(@RequestParam("queryStr") String queryStr, HttpServletRequest request) {
		ResponseData<Station> result = new ResponseData<Station>();
		PageRequest<Book> bookPage = new PageRequest<>();
		Book book = new Book();
		book.setBookName(queryStr);
		bookPage.setQueryObj(book);
		List<Book> bookList = bookService.getList(bookPage).getDataList();
		for(Book b : bookList) {
			b.setDirPath("");
		}
		PageRequest<BookLyric> bookLyricPage = new PageRequest<>();
		BookLyric bookLyric = new BookLyric();
		bookLyric.setDesc(queryStr);
		bookLyricPage.setQueryObj(bookLyric);
		List<BookLyric> bookLyricList = bookLyricService.getList(bookLyricPage).getDataList();
		for(BookLyric b : bookLyricList) {
			b.setDesc("");
			b.setUrl("");
		}
		
		Station station = new Station();
		station.setBookList(bookList);
		station.setLyricList(bookLyricList);
		
		result.setData(station);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
	
	
	@RequestMapping(value = "/searchStatus", method = RequestMethod.POST)
	public ResponseData<Map<String, Integer>> searchStatus(@RequestParam("queryStr") String queryStr, HttpServletRequest request) {
		ResponseData<Map<String, Integer>> result = new ResponseData<Map<String, Integer>>();
		PageRequest<Book> bookPage = new PageRequest<>();
		Map<String, Integer> resultMap = new HashMap<>();
		Book book = new Book();
		book.setBookName(queryStr);
		bookPage.setQueryObj(book);
		List<Book> bookList = bookService.getList(bookPage).getDataList();
		
		if(bookList != null && bookList.size() != 0) {
			resultMap.put("liDaiQinPuStatus", 1);
		}else {
			resultMap.put("liDaiQinPuStatus", 0);
		}
		
		PageRequest<BookLyric> bookLyricPage = new PageRequest<>();
		BookLyric bookLyric = new BookLyric();
		bookLyric.setDesc(queryStr);
		bookLyricPage.setQueryObj(bookLyric);
		List<BookLyric> bookLyricList = bookLyricService.getList(bookLyricPage).getDataList();
		if(bookLyricList != null && bookLyricList.size() != 0) {
			resultMap.put("qinquStatus", 1);
		}else {
			resultMap.put("qinquStatus", 0);
		}
		resultMap.put("qinshengStatus", 0);
		result.setData(resultMap);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
	
}
