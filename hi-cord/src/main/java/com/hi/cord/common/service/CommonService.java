package com.hi.cord.common.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hi.cord.common.model.Paging;

public interface CommonService {
	//int 유효성 검사하기.
	int checkVDInt(String parameter, int default_value);
	
	//Float 유효성 검사하기.
	float checkVDFloat(String parameter, int default_value);
	
	//검색어 유효성 검사하기.
	String checkVDQuestion(String question);
	
	//리스트 페이징하기.
	Paging setPaging(Paging paging);
	
	//JsonData를 VO에 매핑하기.
	ObjectMapper setJSONMapper() throws JsonProcessingException;
	
	//VO에 있는 값들 JSON으로 변환하기,
	String getJSONData(Object rawData) throws JsonProcessingException;
	
	//User IP를 가져오기.	
	String getUserIP();

//	void sendMessageToUser(String toUser, String primaryKey, HttpServletRequest request, Principal principal) throws Exception;
//	
//	void saveWhatIDid(String primaryKey, HttpServletRequest request, Principal principal) throws Exception;
//	
	//Encode SHA256
	String buildSHA256(String str);
	
	//get Validation about logiun User 	
	boolean getLoginAuthValidation(Authentication auth, String authNameYouWant);
	
	//Post Email Locked user.
	void sendEmailLockingUser(String toEmail, String userName, String authentication, String httpPath, String password) throws IOException;
	
	//@Valid로 검사시 중복값 리다이렉트해주기.
	void validCheckAndSendError(MessageSource messageSource, BindingResult result, HttpServletRequest request, String objectValidValue, String fieldObjectName, String fieldName, String messagePropertyName);
}
