package com.brain.home.entity.diagnosis;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.brain.home.entity.patient.Patient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="DIAGNOSIS")
@Getter
@Setter
public class Diagnosis {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "DIAGNOSIS_ID")
	private Long id;
	
	@Column(name = "DIAGNOSIS_NAME", nullable = false, length=30)
	private String name;
	
	@Column(name = "DIAGNOSIS_DOCTOR_OPINION", nullable = false, length = 20)
	private Integer doctorOpinion;
	
	@Column(name = "DIAGNOSIS_ETC", length = 100)
	private String etc;
	
	@Column(name = "DIAGNOSIS_PURPOSE",  length = 20)
	private String purpose;
	
	@Column(name = "DIAGNOSIS_OCCUR_DATE", nullable = false, length = 30)
	private Date occurDate;
	
	@Column(name = "DIAGNOSIS_CREATED_BY", nullable = false, length = 60)
	private String createdBy;
	
	@Column(name = "DIAGNOSIS_MODIFIED_BY", nullable = false, length = 60)
	private String modifiedBy;
	
	@CreationTimestamp
	@Column(name = "DIAGNOSIS_CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@UpdateTimestamp
	@Column(name = "DIAGNOSIS_MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	
	@Column(name = "DIAGNOSIS_DELCHECK", length=5)
	private int delCheck;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="DIAGONOSIS_PATIENT_FK"), name="DIAGNOSIS_PATIENT_ID", referencedColumnName="PATIENT_ID", columnDefinition="BIGINT(20)")
	private Patient patientInDiagnosis;
}
