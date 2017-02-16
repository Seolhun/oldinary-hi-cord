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
	private Integer id;
	
	@Column(name = "USER_EMAIL", nullable = false)
	@NotEmpty
	private String email;
	
	@Column(name = "USER_ATTEMPTS", nullable = false)
	@NotEmpty
	private int attempts;

	@Column(name = "USER_LATESTDATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date latestDate;
	
	@Column(name = "USER_LOGIN_IP")
	private String logIp;
	
	@Column(name = "USER_SUCCESS_FLAG")
	private int successFlag;
	
	@Column(name = "USER_VALIDATION")
	private String validation;
}
