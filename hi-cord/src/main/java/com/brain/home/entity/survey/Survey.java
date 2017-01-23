package com.brain.home.entity.survey;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity(name="SURVEY")
public class Survey implements Serializable{
	private static final long serialVersionUID = -1142733998347389952L;
	// Header
	@Id
	@Column(name="SURVEY_ID", length=30)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer surveyId;
	@Column(name="SURVEY_NAME", length=60)
	private String surveyName;
	@Column(name="SURVEY_TYPE", length=30)
	private String surveyType;
	
	@Column(name="HOW_TO_SURVEY_SUBJECT", length=255)
	private String howToSurveySubject;
	
	@Lob
	@Column(name="HOW_TO_SURVEY_CONTENT")
	private String howToSurveyContent;
	
	@Column(name="HOW_TO_SURVEY_ETC", length=255)
	private String howToSurveyEtc;
	
	@Column(name="MASTER_OF_SURVEY")
	private String masterOfSurvey;
	
	@Lob
	@Column(name="SURVEY_FIRST_QUESTION_JSON")
	private String surveyFirstQuestionsJsonValues;
	
	@Transient
	@JsonProperty("SURVEY_FIRST_QUESTION_JSON")
	private List<SurveyFirstQuestion> surveyFirstQuestionList;
}
