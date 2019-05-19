package com.qzj.dto;

import com.qzj.entity.BaseTgEntity;

public class BookLyric extends BaseTgEntity{

	private static final long serialVersionUID = -2539606269516429059L;
	
	private String musicofviolin;
	
	private String desc;
	
	private String url;
	
	private Integer strokes;

	public String getMusicofviolin() {
		return musicofviolin;
	}

	public void setMusicofviolin(String musicofviolin) {
		this.musicofviolin = musicofviolin;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStrokes() {
		return strokes;
	}

	public void setStrokes(Integer strokes) {
		this.strokes = strokes;
	}
}
