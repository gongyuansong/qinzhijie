package com.qzj.dto;

import com.qzj.entity.BaseTgEntity;

public class BookDetail extends BaseTgEntity{

	/**
	 * t_book_detail， 书本所有章节
	 */
	private static final long serialVersionUID = -2539606269516429059L;

	private Integer bookId;
	
	private String bookName;
	
	private String partName;
	
	private String dirPath;
	
	private Integer orderNum;

	private String allPath;
	
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAllPath() {
		return allPath;
	}

	public void setAllPath(String allPath) {
		this.allPath = allPath;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
