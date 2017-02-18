package com.hi.cord.common;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hi.cord.common.model.Paging;

public interface CommonFn {
int checkVDInt(String parameter, int default_value);
	
	float checkVDFloat(String parameter, int default_value);
	
	String checkVDQuestion(String question);
	
	Paging setPaging(Paging paging);
	
	ObjectMapper setJSONMapper() throws JsonProcessingException;

	String getJSONData(Object rawData) throws JsonProcessingException;
	
	String getUserIP();

	String buildSHA256(String str);
	
	boolean getLoginAuthValidation(Authentication auth, String authNameYouWant);
}
