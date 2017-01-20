package com.brain.home.entity.patient;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.brain.home.entity.diagnosis.Diagnosis;
import com.brain.home.entity.hospital.Hospital;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PATIENT")
@Getter
@Setter
public class Patient implements Serializable {
	private static final long serialVersionUID = 5788359229752820399L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PATIENT_ID")
	private Long id;
	
	@Column(name = "PATIENT_NAME", nullable = false, length=30)
	private String name;
	
	@Column(name = "PATIENT_ZIP_CODE", length = 20)
	private String zipCode;
	
	@Column(name = "PATIENT_ADDRESS",  length = 100)
	private String address;
	
	@Column(name = "PATIENT_IDENTIFICATION",  length = 20)
	private String identification;
	
	@Column(name = "PATIENT_PHONE", nullable = false, length = 30)
	private String phone;
	
	@Column(name = "PATIENT_EMAIL", nullable = false, length = 60)
	private String email;
	
	@Column(name = "PATIENT_CREATED_BY", nullable = false, length = 60)
	private String createdBy;
	
	@Column(name = "PATIENT_MODIFIED_BY", length = 60)
	private String modifiedBy;
	
	@CreationTimestamp
	@Column(name = "PATIENT_CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@UpdateTimestamp
	@Column(name = "PATIENT_MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	
	@Column(name = "PATIENT_DELCHECK", length=5)
	private int delCheck;
	
	@ManyToOne(targetEntity=Hospital.class, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="PATIENT_HOSPITAL_FK"), name="PATIENT_HOSPITAL_ID", referencedColumnName="HOSPITAL_ID", columnDefinition="BIGINT(20)")
	private Hospital hospitalInPatient;
	
	@OneToMany(mappedBy="patientInDiagnosis", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Diagnosis> diagnosisListInPatient;
}
