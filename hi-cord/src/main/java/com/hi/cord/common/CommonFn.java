package com.hi.cord.common;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hi.cord.common.model.Language;
import com.hi.cord.common.model.Paging;

public interface CommonFn {
	Language setLanguageData(Properties text_ko, Properties text_en, HttpServletRequest request);
	
	void setDefaultSetting(ModelMap model, Language language, String target, String targetName);

	int checkVDInt(String parameter, int default_value);
	
	float checkVDFloat(String parameter, int default_value);
	
	String checkVDQuestion(String question);
	
	ObjectMapper setJSONMapper() throws JsonProcessingException;

	String getJSONData(Object rawData) throws JsonProcessingException;

	Paging setPaging(Paging paging);

	String getPrincipal();
	
	String getUserIP();

	boolean checkValidConnectPage(String email);
	
	String buildSHA256(String str);
}
