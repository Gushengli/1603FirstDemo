package com.example.body;

public class Scenery {

	public String title = null;
	public String pic_url = null;
	public String detail_url = null;
	
	public Scenery() {
		super();
	}

	public Scenery(String title, String pic_url, String detail_url) {
		super();
		this.title = title;
		this.pic_url = pic_url;
		this.detail_url = detail_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	@Override
	public String toString() {
		return "Scenery [title=" + title + ", pic_url=" + pic_url
				+ ", detail_url=" + detail_url + "]";
	}
	
	
	
	
}
