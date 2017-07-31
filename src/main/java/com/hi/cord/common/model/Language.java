package com.hi.cord.common.model;

import java.util.Properties;

public class Language {
	private String language_code;
	private Properties text;

	public String getLanguage_code() {
		return language_code;
	}

	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	public Properties getText() {
		return text;
	}

	public void setText(Properties text) {
		this.text = text;
	}

}
