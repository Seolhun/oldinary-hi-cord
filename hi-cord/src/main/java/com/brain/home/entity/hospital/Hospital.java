package com.brain.home.entity.hospital;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.brain.home.entity.patient.Patient;
import com.brain.home.entity.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="HOSPITAL")
@Getter
@Setter
public class Hospital implements Serializable{
	private static final long serialVersionUID = -3911226629653961607L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "HOSPITAL_ID")
	private Long id;
	
	@Column(name = "HOSPITAL_NAME", nullable = false, length=30)
	private String name;
	
	@Column(name = "HOSPITAL_ZIP_CODE", nullable = false, length=20)
	private Integer zipCode;
	
	@Column(name = "HOSPITAL_ADDRESS", nullable = false, length=100)
	private String address;
	
	@Column(name = "HOSPITAL_TEL", nullable = false, length=30)
	private String tel;
	
	@Column(name = "HOSPITAL_INFORMATION", length=300)
	private String information;
	
	@CreationTimestamp
	@Column(name = "HOSPITAL_CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@UpdateTimestamp
	@Column(name = "HOSPITAL_MODIFICATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	
	@Column(name = "HOSPITAL_EMPLOYERS", nullable = false, length=30)
	private int employers;
	
	@Column(name = "HOSPITAL_DELCHECK", nullable = false, length=5)
	private int delCheck;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="MASTER_IN_HOSPITAL", nullable=false, unique=true, foreignKey=@ForeignKey(name="HOSPITAL_MASTER_FK"))
	private User masterInHospital;
	
	@OneToMany(mappedBy="hospitalInUser", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<User> userListInHospital;
	
	@OneToMany(mappedBy="hospitalInPatient", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Patient> patientListInHospital;
}
