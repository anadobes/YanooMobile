package com.anadobes.yanoo.races.models;

import java.util.Date;

public class Race {

	private String name;
	private Date date;
	private String description;

	public Race(String name, Date date, String description) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
