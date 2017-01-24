package com.brain.home.entity.message;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = -7560490606054632981L;
	private String message;
	private String fromUser;
	private String toUser;
	
	public Result() {
	}

	public Result(String message, String fromUser) {
		this.message = message;
		this.fromUser = fromUser;
	}

	public Result(String message, String fromUser, String toUser) {
		this.message = message;
		this.fromUser = fromUser;
		this.toUser = toUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public Result(String message) {
		this.message = message;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}