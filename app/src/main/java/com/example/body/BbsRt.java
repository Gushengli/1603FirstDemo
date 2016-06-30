package com.example.body;

public class BbsRt {

	public String title;
	public String author;
	public int views;
	public int reply;
	public String doc_url_v2;
	public BbsRt(String title, String author, int views, int reply,
			String doc_url_v2) {
		super();
		this.title = title;
		this.author = author;
		this.views = views;
		this.reply = reply;
		this.doc_url_v2 = doc_url_v2;
	}
	@Override
	public String toString() {
		return "BbsRt [title=" + title + ", author=" + author + ", views="
				+ views + ", reply=" + reply + ", doc_url_v2=" + doc_url_v2
				+ "]";
	}
	
	
}
