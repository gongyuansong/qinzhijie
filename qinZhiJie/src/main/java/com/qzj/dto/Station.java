package com.qzj.dto;

import java.util.ArrayList;
import java.util.List;

public class Station {

	private List<Book> bookList = new ArrayList<>();
	
	private List<BookLyric> lyricList = new ArrayList<>();

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public List<BookLyric> getLyricList() {
		return lyricList;
	}

	public void setLyricList(List<BookLyric> lyricList) {
		this.lyricList = lyricList;
	}
}
