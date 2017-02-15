package com.hi.cord.first.entity.message;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 5199613846851497179L;

	private String key;

	private String value;

	private String text;

	private String toUser;

	public Message() {

	}
	
	public Message(String key, String value, String text, String toUser) {
		this.key = key;
		this.value = value;
		this.text = text;
		this.toUser = toUser;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
}