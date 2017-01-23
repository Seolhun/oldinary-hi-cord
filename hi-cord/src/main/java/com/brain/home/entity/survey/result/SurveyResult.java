package com.brain.home.entity.survey.result;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.brain.home.entity.survey.SurveySelect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity(name="SURVEY_RESULT")
public class SurveyResult implements Serializable {
	private static final long serialVersionUID = 1737784313656552848L;
	//Header
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SURVEY_RESULT_ID")
	private Long surveyResultId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SURVEY_BINDING_ID", foreignKey = @ForeignKey(name = "SURVEY_RESULT_BINDING_FK"),  referencedColumnName = "SURVEY_BINDING_ID", nullable = false)
	private SurveyBinding surveyBindingInResult;

	@Column(name="SURVEY_ID", length=30)
	private Integer surveyId;
	@Column(name="SURVEY_NAME", length=60)
	private String surveyName;
	@Column(name="SURVEY_TYPE", length=30)
	private String surveyType;
	@Column(name="SURVEY_STATE", length=10)
	private String surveyState;
	
	@Lob
	@Column(name="SURVEY_RESULT_SELECT_JSON")
	private String surveyResultSelectJsonValues;
	
	@Transient
	@JsonProperty("SURVEY_RESULT_SELECT_JSON")
	private List<SurveySelect> surveyResultSelectList;
}
