package com.anadobes.yanoo.news.models;

import java.util.Date;

public class News {

	private String title;
	private Date date;
	private String writer;
	private String content;

	public News(String title, Date date, String writer, String content) {
		super();
		this.title = title;
		this.date = date;
		this.writer = writer;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
