package com.brain.home.entity.survey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SurveyMethod {
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("content")
	private String content;
	@JsonProperty("etc")
	private String etc;
}
