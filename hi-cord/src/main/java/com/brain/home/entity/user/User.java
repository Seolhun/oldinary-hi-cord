package com.brain.home.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.brain.home.entity.hospital.Hospital;
import com.brain.home.entity.patient.Patient;
import com.brain.home.entity.price.PriceRecord;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User implements Serializable {
	private static final long serialVersionUID = -3474096703802541016L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;
	
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$", message="Invalid Email")
	@Column(name = "USER_EMAIL", unique = true, nullable = false, length = 60)
	private String email;

	@Column(name = "USER_NAME", nullable = false, length = 30)
	private String name;
	
	//@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})", message="Invalid Password")
	@Column(name = "USER_PASSWORD", nullable = false, length = 100)
	private String password;
	
	@Pattern(regexp="\\d{10,11}", message="Invalid Phone")
	@Column(name = "USER_PHONE",unique = true, nullable = false, length = 30)
	private String phone;
	
	@Column(name = "USER_ZIP_CODE", length = 20)
	private String zipCode;
	
	@Column(name = "USER_ADDRESS", length = 100)
	private String address;
	
	@Column(name = "USER_JOB", length = 20)
	private String job;
	
	@Column(name = "USER_GENDER", nullable = false, length = 1)
	private int gender;
	
	@Column(name = "USER_RECEIVE_MAIL", length = 1)
	private int receiveEmail;
	
	@Column(name = "USER_RECEIVE_SMS", length = 1)
	private int receiveSMS;
	
	@Column(name = "USER_STATE", nullable = false, length = 20)
	private String state = State.ACTIVE.getState();
	
	//휴면 계좌 체크
	@Column(name = "USER_ACCOUNT_NON_EXPIRED", length = 1)
	private int accountNonExpired;
	
	//비밀번호 오류 체크
	@Column(name = "USER_CREDENTIALS_NON_EXPIRED", length = 1)
	private int credentialsNonExpired;
	
	//잠겨있는지 확인
	@Column(name = "USER_ACCOUNT_NON_LOCKED", length = 1)
	private int accountNonLocked;
	
	@CreationTimestamp
	@Column(name = "USER_CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@UpdateTimestamp
	@Column(name = "USER_MODIFICATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;
	
	@Column(name = "USER_DELCHECK", length=5)
	private int delCheck;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "USER_PROFILE_REFER",foreignKey=@ForeignKey(name="USER_REFER_FK"),  
		joinColumns = { @JoinColumn(name = "USER_ID", columnDefinition="BIGINT(20)") }, 
		inverseForeignKey=@ForeignKey(name="USER_PROFILE_REFER_FK"), inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
	
	//유저가 병원 주인
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="HOSPITAL_OF_MASTER", unique=true, foreignKey=@ForeignKey(name="MASTER_HOSPITAL_FK"))
	private Hospital hospitalOfMaster;
	
	//소속된 병원
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="HOSPITAL_IN_USER", foreignKey=@ForeignKey(name="USER_HOSPITAL_FK"), referencedColumnName="HOSPITAL_ID")
	private Hospital hospitalInUser;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(foreignKey=@ForeignKey(name="USER_REFER_FK2"), name = "USER_PATIENT_REFER", 
		joinColumns = { @JoinColumn(name = "USER_ID", columnDefinition="BIGINT(20)") }, 
		inverseForeignKey=@ForeignKey(name="USER_PATIENT_REFER_FK")
		, inverseJoinColumns = {@JoinColumn(name = "PATIENT_ID", columnDefinition="BIGINT(20)") })
	private Set<Patient> patientList= new HashSet<Patient>();
	
	@OneToMany(mappedBy="paidByUser", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PriceRecord> paidHistoryList;
}