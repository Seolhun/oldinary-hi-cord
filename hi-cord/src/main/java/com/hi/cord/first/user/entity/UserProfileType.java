package com.hi.cord.first.user.entity;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	PLAYER("player"),
	CAPTAIN("captain"),
	DIRECTOR("director"),
	COACH("coach"),
	REFEREE("referee"),
	OWNER("owner"),
	MASTER("master");
	
	private String type;
	
	private UserProfileType(String type){
		this.type = type;
	}
	
	public String getUserProfileType(){
		return type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString(){
		return this.type;
	}

	public String getName(){
		return this.name();
	}
}
