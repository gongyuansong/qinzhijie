package com.qzj.dto;

import com.qzj.entity.BaseTgEntity;

public class Music extends BaseTgEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name = "";
	
	private Integer musicianId;
	
	private String musican = "";
	
	private String albumName = "";
	
	private String mp3Url = "";
	
	private String cueUrl ="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMusicianId() {
		return musicianId;
	}

	public void setMusicianId(Integer musicianId) {
		this.musicianId = musicianId;
	}

	public String getMusican() {
		return musican;
	}

	public void setMusican(String musican) {
		this.musican = musican;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getMp3Url() {
		return mp3Url;
	}

	public void setMp3Url(String mp3Url) {
		this.mp3Url = mp3Url;
	}

	public String getCueUrl() {
		return cueUrl;
	}

	public void setCueUrl(String cueUrl) {
		this.cueUrl = cueUrl;
	}
}
