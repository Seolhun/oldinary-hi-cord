package com.brain.home.entity.user;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	GUEST("gueset"),
	CUSTOMER("customer"),
	NURSE("nurse"),
	DOCTOR("doctor"),
	LEADER("leader"),
	ADMIN("admin"),
	SUPERADMIN("superadmin"),
	CUSTOMER1("customer1"),
	CUSTOMER2("customer2"),
	CUSTOMER3("customer3");
	
	String type;
	
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
