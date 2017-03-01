package com.hi.cord.common.model;

public enum CommonState {

	ACTIVE("active"),
	EXPIRED("expired"),
	DELETED("deleted"),
	LOCKED("locked");
	
	private String state;
	
	private CommonState(final String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
	}

	public String getName(){
		return this.name();
	}

	@Override
	public String toString(){
		return this.state;
	}



}
