package com.brain.home.entity.survey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SurveySelect {
	@JsonProperty("number")
	private int number;
	@JsonProperty("point")
	private String point;
	@JsonProperty("value")
	private String value;
}
