package com.hi.cord.first.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "TB_USER_ATTEMPTS")
@Data
public class UserAttempts implements Serializable {
	private static final long serialVersionUID = -6645634619910097302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ATTEMPTS_ID")
	private Long userAttemptsId;

	@Column(name = "USER_ATTEMPTS_EMAIL", nullable = false)
	private String userAttemptsEmail;

	@Column(name = "USER_ATTEMPTS_COUNTS", nullable = false)
	private Integer userAttemptsCounts = 1;

	@Column(name = "USER_ATTEMPTS_CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date userAttemptsCreatedDate;

	@Column(name = "USER_ATTEMPTS_LOGIN_IP")
	private String userAttemptsLoginIp;

	@Column(name = "USER_ATTEMPTS_SUCCESS_FLAG")
	private Integer userAttemptsSuccessFlag=0;

	public UserAttempts() {

	}

	public UserAttempts(String userAttemptsEmail, Integer userAttemptsCounts, String userAttemptsLoginIp) {
		this.userAttemptsEmail=userAttemptsEmail;
		this.userAttemptsCounts=userAttemptsCounts;
		this.userAttemptsLoginIp=userAttemptsLoginIp;
	}
	
	public UserAttempts(String userAttemptsEmail, Integer userAttemptsCounts, String userAttemptsLoginIp, Integer userAttemptsSuccessFlag) {
		this.userAttemptsEmail=userAttemptsEmail;
		this.userAttemptsCounts=userAttemptsCounts;
		this.userAttemptsLoginIp=userAttemptsLoginIp;
		this.userAttemptsSuccessFlag=userAttemptsSuccessFlag;
	}
}
