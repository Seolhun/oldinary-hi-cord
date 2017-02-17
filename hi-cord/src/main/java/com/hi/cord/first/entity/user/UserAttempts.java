package com.hi.cord.first.entity.user;

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

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name="TB_USER_ATTEMPTS")
@Data
public class UserAttempts implements Serializable{
	private static final long serialVersionUID = -6645634619910097302L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ATTEMPTS_ID")
	private Long userAttemptsId;
	
	@Column(name = "USER_ATTEMPTS_EMAIL", nullable = false)
	@NotEmpty
	private String userAttemptsEmail;
	
	@Column(name = "USER_ATTEMPTS_ATTEMPT", nullable = false)
	@NotEmpty
	private int userAttemptsAttempts;

	@Column(name = "USER_ATTEMPTS_LATESTDATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date userAttemptsLatestDate;
	
	@Column(name = "USER_ATTEMPTS_LOGIN_IP")
	private String userAttemptsLoginIp;
	
	@Column(name = "USER_ATTEMPTS_SUCCESS_FLAG")
	private int userAttemptsSuccessFlag;
}
