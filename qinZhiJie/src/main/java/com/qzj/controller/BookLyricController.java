package com.qzj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.BookLyric;
import com.qzj.dto.PageRequest;
import com.qzj.dto.PageResult;
import com.qzj.service.BookLyricService;

@CrossOrigin
@RestController
@RequestMapping("/bookLyric")
public class BookLyricController extends BaseTgController {
	
	@Autowired
	private BookLyricService bookLyricService;

	/**
	 * 琴曲查询，支持模糊查询
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public ResponseData<PageResult<BookLyric>> getList(@RequestBody PageRequest<BookLyric> page, HttpServletRequest request) {
		ResponseData<PageResult<BookLyric>> result = new ResponseData<PageResult<BookLyric>>();
		PageResult<BookLyric> p = new PageResult<>();
		
		List<BookLyric> list = bookLyricService.getList(page).getDataList();
		if(page.getQueryObj().getId() == null && StringUtils.isBlank(page.getQueryObj().getDesc()) && StringUtils.isBlank(page.getQueryObj().getMusicofviolin())) {
			for(BookLyric b : list) {
				//没有用的就设空
				b.setDesc(null);
				b.setUrl(null);
			}
		}
		p.setDataList(list);
		result.setData(p);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
}
