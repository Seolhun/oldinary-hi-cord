package com.hi.cord.first.club.entity;

import java.io.Serializable;

public enum SportsType implements Serializable{
	Unchosen ("unchosen"),
	Balling("balling"),
	Tennis("tennis"),
	Bicycle("bicycle"),
	Pingpong("Pingpong"),
	Badminton("badminton");
	
	private String type;
	
	private SportsType(String type){
		this.type = type;
	}
	
	public String getSportsType(){
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
