package com.anadobes.yanoo.races.models;

import java.util.Date;

public class Race {

	private String name;
	private Date date;
	private String description;
	private String iconFileName;

	public Race(String name, Date date, String description, String iconFilename) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.iconFileName = iconFilename;
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

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}
}
