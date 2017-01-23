package com.brain.home.entity.survey.result;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "PATIENT")
public class Patient implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATIENT_ID")
	private Long id;
	
	@Column(name = "PATIENT_CHART_ID", unique = true, nullable = false, length = 100)
	private String chartId;
	
	@Column(name = "PATIENT_NAME", nullable = false, length = 30)
	private String name;
	
	@Column(name = "PATIENT_BIRTH", length = 30)
	private Date birth;
	
	@Column(name = "PATIENT_GENDER", length = 5)
	private String gender;
	
	@Column(name = "PATIENT_HAND", length = 5)
	private String hand;
	
	@Column(name = "PATIENT_BLOOD",  length = 5)
	private String blood;
	
	@Column(name = "PATIENT_HEIGHT", length = 5)
	private String height;
	
	@Column(name = "PATIENT_WEIGHT",length = 5)
	private String weight;
	
	@Column(name = "PATIENT_PHONE", length = 20)
	private String phone;
	
	@Column(name = "PATIENT_PMID_KEY", length = 100)
	private String pmidKey;
	
	@CreationTimestamp
	@Column(name = "PATIENT_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@UpdateTimestamp
	@Column(name = "PATIENT_MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	
	@Column(name = "PATIENT_DELCHECK", length=5)
	private String delCheck;
	
	@OneToMany(mappedBy="surveyBindingInPatientId", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SurveyBinding> surveyBinding;
}