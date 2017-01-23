package com.brain.home.entity.survey;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SurveySecondQestion {
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("content")
	private String content;
	@JsonProperty("etc")
	private String etc;
	@JsonProperty("surveySelectList")
	private List<SurveySelect> surveySelectList;
}
