package com.brain.home.entity.survey.result;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.brain.home.entity.survey.Survey;

import lombok.Data;

@Data
@Entity(name = "SURVEY_BINDING")
public class SurveyBinding implements Serializable {
	private static final long serialVersionUID = 5384993050048786998L;
	// Header
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SURVEY_BINDING_ID")
	private Long SurveyBindingId;
	
	// 환자에게 설문을 보낸 의사의 키값, 설문의 소속을 나타낸다. 환자는 유저가 아니기에.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SURVEY_BINDING_IN_PATIENT_ID", foreignKey = @ForeignKey(name = "SURVEY_RESULT_FK"), referencedColumnName = "PATIENT_ID", nullable = false)
	private Patient surveyBindingInPatientId;
	
	// 환자 설문 접근하기 위한 권한 유효값
	@Column(name = "PATIENT_ACCESS_PRIVILEGE_KEY", nullable = false, length=100)
	private String patientAccessPrivilegeKey;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "SURVEY_BINDING_REFER",foreignKey=@ForeignKey(name="SURVEY_BINDING_FK"),  
		joinColumns = { @JoinColumn(name = "SURVEY_BINDING_ID", columnDefinition="BIGINT(20)") }, 
		inverseForeignKey=@ForeignKey(name="SURVEY_REFER_FK"), inverseJoinColumns = {@JoinColumn(name = "SURVEY_ID") })
	private Set<Survey> surveyList;

	@OneToMany(mappedBy = "surveyBindingInResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SurveyResult> surveyResultList;
}
