package com.qzj.controller;

import java.util.List;

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
		
		
		PageRequest<BookLyric> bookLyricPage = new PageRequest<>();
		BookLyric bookLyric = new BookLyric();
		bookLyric.setDesc(queryStr);
		bookLyricPage.setQueryObj(bookLyric);
		List<BookLyric> bookLyricList = bookLyricService.getList(bookLyricPage).getDataList();
		
		Station station = new Station();
		station.setBookList(bookList);
		station.setLyricList(bookLyricList);
		
		result.setData(station);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
}
