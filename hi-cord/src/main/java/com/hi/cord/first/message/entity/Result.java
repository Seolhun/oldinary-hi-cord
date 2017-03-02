package com.hi.cord.first.message.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Result implements Serializable {
	private static final long serialVersionUID = -7560490606054632981L;
	private Long id;
	private String message;
	private String fromUser;
	private String toUser;
	
}